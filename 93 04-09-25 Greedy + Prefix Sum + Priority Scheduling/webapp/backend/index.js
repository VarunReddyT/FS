// server.js
const express = require('express');
const cors = require('cors');
const WebSocket = require('ws');
const http = require('http');

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

const PORT = 4000;

app.use(cors());
app.use(express.json());

// Users with priorities
const users = {
  'admin': { priority: 1, name: 'Admin' },
  'vip': { priority: 2, name: 'VIP User' },
  'standard': { priority: 3, name: 'Standard User' },
  'guest': { priority: 4, name: 'Guest User' }
};

// Task management
let activeTask = null;
const taskQueue = [];
const clients = new Map(); // Store connected clients

// Efficient Fibonacci calculation with interruption support
function calculateFibonacci(n, signal) {
  return new Promise((resolve, reject) => {
    if (n <= 1) {
      resolve(BigInt(n));
      return;
    }
    
    let a = BigInt(0);
    let b = BigInt(1);
    let temp;
    
    // Calculate in chunks to allow for interruption
    const calculateChunk = () => {
      if (signal.aborted) {
        reject(new Error('Calculation aborted'));
        return;
      }
      
      const chunkSize = 10000; // Process 10,000 iterations at a time
      const iterations = Math.min(chunkSize, n - 2 - (i - 2));
      
      for (let j = 0; j < iterations; j++, i++) {
        temp = a + b;
        a = b;
        b = temp;
        
        if (i >= n) break;
      }
      
      if (i < n) {
        // Yield to event loop to check for interruptions
        setImmediate(calculateChunk);
      } else {
        resolve(b);
      }
    };
    
    let i = 2;
    calculateChunk();
  });
}

// Broadcast message to all connected clients
function broadcastMessage(message) {
  const messageStr = JSON.stringify(message);
  for (const client of clients.values()) {
    if (client.readyState === WebSocket.OPEN) {
      client.send(messageStr);
    }
  }
}

// Broadcast current status to all connected clients
function broadcastStatus() {
  const statusUpdate = {
    type: 'status',
    active: activeTask ? {
      requestId: activeTask.requestId,
      userId: activeTask.userId,
      priority: activeTask.priority,
      n: activeTask.n
    } : null,
    queued: taskQueue.map(task => ({
      requestId: task.requestId,
      userId: task.userId,
      priority: task.priority,
      n: task.n
    }))
  };

  broadcastMessage(statusUpdate);
}

// Process the task queue
async function processQueue() {
  console.log(`Processing queue with ${taskQueue.length} tasks, activeTask: ${activeTask ? activeTask.requestId : 'none'}`);
  
  if (taskQueue.length === 0) return;
  
  // Sort by priority (lower number = higher priority)
  taskQueue.sort((a, b) => a.priority - b.priority);
  const nextTask = taskQueue[0]; // Don't remove from queue yet
  
  // If there's no active task, start the next one
  if (!activeTask) {
    console.log(`No active task, starting next task ${nextTask.requestId} (priority ${nextTask.priority})`);
    activeTask = taskQueue.shift();
    startTask(activeTask);
    return;
  }
  
  // If the next task has higher priority (lower number) than the active task
  if (nextTask.priority < activeTask.priority) {
    console.log(`Preempting task ${activeTask.requestId} (priority ${activeTask.priority}) for task ${nextTask.requestId} (priority ${nextTask.priority})`);
    
    // Store the interrupted task to resume later
    const interruptedTask = { ...activeTask };
    
    // Create a new abort controller for when the task is resumed
    interruptedTask.abortController = new AbortController();
    
    // Mark the current task as preempted to prevent startTask from reprocessing it
    activeTask.wasPreempted = true;
    
    // Abort the current task
    activeTask.abortController.abort();
    
    // Broadcast interruption to all clients
    broadcastMessage({
      type: 'task_interrupted',
      requestId: activeTask.requestId,
      userId: activeTask.userId
    });
    
    // Clear activeTask before starting the new one
    activeTask = null;
    
    // Start the higher priority task
    activeTask = taskQueue.shift();
    
    // Put the interrupted task back in the queue
    taskQueue.push(interruptedTask);
    
    // Sort the queue again to maintain priority order
    taskQueue.sort((a, b) => a.priority - b.priority);
    
    // Start the higher priority task
    startTask(activeTask);
    
    // Broadcast updated status to all clients
    broadcastStatus();
  }
}

// Function to start a task
async function startTask(task) {
  console.log(`Starting task ${task.requestId} (priority ${task.priority}) for user ${task.userId}`);
  
  // Broadcast that calculation started
  broadcastMessage({
    type: 'calculation_started',
    requestId: task.requestId,
    userId: task.userId,
    n: task.n
  });
  
  let wasAborted = false;
  
  try {
    console.log(`Calculating Fibonacci(${task.n}) for task ${task.requestId}`);
    const result = await calculateFibonacci(task.n, task.abortController.signal);
    
    console.log(`Completed calculation for task ${task.requestId}`);
    // Prepare result message
    const resultStr = result.toString();
    const truncatedResult = resultStr.length > 50 ? 
      resultStr.substring(0, 50) + "..." : resultStr;
    
    // Broadcast result to all clients
    broadcastMessage({
      type: 'result',
      requestId: task.requestId,
      userId: task.userId,
      result: truncatedResult,
      fullResult: resultStr,
      n: task.n
    });
  } catch (error) {
    if (error.message === 'Calculation aborted') {
      console.log(`Calculation aborted for request ${task.requestId}`);
      wasAborted = true;
      
      // Only broadcast interruption if it wasn't already handled by preemption
      if (!task.wasPreempted) {
        broadcastMessage({
          type: 'task_interrupted',
          requestId: task.requestId,
          userId: task.userId
        });
      }
      // Task aborted - it will be handled during preemption or cleanup
    } else {
      // Other error - broadcast to all clients
      console.error(`Error calculating fibonacci: ${error.message}`);
      broadcastMessage({
        type: 'error',
        requestId: task.requestId,
        userId: task.userId,
        message: error.message
      });
    }
  } finally {
    // If the task was aborted due to preemption, the activeTask is already 
    // cleared and the task is back in the queue
    if (!wasAborted) {
      activeTask = null;
      // Process next task after a small delay to avoid blocking
      setTimeout(processQueue, 0);
      // Broadcast updated status
      broadcastStatus();
    } else if (task.wasPreempted) {
      // For preempted tasks, we don't need to do anything here
      // activeTask has already been cleared and the new task started
    } else {
      // For non-preempted aborts (e.g., manual cancellation)
      activeTask = null;
      setTimeout(processQueue, 0);
      broadcastStatus();
    }
  }
}

// WebSocket connection handling
wss.on('connection', (ws, req) => {
  const clientId = Date.now() + Math.random().toString(36).substr(2, 5);
  clients.set(clientId, ws);
  console.log(`Client connected: ${clientId}`);
  
  // Send current status to new client
  const status = {
    type: 'status',
    active: activeTask ? {
      requestId: activeTask.requestId,
      userId: activeTask.userId,
      priority: activeTask.priority,
      n: activeTask.n
    } : null,
    queued: taskQueue.map(task => ({
      requestId: task.requestId,
      userId: task.userId,
      priority: task.priority,
      n: task.n
    }))
  };
  
  ws.send(JSON.stringify(status));
  
  ws.on('message', (message) => {
    try {
      const data = JSON.parse(message);
      
      if (data.type === 'calculate') {
        const { n, userId } = data;
        const user = users[userId];
        
        if (!user) {
          ws.send(JSON.stringify({
            type: 'error',
            message: 'Invalid user'
          }));
          return;
        }
        
        if (typeof n !== 'number' || n < 0 || !Number.isInteger(n)) {
          ws.send(JSON.stringify({
            type: 'error',
            message: 'n must be a non-negative integer'
          }));
          return;
        }
        
        const requestId = Date.now() + Math.random().toString(36).substr(2, 5);
        const priority = user.priority;
        const abortController = new AbortController();
        
        // Create the new task
        const newTask = {
          n,
          priority,
          userId,
          requestId,
          clientId,
          abortController
        };
        
        // Add task to queue
        taskQueue.push(newTask);
        
        // Broadcast that task was queued to all clients
        broadcastMessage({
          type: 'task_queued',
          requestId,
          userId,
          priority,
          n,
          position: taskQueue.length
        });
        
        // Process the queue immediately to check for preemption
        processQueue();
        
        // Broadcast updated status
        broadcastStatus();
      }
    } catch (error) {
      console.error('Error processing message:', error);
    }
  });
  
  ws.on('close', () => {
    clients.delete(clientId);
    console.log(`Client disconnected: ${clientId}`);
  });
});

// HTTP endpoint for getting current status (optional)
app.get('/status', (req, res) => {
  const active = activeTask ? {
    requestId: activeTask.requestId,
    userId: activeTask.userId,
    priority: activeTask.priority,
    n: activeTask.n
  } : null;
  
  const queued = taskQueue.map(task => ({
    requestId: task.requestId,
    userId: task.userId,
    priority: task.priority,
    n: task.n
  }));
  
  res.json({ active, queued });
});

server.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
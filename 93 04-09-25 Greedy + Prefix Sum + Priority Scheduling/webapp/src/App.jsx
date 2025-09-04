// App.jsx
import { useState, useEffect, useRef } from 'react'
import './App.css'

function App() {
  const [number, setNumber] = useState('')
  const [user, setUser] = useState('standard')
  const [results, setResults] = useState([])
  const [connected, setConnected] = useState(false)
  const [status, setStatus] = useState({ active: null, queued: [] })
  const ws = useRef(null)

  useEffect(() => {
    // Connect to WebSocket server
    ws.current = new WebSocket('ws://localhost:4000')
    
    ws.current.onopen = () => {
      console.log('Connected to server')
      setConnected(true)
    }
    
    ws.current.onclose = () => {
      console.log('Disconnected from server')
      setConnected(false)
    }
    
    ws.current.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data)
        console.log('Received:', data)
        
        switch (data.type) {
          case 'status':
            setStatus({
              active: data.active,
              queued: data.queued
            })
            break
            
          case 'task_queued':
            setResults(prev => [...prev, {
              requestId: data.requestId,
              userId: data.userId,
              status: 'queued',
              priority: data.priority,
              position: data.position,
              n: data.n
            }])
            break
            
          case 'calculation_started':
            setResults(prev => prev.map(result => 
              result.requestId === data.requestId 
                ? { ...result, status: 'calculating', n: data.n, userId: data.userId }
                : result
            ))
            break
            
          case 'result':
            setResults(prev => prev.map(result => 
              result.requestId === data.requestId 
                ? { 
                    ...result, 
                    status: 'completed', 
                    result: data.result,
                    fullResult: data.fullResult,
                    userId: data.userId
                  }
                : result
            ))
            break
            
          case 'task_interrupted':
            setResults(prev => prev.map(result => 
              result.requestId === data.requestId 
                ? { ...result, status: 'interrupted', userId: data.userId }
                : result
            ))
            break
            
          case 'error':
            setResults(prev => prev.map(result => 
              result.requestId === data.requestId 
                ? { ...result, status: 'error', error: data.message, userId: data.userId }
                : result
            ))
            break
            
          default:
            console.log('Unknown message type:', data.type)
        }
      } catch (error) {
        console.error('Error parsing message:', error)
      }
    }
    
    return () => {
      if (ws.current) {
        ws.current.close()
      }
    }
  }, [])

  const handleSubmit = (e) => {
    e.preventDefault()
    if (!number || isNaN(number) || number < 0) {
      alert('Please enter a valid non-negative integer')
      return
    }

    if (!connected || !ws.current) {
      alert('Not connected to server')
      return
    }

    // Send calculation request
    ws.current.send(JSON.stringify({
      type: 'calculate',
      n: parseInt(number),
      userId: user
    }))

    // Clear input
    setNumber('')
  }

  const getUserLabel = (userId) => {
    const labels = {
      'admin': 'Admin',
      'vip': 'VIP',
      'standard': 'Standard',
      'guest': 'Guest'
    }
    return labels[userId] || userId
  }

  const getStatusColor = (status) => {
    const colors = {
      'queued': '#ff9800',
      'calculating': '#2196f3',
      'completed': '#4caf50',
      'interrupted': '#f44336',
      'error': '#f44336'
    }
    return colors[status] || '#9e9e9e'
  }

  return (
    <div className="app">
      <h1>Priority-Based Fibonacci Calculator</h1>
      
      {/* <div className="connection-status">
        Connection: {connected ? 
          <span className="connected">Connected</span> : 
          <span className="disconnected">Disconnected</span>}
      </div> */}
      
      <form onSubmit={handleSubmit} className="form">
        <div className="form-group">
          <label htmlFor="user">Select User:</label>
          <select 
            id="user" 
            value={user} 
            onChange={(e) => setUser(e.target.value)}
          >
            <option value="admin">Admin (Priority 1)</option>
            <option value="vip">VIP User (Priority 2)</option>
            <option value="standard">Standard User (Priority 3)</option>
            <option value="guest">Guest User (Priority 4)</option>
          </select>
        </div>
        
        <div className="form-group">
          <label htmlFor="number">Enter n for Fibonacci calculation:</label>
          <input
            id="number"
            type="number"
            value={number}
            onChange={(e) => setNumber(e.target.value)}
            min="0"
            required
          />
        </div>
        
        <button type="submit" disabled={!connected}>
          Calculate Fibonacci
        </button>
      </form>
{/*       
      <div className="status-panel">
        <h2>Current Status</h2>
        <div className="active-task">
          <h3>Active Task:</h3>
          {status.active ? (
            <div className="task-info">
              <span>Request #{status.active.requestId}</span>
              <span>User: {getUserLabel(status.active.userId)}</span>
              <span>Priority: {status.active.priority}</span>
              <span>n: {status.active.n}</span>
            </div>
          ) : (
            <p>No active task</p>
          )}
        </div>
        
        <div className="queued-tasks">
          <h3>Queued Tasks: ({status.queued.length})</h3>
          {status.queued.length > 0 ? (
            <ul>
              {status.queued.map((task, index) => (
                <li key={task.requestId} className="task-info">
                  <span>Request #{task.requestId}</span>
                  <span>User: {getUserLabel(task.userId)}</span>
                  <span>Priority: {task.priority}</span>
                  <span>n: {task.n}</span>
                  <span>Position: {index + 1}</span>
                </li>
              ))}
            </ul>
          ) : (
            <p>No queued tasks</p>
          )}
        </div>
      </div> */}
      
      <div className="results">
        <h2>Calculation Results</h2>
        {results.length === 0 ? (
          <p>No calculations yet</p>
        ) : (
          <div className="results-list">
            {results.map((result) => (
              <div 
                key={result.requestId} 
                className="result-item"
                data-user={result.userId || user}
              >
                {(result.userId || user) === user && (
                  <div className="my-request">Your Request</div>
                )}
                <div className="result-header">
                  <span className="request-id">Request #{result.requestId}</span>
                  <span className="user-type">{getUserLabel(result.userId || user)}</span>
                  <span 
                    className="status" 
                    style={{ color: getStatusColor(result.status) }}
                  >
                    {result.status.toUpperCase()}
                  </span>
                </div>
                <div className="result-details">
                  <span>n = {result.n}</span>
                  {result.position && <span>Queue position: {result.position}</span>}
                  {result.priority && <span>Priority: {result.priority}</span>}
                </div>
                {result.result && (
                  <div className="calculation-result">
                    <strong>Result:</strong> 
                    <span className="result-value">{result.result}</span>
                    {result.fullResult && result.result.includes('...') && (
                      <details className="full-result-details">
                        <summary>View Full Result</summary>
                        <div className="full-result-container">
                          {result.fullResult}
                        </div>
                      </details>
                    )}
                  </div>
                )}
                {result.error && (
                  <div className="error">
                    <strong>Error:</strong> {result.error}
                  </div>
                )}
              </div>
            ))}
          </div>
        )}
      </div>
      
      <div className="info">
        <h3>How it works:</h3>
        <ul>
          <li>Higher priority users can interrupt lower priority calculations</li>
          <li>Priority 1 (Admin) is highest, Priority 4 (Guest) is lowest</li>
          <li>Interrupted calculations are resumed after higher priority tasks complete</li>
          <li>All communication happens in real-time via WebSockets</li>
        </ul>
      </div>
    </div>
  )
}

export default App
// Task : Digital Clock with Countdown Timer
// Functionality: Display current time, countdown timer, and start/stop controls

import React, { useState, useEffect, useRef } from 'react';
import './App.css';

export default function ClockComponent() {
  const [currentTime, setCurrentTime] = useState(new Date());
  const [inputMin, setInputMin] = useState('');
  const [intervalMin, setIntervalMin] = useState(null);
  const [running, setRunning] = useState(false);
  const minuteCountdownRef = useRef(null);
  const clockRef = useRef(null);

  useEffect(() => {
    if (running && intervalMin > 0) {
      clockRef.current = setInterval(() => {
        setCurrentTime(new Date());
      }, 1000);
    }
    return () => clearInterval(clockRef.current);
  }, [running, intervalMin]);

  useEffect(() => {
    if (running && intervalMin > 0) {
      minuteCountdownRef.current = setInterval(() => {
        setIntervalMin((prev) => {
          if (prev > 1) return prev - 1;
          clearInterval(minuteCountdownRef.current);
          setRunning(false);
          return 0;
        });
      }, 60000);
    }
    return () => clearInterval(minuteCountdownRef.current);
  }, [running]);

  const handleStart = () => {
    const parsed = parseInt(inputMin);
    if (!running && intervalMin === null && parsed > 0) {
      setIntervalMin(parsed);
      setCurrentTime(new Date());
      setRunning(true);
    } else if (!running && intervalMin > 0) {
      setCurrentTime(new Date());
      setRunning(true);
    }
  };

  const handleStop = () => {
    setRunning(false);
    clearInterval(clockRef.current);
    clearInterval(minuteCountdownRef.current);
  };

  const formatTime = (date) =>
    `${String(date.getHours()).padStart(2, '0')} | ${String(
      date.getMinutes()
    ).padStart(2, '0')} | ${String(date.getSeconds()).padStart(2, '0')}`;

  return (
    <div className="clock-container">
      <div className="clock-box">
        <h1 className="title">Digital Clock</h1>
        <div className="time">{formatTime(currentTime)}</div>
        <div className="countdown">
          ⏳ Countdown: <strong>{intervalMin !== null ? intervalMin : '--'}</strong> min
        </div>
        <div className="controls">
          <input
            type="number"
            placeholder="Enter minutes"
            value={inputMin}
            onChange={(e) => setInputMin(e.target.value)}
            disabled={running}
          />
          <button onClick={handleStart}>
            {intervalMin !== null && !running ? 'Resume' : 'Start'}
          </button>
          <button onClick={handleStop}>Stop</button>
        </div>
        <p className="note">
          Time updates every second. Countdown decreases every minute.
        </p>
      </div>
    </div>
  );
}



// body {
//     margin: 0;
//     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
//     background: linear-gradient(to right, #1e1e2f, #2c3e50);
//     color: #fff;
//   }
  
//   .clock-container {
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     min-height: 100vh;
//     padding: 1rem;
//   }
  
//   .clock-box {
//     background-color: #2c2f40;
//     padding: 2rem;
//     border-radius: 12px;
//     box-shadow: 0 8px 20px rgba(0, 0, 0, 0.4);
//     text-align: center;
//     max-width: 400px;
//     width: 100%;
//   }
  
//   .title {
//     font-size: 1.8rem;
//     margin-bottom: 1rem;
//   }
  
//   .time {
//     font-size: 2.5rem;
//     margin: 1rem 0;
//     background: #00000050;
//     padding: 0.8rem;
//     border-radius: 10px;
//     font-family: monospace;
//     letter-spacing: 3px;
//   }
  
//   .countdown {
//     font-size: 1.1rem;
//     margin-bottom: 1.2rem;
//   }
  
//   .controls {
//     display: flex;
//     gap: 0.5rem;
//     justify-content: center;
//     margin-bottom: 1rem;
//   }
  
//   .controls input {
//     padding: 0.5rem;
//     width: 100px;
//     border-radius: 6px;
//     border: 1px solid #555;
//     background-color: #1e1e2f;
//     color: white;
//   }
  
//   .controls input:disabled {
//     opacity: 0.5;
//   }
  
//   .controls button {
//     padding: 0.5rem 1rem;
//     border: none;
//     border-radius: 6px;
//     background-color: #3498db;
//     color: white;
//     cursor: pointer;
//     font-weight: bold;
//     transition: background 0.2s;
//   }
  
//   .controls button:hover {
//     background-color: #2980b9;
//   }
  
//   .controls button:nth-child(3) {
//     background-color: #e74c3c;
//   }
  
//   .controls button:nth-child(3):hover {
//     background-color: #c0392b;
//   }
  
//   .note {
//     font-size: 0.85rem;
//     color: #aaa;
//   }
  
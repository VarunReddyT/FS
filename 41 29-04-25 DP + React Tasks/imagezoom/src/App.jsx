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

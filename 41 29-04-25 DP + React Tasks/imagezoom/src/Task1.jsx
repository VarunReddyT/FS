// Task: Image Zoom Demo
// Functionality: Zoom in, zoom out, and reset the image scale

import React, { useState } from 'react';
import './App.css';
import image from "./assets/22BD1A051U.jpg"

function App() {
  const [scale, setScale] = useState(1);
  
  const zoomIn = () => {
    setScale(prevScale => prevScale * 1.2);
  };
  
  const zoomOut = () => {
    setScale(prevScale => Math.max(prevScale / 1.2, 0.1));
  };
  
  const reset = () => {
    setScale(1);
  };

  return (
    <div className="app">
      <h1>Image Zoom Demo</h1>
      <div className="controls">
        <button onClick={zoomIn}>Zoom In</button>
        <button onClick={zoomOut}>Zoom Out</button>
        <button onClick={reset}>Reset</button>
      </div>
      <div className="image-container">
        <img 
          src={image} 
          alt="Zoomable content"
          style={{
            transform: `scale(${scale})`,
            transformOrigin: 'center center',
            width: '100%',
            height: '100%',
            objectFit: 'cover',
            transition: 'transform 0.3s ease'
          }}
        />
      </div>
      <div className="scale-info">Current Scale: {scale.toFixed(2)}x</div>
    </div>
  );
}

export default App;


// .app {
//     max-width: 800px;
//     margin: 0 auto;
//     padding: 20px;
//     text-align: center;
//   }
  
//   .controls {
//     margin: 20px 0;
//   }
  
//   .controls button {
//     margin: 0 10px;
//     padding: 8px 16px;
//     font-size: 16px;
//     cursor: pointer;
//   }
  
//   .image-container {
//     width: 600px;
//     height: 400px;
//     margin: 0 auto;
//     border: 2px solid #333;
//     overflow: hidden;
//     position: relative;
//   }
  
//   .scale-info {
//     margin-top: 15px;
//     font-size: 18px;
//     color: #555;
//   }
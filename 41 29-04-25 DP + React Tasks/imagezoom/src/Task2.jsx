// Task : Lightbox Image Gallery
// Functionality: Click to view, zoom in/out, drag to move, and navigate through images

import React, { useState, useRef, useEffect } from 'react';
import './App.css';

import image1 from './assets/gallery1.jpeg';
import image2 from './assets/gallery2.jpeg';
import image3 from './assets/gallery3.jpeg';
import image4 from './assets/gallery4.jpeg';
import image5 from './assets/gallery5.jpeg';
import image6 from './assets/gallery6.jpeg';
import image7 from './assets/gallery7.jpeg';
import image8 from './assets/gallery8.jpeg';
import image9 from './assets/gallery9.jpeg';
import image10 from './assets/gallery10.jpeg';

const images = [
  { src: image1, alt: 'Nature landscape' },
  { src: image2, alt: 'Mountain view' },
  { src: image3, alt: 'Ocean sunset' },
  { src: image4, alt: 'Forest path' },
  { src: image5, alt: 'City skyline' },
  { src: image6, alt: 'Desert dunes' },
  { src: image7, alt: 'Waterfall' },
  { src: image8, alt: 'Beach scene' },
  { src: image9, alt: 'Winter mountains' },
  { src: image10, alt: 'Autumn leaves' },
];

function App() {
  const [currentImageIndex, setCurrentImageIndex] = useState(null);
  const [zoomLevel, setZoomLevel] = useState(1);
  const [position, setPosition] = useState({ x: 0, y: 0 });
  const [isDragging, setIsDragging] = useState(false);
  const [startPos, setStartPos] = useState({ x: 0, y: 0 });
  const imageRef = useRef(null);

  const openLightbox = (index) => {
    setCurrentImageIndex(index);
    setZoomLevel(1);
    setPosition({ x: 0, y: 0 });
    document.body.style.overflow = 'hidden';
  };

  const closeLightbox = () => {
    setCurrentImageIndex(null);
    document.body.style.overflow = 'auto';
  };

  const goToPrev = () => {
    setCurrentImageIndex((prev) => (prev === 0 ? images.length - 1 : prev - 1));
    setZoomLevel(1);
    setPosition({ x: 0, y: 0 });
  };

  const goToNext = () => {
    setCurrentImageIndex((prev) => (prev === images.length - 1 ? 0 : prev + 1));
    setZoomLevel(1);
    setPosition({ x: 0, y: 0 });
  };

  const zoomIn = () => {
    setZoomLevel((prev) => Math.min(prev * 1.2, 3));
  };

  const zoomOut = () => {
    setZoomLevel((prev) => Math.max(prev / 1.2, 1));
  };

  const resetZoom = () => {
    setZoomLevel(1);
    setPosition({ x: 0, y: 0 });
  };

  const handleMouseDown = (e) => {
    if (zoomLevel === 1) return;
    setIsDragging(true);
    setStartPos({
      x: e.clientX - position.x,
      y: e.clientY - position.y,
    });
  };

  const handleMouseMove = (e) => {
    if (!isDragging) return;
    setPosition({
      x: e.clientX - startPos.x,
      y: e.clientY - startPos.y,
    });
  };

  const handleMouseUp = () => {
    setIsDragging(false);
  };

  useEffect(() => {
    const handleKeyDown = (e) => {
      if (currentImageIndex === null) return;
      if (e.key === 'Escape') closeLightbox();
      if (e.key === 'ArrowLeft') goToPrev();
      if (e.key === 'ArrowRight') goToNext();
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [currentImageIndex]);

  return (
    <div className="app">
      <header className="app-header">
        <h1>Photo Gallery</h1>
        <p>Click on any image to view</p>
      </header>

      <div className="gallery-grid">
        {images.map((image, index) => (
          <div 
            key={index} 
            className="gallery-item"
            onClick={() => openLightbox(index)}
          >
            <img 
              src={image.src} 
              alt={image.alt}
              loading="lazy"
            />
            <div className="image-overlay">
              <span>View</span>
            </div>
          </div>
        ))}
      </div>

      {currentImageIndex !== null && (
        <div className="lightbox">
          <div className="lightbox-overlay" onClick={closeLightbox}></div>
          
          <div className="lightbox-content">
            <button className="nav-btn prev-btn" onClick={goToPrev}>
              &lt;
            </button>
            
            <div 
              className="image-container"
              onMouseDown={handleMouseDown}
              onMouseMove={handleMouseMove}
              onMouseUp={handleMouseUp}
              onMouseLeave={handleMouseUp}
            >
              <img
                ref={imageRef}
                src={images[currentImageIndex].src}
                alt={images[currentImageIndex].alt}
                style={{
                  transform: `scale(${zoomLevel}) translate(${position.x}px, ${position.y}px)`,
                  cursor: zoomLevel > 1 ? (isDragging ? 'grabbing' : 'grab') : 'default'
                }}
              />
            </div>
            
            <button className="nav-btn next-btn" onClick={goToNext}>
              &gt;
            </button>
          </div>

          <div className="lightbox-controls">
            <button onClick={zoomOut} title="Zoom Out">
              <svg viewBox="0 0 24 24">
                <path d="M19 13H5v-2h14v2z"/>
              </svg>
            </button>
            <button onClick={resetZoom} title="Reset Zoom">
              <svg viewBox="0 0 24 24">
                <path d="M12 5V1L7 6l5 5V7c3.31 0 6 2.69 6 6s-2.69 6-6 6-6-2.69-6-6H4c0 4.42 3.58 8 8 8s8-3.58 8-8-3.58-8-8-8z"/>
              </svg>
            </button>
            <button onClick={zoomIn} title="Zoom In">
              <svg viewBox="0 0 24 24">
                <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
              </svg>
            </button>
            <span className="zoom-level">{Math.round(zoomLevel * 100)}%</span>
          </div>

          <div className="image-counter">
            {currentImageIndex + 1} / {images.length}
          </div>

          <button className="close-btn" onClick={closeLightbox} title="Close">
            &times;
          </button>
        </div>
      )}
    </div>
  );
}

export default App;


// :root {
//     --primary-color: #4a6fa5;
//     --secondary-color: #166088;
//     --background-color: #f8f9fa;
//     --text-color: #333;
//     --light-text: #fff;
//     --overlay-color: rgba(0, 0, 0, 0.9);
//     --transition-speed: 0.3s;
//   }
  
//   * {
//     box-sizing: border-box;
//     margin: 0;
//     padding: 0;
//   }
  
//   body {
//     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
//     line-height: 1.6;
//     color: var(--text-color);
//     background-color: var(--background-color);
//   }
  
//   .app {
//     max-width: 1200px;
//     margin: 0 auto;
//     padding: 2rem;
//   }
  
//   .app-header {
//     text-align: center;
//     margin-bottom: 2rem;
//   }
  
//   .app-header h1 {
//     font-size: 2.5rem;
//     color: var(--secondary-color);
//     margin-bottom: 0.5rem;
//   }
  
//   .gallery-grid {
//     display: grid;
//     grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
//     gap: 1.5rem;
//   }
  
//   .gallery-item {
//     position: relative;
//     border-radius: 8px;
//     overflow: hidden;
//     box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
//     transition: transform var(--transition-speed) ease, box-shadow var(--transition-speed) ease;
//     aspect-ratio: 1 / 1;
//   }
  
//   .gallery-item:hover {
//     transform: translateY(-5px);
//     box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
//   }
  
//   .gallery-item img {
//     width: 100%;
//     height: 100%;
//     object-fit: cover;
//     display: block;
//     transition: transform var(--transition-speed) ease;
//   }
  
//   .gallery-item:hover img {
//     transform: scale(1.05);
//   }
  
//   .image-overlay {
//     position: absolute;
//     top: 0;
//     left: 0;
//     right: 0;
//     bottom: 0;
//     background: rgba(0, 0, 0, 0.3);
//     display: flex;
//     align-items: center;
//     justify-content: center;
//     opacity: 0;
//     transition: opacity var(--transition-speed) ease;
//     color: white;
//     font-weight: bold;
//   }
  
//   .gallery-item:hover .image-overlay {
//     opacity: 1;
//   }
  
//   .lightbox {
//     position: fixed;
//     top: 0;
//     left: 0;
//     right: 0;
//     bottom: 0;
//     z-index: 1000;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//   }
  
//   .lightbox-overlay {
//     position: absolute;
//     top: 0;
//     left: 0;
//     right: 0;
//     bottom: 0;
//     background-color: var(--overlay-color);
//   }
  
//   .lightbox-content {
//     position: relative;
//     z-index: 1001;
//     width: 90%;
//     max-width: 1200px;
//     height: 90vh;
//     display: flex;
//     align-items: center;
//   }
  
//   .image-container {
//     width: 100%;
//     height: 100%;
//     overflow: hidden;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//   }
  
//   .image-container img {
//     max-width: 100%;
//     max-height: 100%;
//     object-fit: contain;
//     transition: transform var(--transition-speed) ease;
//   }
  
//   .nav-btn {
//     position: absolute;
//     top: 50%;
//     transform: translateY(-50%);
//     width: 50px;
//     height: 50px;
//     background-color: rgba(255, 255, 255, 0.2);
//     border: none;
//     border-radius: 50%;
//     color: white;
//     font-size: 1.5rem;
//     cursor: pointer;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     z-index: 1002;
//     transition: background-color var(--transition-speed) ease;
//   }
  
//   .nav-btn:hover {
//     background-color: rgba(255, 255, 255, 0.4);
//   }
  
//   .prev-btn {
//     left: 20px;
//   }
  
//   .next-btn {
//     right: 20px;
//   }
  
//   .lightbox-controls {
//     position: fixed;
//     bottom: 30px;
//     left: 50%;
//     transform: translateX(-50%);
//     display: flex;
//     align-items: center;
//     gap: 15px;
//     background-color: rgba(0, 0, 0, 0.7);
//     padding: 10px 20px;
//     border-radius: 30px;
//     z-index: 1002;
//   }
  
//   .lightbox-controls button {
//     background: none;
//     border: none;
//     color: white;
//     width: 40px;
//     height: 40px;
//     border-radius: 50%;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     cursor: pointer;
//     transition: background-color var(--transition-speed) ease;
//   }
  
//   .lightbox-controls button:hover {
//     background-color: rgba(255, 255, 255, 0.2);
//   }
  
//   .lightbox-controls button svg {
//     width: 24px;
//     height: 24px;
//     fill: currentColor;
//   }
  
//   .zoom-level {
//     color: white;
//     font-size: 0.9rem;
//     min-width: 50px;
//     text-align: center;
//   }
  
//   .image-counter {
//     position: fixed;
//     top: 20px;
//     left: 50%;
//     transform: translateX(-50%);
//     color: white;
//     background-color: rgba(0, 0, 0, 0.7);
//     padding: 5px 15px;
//     border-radius: 20px;
//     z-index: 1002;
//   }
  
//   .close-btn {
//     position: fixed;
//     top: 20px;
//     right: 20px;
//     width: 40px;
//     height: 40px;
//     background-color: rgba(255, 255, 255, 0.2);
//     border: none;
//     border-radius: 50%;
//     color: white;
//     font-size: 1.5rem;
//     cursor: pointer;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     z-index: 1002;
//     transition: background-color var(--transition-speed) ease;
//   }
  
//   .close-btn:hover {
//     background-color: rgba(255, 255, 255, 0.4);
//   }
  
//   @media (max-width: 768px) {
//     .gallery-grid {
//       grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
//       gap: 1rem;
//     }
    
//     .nav-btn {
//       width: 40px;
//       height: 40px;
//       font-size: 1.2rem;
//     }
    
//     .lightbox-controls {
//       bottom: 15px;
//       padding: 8px 15px;
//     }
    
//     .lightbox-controls button {
//       width: 35px;
//       height: 35px;
//     }
//   }
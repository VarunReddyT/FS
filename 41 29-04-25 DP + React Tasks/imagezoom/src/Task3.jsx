// Task : Image Gallery with Zoom and Drag using keystrokes 
// Functionality: View images in a gallery, zoom in/out, drag to move, and keyboard shortcuts

import React, { useState, useRef, useEffect } from 'react';
import './App.css';

// Import your images
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
  const lightboxRef = useRef(null);

  // Keyboard shortcuts
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (currentImageIndex === null) return;

      switch (e.key) {
        case 'Escape':
          closeLightbox();
          break;
        case 'ArrowLeft':
          goToPrev();
          break;
        case 'ArrowRight':
          goToNext();
          break;
        case 'ArrowUp':
          zoomOut();
          break;
        case 'ArrowDown':
          zoomIn();
          break;
        case '0':
          resetZoom();
          break;
        default:
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [currentImageIndex, zoomLevel]);

  const openLightbox = (index) => {
    setCurrentImageIndex(index);
    resetZoom(); // Reset to default size when opening
    document.body.style.overflow = 'hidden';
  };

  const closeLightbox = () => {
    setCurrentImageIndex(null);
    document.body.style.overflow = 'auto';
  };

  const goToPrev = () => {
    setCurrentImageIndex((prev) => (prev === 0 ? images.length - 1 : prev - 1));
    resetZoom(); // Reset to default size when changing images
  };

  const goToNext = () => {
    setCurrentImageIndex((prev) => (prev === images.length - 1 ? 0 : prev + 1));
    resetZoom(); // Reset to default size when changing images
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

  return (
    <div className="app">
      <header className="app-header">
        <h1>Photo Gallery</h1>
        <p>Click on any image to view</p>
        <div className="keyboard-hints">
          <div><kbd>←</kbd> Previous</div>
          <div><kbd>→</kbd> Next</div>
          <div><kbd>↑</kbd> Zoom Out</div>
          <div><kbd>↓</kbd> Zoom In</div>
          <div><kbd>0</kbd> Reset</div>
          <div><kbd>ESC</kbd> Close</div>
        </div>
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
        <div 
          className="lightbox"
          ref={lightboxRef}
          tabIndex="0"
        >
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
            <button onClick={zoomOut} title="Zoom Out (↑)">
              <svg viewBox="0 0 24 24">
                <path d="M19 13H5v-2h14v2z"/>
              </svg>
            </button>
            <button onClick={resetZoom} title="Reset Zoom (0)">
              <svg viewBox="0 0 24 24">
                <path d="M12 5V1L7 6l5 5V7c3.31 0 6 2.69 6 6s-2.69 6-6 6-6-2.69-6-6H4c0 4.42 3.58 8 8 8s8-3.58 8-8-3.58-8-8-8z"/>
              </svg>
            </button>
            <button onClick={zoomIn} title="Zoom In (↓)">
              <svg viewBox="0 0 24 24">
                <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
              </svg>
            </button>
            <span className="zoom-level">{Math.round(zoomLevel * 100)}%</span>
          </div>

          <div className="image-counter">
            {currentImageIndex + 1} / {images.length}
          </div>

          <button className="close-btn" onClick={closeLightbox} title="Close (ESC)">
            &times;
          </button>
        </div>
      )}
    </div>
  );
}

export default App;


/* Add to existing CSS */
// .keyboard-hints {
//     display: flex;
//     gap: 1.5rem;
//     justify-content: center;
//     margin-top: 1rem;
//     font-size: 0.9rem;
//     color: var(--secondary-color);
//   }
  
//   .keyboard-hints div {
//     display: flex;
//     align-items: center;
//     gap: 0.3rem;
//   }
  
//   kbd {
//     background-color: #f4f4f4;
//     border: 1px solid #ccc;
//     border-radius: 3px;
//     padding: 0.1em 0.4em;
//     font-family: monospace;
//     font-size: 0.9em;
//     box-shadow: 0 1px 0 rgba(0,0,0,0.2);
//   }
  
//   .lightbox {
//     outline: none;
//   }
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
  const imageRef = useRef(null);

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
        case '-':
        case '_':
        case 'ArrowUp':
          zoomIn();
          break;
        case '+':
        case '=':
        case 'ArrowDown':
          zoomOut();
          break;
        case '0':
        case 'Home':
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
    resetZoom();
    document.body.style.overflow = 'hidden';
    // Focus the lightbox for keyboard navigation
    setTimeout(() => lightboxRef.current?.focus(), 100);
  };

  const closeLightbox = () => {
    setCurrentImageIndex(null);
    document.body.style.overflow = 'auto';
  };

  const goToPrev = (e) => {
    e?.stopPropagation();
    setCurrentImageIndex((prev) => (prev === 0 ? images.length - 1 : prev - 1));
    resetZoom();
  };

  const goToNext = (e) => {
    e?.stopPropagation();
    setCurrentImageIndex((prev) => (prev === images.length - 1 ? 0 : prev + 1));
    resetZoom();
  };

  const zoomIn = (e) => {
    e?.stopPropagation();
    setZoomLevel((prev) => {
      const newZoom = Math.min(prev * 1.2, 4);
      // Adjust position to zoom toward center
      if (imageRef.current && newZoom !== prev) {
        const rect = imageRef.current.getBoundingClientRect();
        const centerX = window.innerWidth / 2;
        const centerY = window.innerHeight / 2;
        const imgCenterX = rect.left + rect.width / 2;
        const imgCenterY = rect.top + rect.height / 2;
        
        setPosition(prevPos => ({
          x: prevPos.x - (centerX - imgCenterX) * 0.2,
          y: prevPos.y - (centerY - imgCenterY) * 0.2
        }));
      }
      return newZoom;
    });
  };

  const zoomOut = (e) => {
    e?.stopPropagation();
    setZoomLevel((prev) => {
      const newZoom = Math.max(prev / 1.2, 0.2);
      if (newZoom === 1) {
        setPosition({ x: 0, y: 0 });
      }
      return newZoom;
    });
  };

  const resetZoom = () => {
    setZoomLevel(1);
    setPosition({ x: 0, y: 0 });
  };

  const handleMouseDown = (e) => {
    if (zoomLevel === 1) return;
    e.preventDefault();
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

  const handleWheel = (e) => {
    if (e.ctrlKey) {
      e.preventDefault();
      if (e.deltaY < 0) {
        zoomIn();
      } else {
        zoomOut();
      }
    }
  };

  return (
    <div className="app">
      <header className="app-header">
        <h1>Photo Gallery</h1>
        <p>Click on any image to view</p>
        <div className="keyboard-hints">
          <div><kbd>←</kbd> <kbd>→</kbd> Navigate</div>
          <div><kbd>+</kbd> <kbd>-</kbd> Zoom</div>
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
          onWheel={handleWheel}
        >
          <div className="lightbox-overlay" onClick={closeLightbox}></div>
          
          <div className="lightbox-content">
            <button 
              className="nav-btn prev-btn" 
              onClick={goToPrev}
              aria-label="Previous image"
            >
              <svg viewBox="0 0 24 24">
                <path d="M15.41 16.59L10.83 12l4.58-4.59L14 6l-6 6 6 6 1.41-1.41z"/>
              </svg>
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
                  cursor: zoomLevel > 1 ? (isDragging ? 'grabbing' : 'grab') : 'zoom-in'
                }}
                onClick={(e) => {
                  if (zoomLevel === 1) {
                    zoomIn(e);
                  } else {
                    resetZoom();
                  }
                }}
              />
            </div>
            
            <button 
              className="nav-btn next-btn" 
              onClick={goToNext}
              aria-label="Next image"
            >
              <svg viewBox="0 0 24 24">
                <path d="M8.59 16.59L13.17 12 8.59 7.41 10 6l6 6-6 6-1.41-1.41z"/>
              </svg>
            </button>
          </div>

          <div className="lightbox-controls">
            <div className="controls-group">
              <button 
                onClick={zoomOut} 
                title="Zoom Out (-)"
                aria-label="Zoom out"
              >
                <svg viewBox="0 0 24 24">
                  <path d="M19 13H5v-2h14v2z"/>
                </svg>
              </button>
              <button 
                onClick={resetZoom} 
                title="Reset Zoom (0)"
                aria-label="Reset zoom"
              >
                {Math.round(zoomLevel * 100)}%
              </button>
              <button 
                onClick={zoomIn} 
                title="Zoom In (+)"
                aria-label="Zoom in"
              >
                <svg viewBox="0 0 24 24">
                  <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
                </svg>
              </button>
            </div>
          </div>

          <div className="image-counter">
            {currentImageIndex + 1} / {images.length}
          </div>

          <button 
            className="close-btn" 
            onClick={closeLightbox} 
            title="Close (ESC)"
            aria-label="Close lightbox"
          >
            <svg viewBox="0 0 24 24">
              <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"/>
            </svg>
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
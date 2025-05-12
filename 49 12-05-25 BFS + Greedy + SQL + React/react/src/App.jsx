import React from 'react';
import Data from './components/Data';
import Greeting from './components/Greeting';
import Counter from './components/Counter';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
function App() {
  return (
    <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Greeting name="KMIT" />} />
          <Route path="/counter" element={<Counter />} />
          <Route path="/data" element={<Data />} />
        </Routes>
    </Router>
  )
}

export default App

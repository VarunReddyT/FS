import React from 'react';
import Display from './components/Display';
import Display2 from './components/Display2';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Navbar from './components/Navbar';
import Calculator from './components/Calculator';
import Form from './components/Form';
function App() {
  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Display />} />
        <Route path="/display2" element={<Display2 />} />
        <Route path="/calculator" element={<Calculator />} />
        <Route path="/form" element={<Form />} />
      </Routes>
    </Router>
  )
}

export default App

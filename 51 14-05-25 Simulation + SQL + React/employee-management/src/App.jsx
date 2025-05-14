import React from "react"
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import { useState } from "react"
import EmployeeForm from "./components/EmployeeForm.jsx"
import Login from "./components/Login.jsx"
import EmployeeList from "./components/EmployeeList.jsx"
import Home from "./components/Home.jsx"
import Navbar from "./components/Navbar.jsx"
import AdminPanel from "./components/AdminPanel.jsx"
import HRPanel from "./components/HRPanel.jsx"
function App() {
  const [employees, setEmployees] = useState([])
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/admin" element={<AdminPanel employees={employees} setEmployees={setEmployees}/>} />
        <Route path="/hr" element={<HRPanel employees={employees}/>}  />
        <Route path="/register" element={<EmployeeForm setEmployees={setEmployees} />} />
        <Route path="/login" element={<Login />} />
        <Route path="/employees" element={<EmployeeList employees={employees} />} />
      </Routes>
    </Router>
  )
}

export default App

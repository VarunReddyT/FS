import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import axios from 'axios'
import {jwtDecode} from 'jwt-decode'
import Login from './components/Login'
import Register from './components/Register'
import AdminDashboard from './components/AdminDashboard'
import UserChat from './components/UserChat'
import Navbar from './components/Navbar'

function App() {
  const [user, setUser] = useState(null)
  const [token, setToken] = useState(localStorage.getItem('token'))

  useEffect(() => {
    if (token) {
      const decoded = jwtDecode(token)
      setUser(decoded)
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    } else {
      delete axios.defaults.headers.common['Authorization']
    }
  }, [token])

  const login = (token) => {
    localStorage.setItem('token', token)
    setToken(token)
  }

  const logout = () => {
    localStorage.removeItem('token')
    setToken(null)
    setUser(null)
  }

  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <Navbar user={user} logout={logout} />
        
        <div className="container mx-auto p-4">
          <Routes>
            <Route path="/login" element={!user ? <Login login={login} /> : <Navigate to="/" />} />
            <Route path="/register" element={!user ? <Register login={login} /> : <Navigate to="/" />} />
            <Route 
              path="/admin" 
              element={user?.role === 'admin' ? <AdminDashboard /> : <Navigate to="/" />} 
            />
            <Route 
              path="/" 
              element={user ? (user.role === 'admin' ? <Navigate to="/admin" /> : <UserChat />) : <Navigate to="/login" />} 
            />
          </Routes>
        </div>
      </div>
    </Router>
  )
}

export default App;
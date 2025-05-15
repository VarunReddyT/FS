import React from 'react'
import { Link, useLocation } from 'react-router-dom'
import { useEffect, useState } from 'react'
export default function Navbar() {
    const path = useLocation();
    const [isLoggedIn, setIsLoggedIn] = useState(false)

    useEffect(() => {
        const userData = sessionStorage.getItem('user')
        if (userData) {
            setIsLoggedIn(true)
        }
    }, [path])

    const handleLogout = () => {
        sessionStorage.removeItem('user')
        setIsLoggedIn(false)
    }
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">EMS</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to="/">Home</Link>
                        </li>
                        {!isLoggedIn ? (
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">Login</Link>
                            </li>
                        ) : (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" type='button' onClick={handleLogout} to="/">Logout</Link>
                                </li>
                            </>
                        )
                        }
                        {!isLoggedIn && (
                            <li className="nav-item">
                                <Link className="nav-link" to="/register">Register</Link>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    )
}

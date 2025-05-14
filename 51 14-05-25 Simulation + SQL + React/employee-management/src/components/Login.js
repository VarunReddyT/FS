import React from 'react'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'
const users = [
    { username: 'admin', password: 'admin', role: 'admin' },
    { username: 'hr123', password: 'hr123', role: 'hr' },
]
export default function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const user = users.find(user => user.username === username && user.password === password);
        if (user) {
            sessionStorage.setItem('user', JSON.stringify(user));
            if (user.role === 'admin') {
                navigate('/adminpanel');
            } else if (user.role === 'hr') {
                navigate('/hrpanel');
            }
        } else {
            alert('Invalid credentials');
        }
    }
    return (
        <form onSubmit={handleSubmit} className="container mt-5">
            <h1 className="text-center">Login</h1>
            <div class="mb-3">
                <label for="exampleUsername" class="form-label">Username</label>
                <input type="email" class="form-control" id="exampleUsername" onChange={(e) => setUsername(e.target.value)} required/>
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" onChange={(e) => setPassword(e.target.value)} required/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    )
}

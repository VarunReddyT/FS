import { Link } from 'react-router-dom'

export default function Navbar({ user, logout }) {
  return (
    <nav className="bg-blue-600 text-white p-4 shadow-md">
      <div className="container mx-auto flex justify-between items-center">
        <Link to="/" className="text-xl font-bold">PDF Chatbot</Link>
        
        <div className="flex items-center space-x-4">
          {user && (
            <>
              {user.role === 'admin' && (
                <Link to="/admin" className="hover:underline">Admin Dashboard</Link>
              )}
              <button 
                onClick={logout}
                className="bg-red-500 hover:bg-red-600 px-4 py-2 rounded"
              >
                Logout
              </button>
            </>
          )}
          {!user && (
            <>
              <Link to="/login" className="hover:underline">Login</Link>
              <Link to="/register" className="hover:underline">Register</Link>
            </>
          )}
        </div>
      </div>
    </nav>
  )
}
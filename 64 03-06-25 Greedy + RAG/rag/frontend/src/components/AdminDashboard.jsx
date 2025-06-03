import { useState, useEffect } from 'react'
import axios from 'axios'

export default function AdminDashboard() {
  const [pdfs, setPdfs] = useState([])
  const [file, setFile] = useState(null)
  const [message, setMessage] = useState('')

  useEffect(() => {
    const fetchPdfs = async () => {
      try {
        const res = await axios.get('http://localhost:5000/api/pdfs')
        setPdfs(res.data)
      } catch (err) {
        console.error('Failed to fetch PDFs', err)
      }
    }
    fetchPdfs()
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!file) return

    const formData = new FormData()
    formData.append('pdf', file)

    try {
      const res = await axios.post('http://localhost:5000/api/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      setMessage('PDF uploaded successfully')
      setPdfs([...pdfs, res.data.pdf])
      setFile(null)
    } catch (err) {
      setMessage('Upload failed: ' + err.response?.data)
    }
  }

  return (
    <div className="max-w-4xl mx-auto mt-8">
      <h1 className="text-2xl font-bold mb-6">Admin Dashboard</h1>
      
      <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <h2 className="text-xl font-semibold mb-4">Upload PDF</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <input
              type="file"
              accept=".pdf"
              onChange={(e) => setFile(e.target.files[0])}
              className="block w-full text-sm text-gray-500
                file:mr-4 file:py-2 file:px-4
                file:rounded-md file:border-0
                file:text-sm file:font-semibold
                file:bg-blue-50 file:text-blue-700
                hover:file:bg-blue-100"
            />
          </div>
          <button
            type="submit"
            className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded"
            disabled={!file}
          >
            Upload
          </button>
        </form>
        {message && <p className="mt-4 text-green-600">{message}</p>}
      </div>
      
      <div className="bg-white p-6 rounded-lg shadow-md">
        <h2 className="text-xl font-semibold mb-4">Uploaded PDFs</h2>
        {pdfs.length === 0 ? (
          <p>No PDFs uploaded yet</p>
        ) : (
          <ul className="divide-y divide-gray-200">
            {pdfs.map((pdf) => (
              <li key={pdf._id} className="py-4">
                <div className="flex justify-between items-center">
                  <span className="text-gray-800">{pdf.filename}</span>
                  <span className="text-sm text-gray-500">
                    {new Date(pdf.uploadedAt).toLocaleDateString()}
                  </span>
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  )
}
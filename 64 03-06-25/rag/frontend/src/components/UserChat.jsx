import { useState, useEffect, useRef } from 'react'
import axios from 'axios'

export default function UserChat() {
  const [messages, setMessages] = useState([])
  const [input, setInput] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const messagesEndRef = useRef(null)

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' })
  }

  useEffect(() => {
    scrollToBottom()
  }, [messages])

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!input.trim()) return

    const userMessage = { text: input, sender: 'user' }
    setMessages([...messages, userMessage])
    setInput('')
    setIsLoading(true)

    try {
      const res = await axios.post('http://localhost:5000/api/chat', { question: input })
      const botMessage = { 
        text: res.data.answer, 
        sender: 'bot',
        sources: res.data.sources 
      }
      setMessages(prev => [...prev, botMessage])
    } catch (err) {
      const errorMessage = { 
        text: 'Failed to get response: ' + err.message, 
        sender: 'bot',
        isError: true
      }
      setMessages(prev => [...prev, errorMessage])
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <div className="max-w-4xl mx-auto mt-8">
      <h1 className="text-2xl font-bold mb-6">Chat with PDFs</h1>
      
      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <div className="h-96 p-4 overflow-y-auto bg-gray-50">
          {messages.length === 0 ? (
            <div className="flex items-center justify-center h-full">
              <p className="text-gray-500">Ask a question about the uploaded PDFs</p>
            </div>
          ) : (
            <div className="space-y-4">
              {messages.map((msg, index) => (
                <div 
                  key={index} 
                  className={`flex ${msg.sender === 'user' ? 'justify-end' : 'justify-start'}`}
                >
                  <div 
                    className={`max-w-xs md:max-w-md lg:max-w-lg rounded-lg px-4 py-2 ${
                      msg.sender === 'user' 
                        ? 'bg-blue-500 text-white' 
                        : msg.isError 
                          ? 'bg-red-100 text-red-800' 
                          : 'bg-gray-200 text-gray-800'
                    }`}
                  >
                    <p>{msg.text}</p>
                    {msg.sources && msg.sender === 'bot' && !msg.isError && (
                      <p className="text-xs mt-2 text-gray-600">
                        Sources: {msg.sources.join(', ')}
                      </p>
                    )}
                  </div>
                </div>
              ))}
              {isLoading && (
                <div className="flex justify-start">
                  <div className="bg-gray-200 text-gray-800 rounded-lg px-4 py-2">
                    <p>Thinking...</p>
                  </div>
                </div>
              )}
              <div ref={messagesEndRef} />
            </div>
          )}
        </div>
        
        <form onSubmit={handleSubmit} className="p-4 border-t">
          <div className="flex">
            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Type your question..."
              className="flex-1 p-2 border rounded-l focus:outline-none focus:ring-2 focus:ring-blue-500"
              disabled={isLoading}
            />
            <button
              type="submit"
              className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-r disabled:bg-blue-300"
              disabled={isLoading || !input.trim()}
            >
              Send
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}
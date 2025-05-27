import axios from "axios"

const api = axios.create({
  baseURL: "http://localhost:4000",
  headers: {
    "Content-Type": "application/json",
  },
})

// Invoice API functions
export const getInvoices = async () => {
  const response = await api.get("/invoices")
  return response.data
}

export const getInvoice = async (id) => {
  const response = await api.get(`/invoices/${id}`)
  return response.data
}

export const createInvoice = async (invoiceData) => {
  const response = await api.post("/invoices", invoiceData)
  return response.data
}

export const updateInvoice = async (id, invoiceData) => {
  const response = await api.put(`/invoices/${id}`, invoiceData)
  return response.data
}

export const deleteInvoice = async (id) => {
  const response = await api.delete(`/invoices/${id}`)
  return response.data
}

export const getNextInvoiceNumber = async () => {
  const response = await api.get("/inv/next-number")
  return response.data
}

export default api

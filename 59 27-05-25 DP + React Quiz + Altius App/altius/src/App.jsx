import { Routes, Route } from "react-router-dom"
import { Box } from "@mui/material"
import Layout from "./components/Layout"
import InvoiceList from "./components/InvoiceList"
import InvoiceDetail from "./components/InvoiceDetail"

function App() {
  return (
    <Box sx={{ display: "flex" }}>
      <Layout>
        <Routes>
          <Route path="/" element={<InvoiceList />} />
          <Route path="/invoices" element={<InvoiceList />} />
          <Route path="/invoices/new" element={<InvoiceDetail />} />
          <Route path="/invoices/:id" element={<InvoiceDetail />} />
        </Routes>
      </Layout>
    </Box>
  )
}

export default App

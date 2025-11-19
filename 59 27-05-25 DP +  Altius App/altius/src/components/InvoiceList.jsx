"use client"

import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { Box, Button, Paper, Typography, Alert, CircularProgress } from "@mui/material"
import { DataGrid } from "@mui/x-data-grid"
import { Add as AddIcon } from "@mui/icons-material"
import { getInvoices } from "../services/api"

function InvoiceList() {
  const [invoices, setInvoices] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const navigate = useNavigate()

  useEffect(() => {
    fetchInvoices()
  }, [])

  const fetchInvoices = async () => {
    try {
      setLoading(true)
      const data = await getInvoices()
      setInvoices(data)
      setError(null)
    } catch (err) {
      setError("Failed to fetch invoices")
      console.error("Error fetching invoices:", err)
    } finally {
      setLoading(false)
    }
  }

  const columns = [
    {
      field: "invoiceNumber",
      headerName: "Invoice #",
      width: 120,
      type: "number",
    },
    {
      field: "date",
      headerName: "Date",
      width: 120,
      
    },
    {
      field: "customerName",
      headerName: "Customer Name",
      width: 200,
    },
    {
      field: "GSTIN",
      headerName: "GSTIN",
      width: 150,
    },
    {
      field: "totalAmount",
      headerName: "Total Amount",
      width: 130,
      type: "number",
      valueFormatter: (params) => {
        return `₹${params.value.toFixed(2)}`
      },
    },
    {
      field: "createdAt",
      headerName: "Created At",
      width: 150,
      valueFormatter: (params) => {
        return new Date(params.value).toLocaleDateString()
      },
    },
  ]

  const handleRowClick = (params) => {
    navigate(`/invoices/${params.row.id}`)
  }

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
        <CircularProgress />
      </Box>
    )
  }

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4" component="h1">
          Invoices
        </Typography>
        <Button variant="contained" startIcon={<AddIcon />} onClick={() => navigate("/invoices/new")}>
          Add Invoice
        </Button>
      </Box>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      )}

      <Paper sx={{ height: 600, width: "100%" }}>
        <DataGrid
          rows={invoices}
          columns={columns}
          pageSize={10}
          rowsPerPageOptions={[5, 10, 20]}
          onRowClick={handleRowClick}
          sx={{
            "& .MuiDataGrid-row:hover": {
              cursor: "pointer",
            },
          }}
          disableSelectionOnClick
        />
      </Paper>
    </Box>
  )
}

export default InvoiceList

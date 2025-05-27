"use client"

import { useState, useEffect } from "react"
import { useParams, useNavigate } from "react-router-dom"
import {
  Box,
  Button,
  Card,
  CardContent,
  Grid,
  TextField,
  Typography,
  Alert,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  CircularProgress,
} from "@mui/material"
import {
  Save as SaveIcon,
  Cancel as CancelIcon,
  Delete as DeleteIcon,
  Add as AddIcon,
  Remove as RemoveIcon,
} from "@mui/icons-material"
import { getInvoice, createInvoice, updateInvoice, deleteInvoice, getNextInvoiceNumber } from "../services/api"

function InvoiceDetail() {
  const { id } = useParams()
  const navigate = useNavigate()
  const isEditMode = Boolean(id)

  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [deleteDialogOpen, setDeleteDialogOpen] = useState(false)
  const [nextInvoiceNumber, setNextInvoiceNumber] = useState(null)

  const [formData, setFormData] = useState({
    date: new Date().toISOString().split("T")[0],
    customerName: "",
    billingAddress: "",
    shippingAddress: "",
    GSTIN: "",
    items: [{ itemName: "", quantity: 1, price: 0, amount: 0 }],
    billSundrys: [],
    totalAmount: 0,
    invoiceNumber: "",
  })

  useEffect(() => {
    if (isEditMode) {
      fetchInvoice()
    } else {
      fetchNextInvoiceNumber()
    }
  }, [id, isEditMode])

  useEffect(() => {
    calculateTotalAmount()
  }, [formData.items, formData.billSundrys])

  const fetchNextInvoiceNumber = async () => {
    try {
      const data = await getNextInvoiceNumber()
      setNextInvoiceNumber(data.nextInvoiceNumber)
      setFormData((prev) => ({ ...prev, invoiceNumber: data.nextInvoiceNumber }))
    } catch (err) {
      setError("Failed to fetch next invoice number")
    }
  }

  const fetchInvoice = async () => {
    try {
      setLoading(true)
      const data = await getInvoice(id)
      setFormData({
        date: data.date,
        customerName: data.customerName,
        billingAddress: data.billingAddress,
        shippingAddress: data.shippingAddress,
        GSTIN: data.GSTIN,
        items: data.Items || [],
        billSundrys: data.BillSundrys || [],
        totalAmount: data.totalAmount,
        invoiceNumber: data.invoiceNumber,
      })
      setError(null)
    } catch (err) {
      setError("Failed to fetch invoice")
      console.error("Error fetching invoice:", err)
    } finally {
      setLoading(false)
    }
  }

  const calculateTotalAmount = () => {
    const itemsTotal = formData.items.reduce((sum, item) => sum + (Number(item.amount) || 0), 0)
    const sundriesTotal = formData.billSundrys.reduce((sum, sundry) => sum + (Number(sundry.amount) || 0), 0)
    const total = itemsTotal + sundriesTotal
    setFormData((prev) => ({
      ...prev,
      totalAmount: total,
    }))
  }

  const handleInputChange = (field, value) => {
    setFormData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleItemChange = (index, field, value) => {
    const updatedItems = [...formData.items]
    updatedItems[index] = {
      ...updatedItems[index],
      [field]: value,
    }
    if (field === "quantity" || field === "price") {
      const quantity = field === "quantity" ? Number(value) || 0 : Number(updatedItems[index].quantity) || 0
      const price = field === "price" ? Number(value) || 0 : Number(updatedItems[index].price) || 0
      updatedItems[index].amount = quantity * price
    }
    setFormData((prev) => ({
      ...prev,
      items: updatedItems,
    }))
  }

  const handleBillSundryChange = (index, field, value) => {
    const updatedSundries = [...formData.billSundrys]
    updatedSundries[index] = {
      ...updatedSundries[index],
      [field]: value,
    }
    setFormData((prev) => ({
      ...prev,
      billSundrys: updatedSundries,
    }))
  }

  const addItem = () => {
    setFormData((prev) => ({
      ...prev,
      items: [...prev.items, { itemName: "", quantity: 1, price: 0, amount: 0 }],
    }))
  }

  const removeItem = (index) => {
    if (formData.items.length > 1) {
      const updatedItems = formData.items.filter((_, i) => i !== index)
      setFormData((prev) => ({
        ...prev,
        items: updatedItems,
      }))
    }
  }

  const addBillSundry = () => {
    setFormData((prev) => ({
      ...prev,
      billSundrys: [...prev.billSundrys, { billSundryName: "", amount: 0 }],
    }))
  }

  const removeBillSundry = (index) => {
    const updatedSundries = formData.billSundrys.filter((_, i) => i !== index)
    setFormData((prev) => ({
      ...prev,
      billSundrys: updatedSundries,
    }))
  }

  // Validation helpers
  const isBackdated = (dateStr) => {
    const today = new Date()
    const inputDate = new Date(dateStr)
    today.setHours(0, 0, 0, 0)
    inputDate.setHours(0, 0, 0, 0)
    return inputDate < today
  }

  const validateForm = () => {
    const errors = []
    if (!formData.date) errors.push("Date is required")
    if (isBackdated(formData.date)) errors.push("Backdated entries are not allowed")
    if (!formData.customerName) errors.push("Customer Name is required")
    if (!formData.billingAddress) errors.push("Billing Address is required")
    if (!formData.shippingAddress) errors.push("Shipping Address is required")
    if (!formData.GSTIN) errors.push("GSTIN is required")
    if (!formData.items || formData.items.length === 0) errors.push("At least one item is required")
    formData.items.forEach((item, idx) => {
      if (!item.itemName) errors.push(`Item #${idx + 1}: Name is required`)
      if (Number(item.price) <= 0) errors.push(`Item #${idx + 1}: Price must be > 0`)
      if (Number(item.quantity) <= 0) errors.push(`Item #${idx + 1}: Quantity must be > 0`)
    })
    return errors
  }

  // Map frontend data to backend expected structure
  const mapToBackend = (data) => ({
    Date: data.date,
    CustomerName: data.customerName,
    BillingAddress: data.billingAddress,
    ShippingAddress: data.shippingAddress,
    GSTIN: data.GSTIN,
    Items: data.items.map((i) => ({
      itemName: i.itemName,
      quantity: Number(i.quantity),
      price: Number(i.price),
      amount: Number(i.amount),
    })),
    BillSundrys: data.billSundrys.map((b) => ({
      billSundryName: b.billSundryName,
      amount: Number(b.amount),
    })),
    TotalAmount: data.totalAmount,
  })

  const handleSubmit = async (e) => {
    e.preventDefault()
    const validationErrors = validateForm()
    if (validationErrors.length > 0) {
      setError(validationErrors.join(", "))
      return
    }
    try {
      setLoading(true)
      setError(null)
      if (isEditMode) {
        await updateInvoice(id, mapToBackend(formData))
      } else {
        await createInvoice(mapToBackend(formData))
      }
      navigate("/invoices")
    } catch (err) {
      const backendErrors = err.response?.data?.errors
      setError(
        Array.isArray(backendErrors)
          ? backendErrors.join(", ")
          : err.response?.data?.message || "Failed to save invoice"
      )
      console.error("Error saving invoice:", err)
    } finally {
      setLoading(false)
    }
  }

  const handleDelete = async () => {
    try {
      setLoading(true)
      await deleteInvoice(id)
      navigate("/invoices")
    } catch (err) {
      setError("Failed to delete invoice")
      console.error("Error deleting invoice:", err)
    } finally {
      setLoading(false)
      setDeleteDialogOpen(false)
    }
  }

  if (loading && isEditMode && !formData.customerName) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
        <CircularProgress />
      </Box>
    )
  }

  return (
    <Box>
      <Typography variant="h4" component="h1" gutterBottom>
        {isEditMode
          ? `Edit Invoice #${formData.invoiceNumber}`
          : "Create New Invoice"}
      </Typography>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      )}

      <form onSubmit={handleSubmit}>
        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Invoice Details
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Date"
                  type="date"
                  value={formData.date}
                  onChange={(e) => handleInputChange("date", e.target.value)}
                  required
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Customer Name"
                  value={formData.customerName}
                  onChange={(e) => handleInputChange("customerName", e.target.value)}
                  required
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Billing Address"
                  multiline
                  rows={3}
                  value={formData.billingAddress}
                  onChange={(e) => handleInputChange("billingAddress", e.target.value)}
                  required
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Shipping Address"
                  multiline
                  rows={3}
                  value={formData.shippingAddress}
                  onChange={(e) => handleInputChange("shippingAddress", e.target.value)}
                  required
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="GSTIN"
                  value={formData.GSTIN}
                  onChange={(e) => handleInputChange("GSTIN", e.target.value)}
                  required
                />
              </Grid>
            </Grid>
          </CardContent>
        </Card>

        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
              <Typography variant="h6">Items</Typography>
              <Button variant="outlined" startIcon={<AddIcon />} onClick={addItem}>
                Add Item
              </Button>
            </Box>

            {formData.items.map((item, index) => (
              <Box key={index} sx={{ mb: 2, p: 2, border: "1px solid #e0e0e0", borderRadius: 1 }}>
                <Grid container spacing={2} alignItems="center">
                  <Grid item xs={12} sm={3}>
                    <TextField
                      fullWidth
                      label="Item Name"
                      value={item.itemName}
                      onChange={(e) => handleItemChange(index, "itemName", e.target.value)}
                      required
                    />
                  </Grid>
                  <Grid item xs={12} sm={2}>
                    <TextField
                      fullWidth
                      label="Quantity"
                      type="number"
                      value={item.quantity}
                      onChange={(e) => handleItemChange(index, "quantity", e.target.value)}
                      required
                      inputProps={{ min: 0.01, step: 0.01 }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={2}>
                    <TextField
                      fullWidth
                      label="Price"
                      type="number"
                      value={item.price}
                      onChange={(e) => handleItemChange(index, "price", e.target.value)}
                      required
                      inputProps={{ min: 0.01, step: 0.01 }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={2}>
                    <TextField
                      fullWidth
                      label="Amount"
                      type="number"
                      value={item.amount}
                      InputProps={{ readOnly: true }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={1}>
                    <Button color="error" onClick={() => removeItem(index)} disabled={formData.items.length === 1}>
                      <RemoveIcon />
                    </Button>
                  </Grid>
                </Grid>
              </Box>
            ))}
          </CardContent>
        </Card>

        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
              <Typography variant="h6">Bill Sundries</Typography>
              <Button variant="outlined" startIcon={<AddIcon />} onClick={addBillSundry}>
                Add Bill Sundry
              </Button>
            </Box>

            {formData.billSundrys.map((sundry, index) => (
              <Box key={index} sx={{ mb: 2, p: 2, border: "1px solid #e0e0e0", borderRadius: 1 }}>
                <Grid container spacing={2} alignItems="center">
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      label="Bill Sundry Name"
                      value={sundry.billSundryName}
                      onChange={(e) => handleBillSundryChange(index, "billSundryName", e.target.value)}
                      required
                    />
                  </Grid>
                  <Grid item xs={12} sm={4}>
                    <TextField
                      fullWidth
                      label="Amount"
                      type="number"
                      value={sundry.amount}
                      onChange={(e) => handleBillSundryChange(index, "amount", e.target.value)}
                      required
                      inputProps={{ step: 0.01 }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={2}>
                    <Button color="error" onClick={() => removeBillSundry(index)}>
                      <RemoveIcon />
                    </Button>
                  </Grid>
                </Grid>
              </Box>
            ))}
          </CardContent>
        </Card>

        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Total Amount: ₹{formData.totalAmount}
            </Typography>
          </CardContent>
        </Card>

        <Box display="flex" gap={2}>
          <Button type="submit" variant="contained" startIcon={<SaveIcon />} disabled={loading}>
            {loading ? "Saving..." : "Save"}
          </Button>

          <Button variant="outlined" startIcon={<CancelIcon />} onClick={() => navigate("/invoices")}>
            Cancel
          </Button>

          {isEditMode && (
            <Button
              variant="contained"
              color="error"
              startIcon={<DeleteIcon />}
              onClick={() => setDeleteDialogOpen(true)}
            >
              Delete
            </Button>
          )}
        </Box>
      </form>

      <Dialog open={deleteDialogOpen} onClose={() => setDeleteDialogOpen(false)}>
        <DialogTitle>Delete Invoice</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to delete this invoice? This action cannot be undone.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setDeleteDialogOpen(false)}>Cancel</Button>
          <Button onClick={handleDelete} color="error" autoFocus>
            Delete
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  )
}

export default InvoiceDetail

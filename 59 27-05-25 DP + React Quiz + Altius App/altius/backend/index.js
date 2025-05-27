const express = require("express")
const cors = require("cors")
const mongoose = require("mongoose")
const invoiceRoutes = require("./routes/invoiceRoutes")
const InvoiceHeader = require("./schema/InvoiceHeader")

const app = express()
const PORT = process.env.PORT || 4000

app.use(cors())
app.use(express.json())

// Connect to MongoDB
mongoose.connect("mongodb://127.0.0.1:27017/altius", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
.then(() => console.log("MongoDB connected"))
.catch((err) => console.error("MongoDB connection error:", err))

// Health check
app.get("/api/health", (req, res) => {
  res.json({ status: "ok" })
})

// Invoice API
app.use("/invoices", invoiceRoutes)

// Endpoint for next invoice number
app.get("/inv/next-number", async (req, res) => {
  try {
    const lastInvoice = await InvoiceHeader.findOne().sort({ invoiceNumber: -1 })
    const nextInvoiceNumber = lastInvoice ? lastInvoice.invoiceNumber + 1 : 1
    res.json({ nextInvoiceNumber })
  } catch (err) {
  console.error("Error fetching next invoice number:", err)
    res.status(500).json({ message: "Failed to fetch next invoice number" })
  }
})

// Start server
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`)
})

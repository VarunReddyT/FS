const mongoose = require('mongoose');
const { v4: uuidv4 } = require('uuid');
const Counter = require('./Counter');

const InvoiceHeaderSchema = new mongoose.Schema({
  id: {
    type: String,
    default: uuidv4,
    unique: true
  },
  date: {
    type: String,
    required: true
  },
  invoiceNumber: {
    type: Number,
    unique: true
  },
  customerName: {
    type: String,
    required: true
  },
  billingAddress: {
    type: String,
    required: true
  },
  shippingAddress: {
    type: String,
    required: true
  },
  GSTIN: {
    type: String,
    required: true
  },
  totalAmount: {
    type: Number,
    required: true
  }
}, {
  timestamps: true
});

InvoiceHeaderSchema.pre('save', async function (next) {
  const doc = this;
  if (doc.isNew) {
    const counter = await Counter.findByIdAndUpdate(
      { _id: 'invoiceNumber' },
      { $inc: { seq: 1 } },
      { new: true, upsert: true }
    );
    doc.invoiceNumber = counter.seq;
  }
  next();
});

module.exports = mongoose.model('InvoiceHeader', InvoiceHeaderSchema);

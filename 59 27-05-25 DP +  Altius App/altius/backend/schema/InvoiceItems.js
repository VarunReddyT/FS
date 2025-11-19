const mongoose = require('mongoose');
const { v4: uuidv4 } = require('uuid');

const InvoiceItemSchema = new mongoose.Schema({
  id: {
    type: String,
    default: uuidv4,
    unique: true
  },
  invoiceId: {
    type: String,
    required: true,
    ref: 'InvoiceHeader'
  },
  itemName: {
    type: String,
    required: true
  },
  quantity: {
    type: Number,
    required: true
  },
  price: {
    type: Number,
    required: true
  },
  amount: {
    type: Number,
    required: true
  }
}, {
  timestamps: true
});

module.exports = mongoose.model('InvoiceItem', InvoiceItemSchema);

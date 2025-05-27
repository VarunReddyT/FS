const mongoose = require('mongoose');

const InvoiceBillSundrySchema = new mongoose.Schema({
  invoiceId: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'InvoiceHeader',
    required: true
  },
  billSundryName: {
    type: String,
    required: true
  },
  amount: {
    type: Number,
    required: true
  }
});

module.exports = mongoose.model('InvoiceBillSundry', InvoiceBillSundrySchema);
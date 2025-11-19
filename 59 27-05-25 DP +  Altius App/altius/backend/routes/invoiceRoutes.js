const express = require('express');
const router = express.Router();
const InvoiceHeader = require('../schema/InvoiceHeader');
const InvoiceItems = require('../schema/InvoiceItems');
const InvoiceBillSundry = require('../schema/InvoiceBillSundry');

// Helper: Validate date is not backdated
function isBackdated(dateStr) {
  const today = new Date();
  const inputDate = new Date(dateStr);
  today.setHours(0, 0, 0, 0);
  inputDate.setHours(0, 0, 0, 0);
  return inputDate < today;
}

// Get all invoices
router.get('/', async (req, res) => {
  try {
    const invoices = await InvoiceHeader.find().sort({ invoiceNumber: -1 });
    res.json(invoices);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Get single invoice
router.get('/:id', async (req, res) => {
  try {
    const invoice = await InvoiceHeader.findOne({ id: req.params.id });
    if (!invoice) return res.status(404).json({ message: 'Invoice not found' });

    const invoiceIdStr = invoice._id.toString();

    const items = await InvoiceItems.find({ invoiceId: invoiceIdStr });
    const billSundries = await InvoiceBillSundry.find({ invoiceId: invoiceIdStr });

    // Convert documents to plain objects
    const invoiceObj = invoice.toObject();
    invoiceObj.Items = items.map(item => item.toObject());
    invoiceObj.BillSundrys = billSundries.map(bs => bs.toObject());

    // Optionally: Clean _id/ObjectId/Decimal128 manually if needed
    invoiceObj._id = invoiceObj._id.toString();
    invoiceObj.Items = invoiceObj.Items.map(item => ({
      ...item,
      _id: item._id.toString(),
      invoiceId: item.invoiceId.toString(),
      quantity: parseFloat(item.quantity.toString()),
      price: parseFloat(item.price.toString()),
      amount: parseFloat(item.amount.toString())
    }));
    invoiceObj.BillSundrys = invoiceObj.BillSundrys.map(bs => ({
      ...bs,
      _id: bs._id.toString(),
      invoiceId: bs.invoiceId.toString(),
      amount: parseFloat(bs.amount.toString())
    }));

    res.json(invoiceObj);

  } catch (err) {
    console.error(err);
    res.status(500).json({ message: err.message });
  }
});

// Create invoice
router.post('/', async (req, res) => {
  const errors = [];
  try {
    // Get the next invoice number
    const lastInvoice = await InvoiceHeader.findOne().sort({ invoiceNumber: -1 });
    const nextInvoiceNumber = lastInvoice ? lastInvoice.invoiceNumber + 1 : 1;

    // Validate required fields
    if (!req.body.Date) errors.push('Date is required');
    if (isBackdated(req.body.Date)) errors.push('Backdated entries are not allowed');
    if (!req.body.CustomerName) errors.push('Customer Name is required');
    if (!req.body.BillingAddress) errors.push('Billing Address is required');
    if (!req.body.ShippingAddress) errors.push('Shipping Address is required');
    if (!req.body.GSTIN) errors.push('GSTIN is required');
    if (!req.body.Items || req.body.Items.length === 0) errors.push('At least one item is required');

    let itemsAmount = 0;
    if (req.body.Items) {
      req.body.Items.forEach((item, idx) => {
        if (!item.itemName) errors.push(`Item #${idx + 1}: Name is required`);
        if (item.price <= 0) errors.push(`Item #${idx + 1}: Price must be > 0`);
        if (item.quantity <= 0) errors.push(`Item #${idx + 1}: Quantity must be > 0`);
        item.amount = item.quantity * item.price;
        itemsAmount += item.amount;
      });
    }

    let sundriesAmount = 0;
    if (req.body.BillSundrys && req.body.BillSundrys.length > 0) {
      for (const sundry of req.body.BillSundrys) {
        sundriesAmount += Number(sundry.amount);
      }
    }

    const totalAmount = itemsAmount + sundriesAmount;

    if (errors.length > 0) {
      return res.status(400).json({ errors });
    }

    // Create invoice
    const invoice = new InvoiceHeader({
      invoiceNumber: nextInvoiceNumber,
      date: req.body.Date,
      customerName: req.body.CustomerName,
      billingAddress: req.body.BillingAddress,
      shippingAddress: req.body.ShippingAddress,
      GSTIN: req.body.GSTIN,
      totalAmount: totalAmount
    });

    const savedInvoice = await invoice.save();

    // Save items
    const items = req.body.Items.map(item => ({
      invoiceId: savedInvoice._id,
      itemName: item.itemName,
      quantity: item.quantity,
      price: item.price,
      amount: item.amount
    }));
    await InvoiceItems.insertMany(items);

    // Save bill sundries if any
    if (req.body.BillSundrys && req.body.BillSundrys.length > 0) {
      const sundries = req.body.BillSundrys.map(sundry => ({
        invoiceId: savedInvoice._id,
        billSundryName: sundry.billSundryName,
        amount: sundry.amount
      }));
      await InvoiceBillSundry.insertMany(sundries);
    }

    res.status(201).json(savedInvoice);
  } catch (err) {
    res.status(400).json({ errors: errors.length ? errors : [err.message] });
  }
});

// Update invoice
router.put('/:id', async (req, res) => {
  const errors = [];
  try {
    const invoice = await InvoiceHeader.findOne({ id: req.params.id });
    if (!invoice) return res.status(404).json({ errors: ['Invoice not found'] });

    // Validate required fields
    if (!req.body.Date) errors.push('Date is required');
    if (isBackdated(req.body.Date)) errors.push('Backdated entries are not allowed');
    if (!req.body.CustomerName) errors.push('Customer Name is required');
    if (!req.body.BillingAddress) errors.push('Billing Address is required');
    if (!req.body.ShippingAddress) errors.push('Shipping Address is required');
    if (!req.body.GSTIN) errors.push('GSTIN is required');
    if (!req.body.Items || req.body.Items.length === 0) errors.push('At least one item is required');

    let itemsAmount = 0;
    if (req.body.Items) {
      req.body.Items.forEach((item, idx) => {
        if (!item.itemName) errors.push(`Item #${idx + 1}: Name is required`);
        if (item.price <= 0) errors.push(`Item #${idx + 1}: Price must be > 0`);
        if (item.quantity <= 0) errors.push(`Item #${idx + 1}: Quantity must be > 0`);
        item.amount = item.quantity * item.price;
        itemsAmount += item.amount;
      });
    }

    let sundriesAmount = 0;
    if (req.body.BillSundrys && req.body.BillSundrys.length > 0) {
      for (const sundry of req.body.BillSundrys) {
        sundriesAmount += Number(sundry.amount);
      }
    }

    const totalAmount = itemsAmount + sundriesAmount;

    if (errors.length > 0) {
      return res.status(400).json({ errors });
    }

    // Update invoice
    invoice.date = req.body.Date;
    invoice.customerName = req.body.CustomerName;
    invoice.billingAddress = req.body.BillingAddress;
    invoice.shippingAddress = req.body.ShippingAddress;
    invoice.GSTIN = req.body.GSTIN;
    invoice.totalAmount = totalAmount;

    const updatedInvoice = await invoice.save();

    // Delete existing items and sundries
    await InvoiceItems.deleteMany({ invoiceId: invoice._id });
    await InvoiceBillSundry.deleteMany({ invoiceId: invoice._id });

    // Save new items
    const items = req.body.Items.map(item => ({
      invoiceId: updatedInvoice._id,
      itemName: item.itemName,
      quantity: item.quantity,
      price: item.price,
      amount: item.amount
    }));
    await InvoiceItems.insertMany(items);

    // Save new bill sundries if any
    if (req.body.BillSundrys && req.body.BillSundrys.length > 0) {
      const sundries = req.body.BillSundrys.map(sundry => ({
        invoiceId: updatedInvoice._id,
        billSundryName: sundry.billSundryName,
        amount: sundry.amount
      }));
      await InvoiceBillSundry.insertMany(sundries);
    }

    res.json(updatedInvoice);
  } catch (err) {
    res.status(400).json({ errors: errors.length ? errors : [err.message] });
  }
});

// Delete invoice
router.delete('/:id', async (req, res) => {
  try {
    const invoice = await InvoiceHeader.findOne({ id: req.params.id });
    if (!invoice) return res.status(404).json({ message: 'Invoice not found' });

    await InvoiceItems.deleteMany({ invoiceId: invoice._id });
    await InvoiceBillSundry.deleteMany({ invoiceId: invoice._id });
    await invoice.remove();

    res.json({ message: 'Invoice deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});



module.exports = router;
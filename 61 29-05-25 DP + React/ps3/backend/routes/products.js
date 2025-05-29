const express = require('express');
const { getProducts, searchProducts } = require('../controllers/products.js');
const router = express.Router();

// GET /api/products - Get all products with pagination
router.get('/', getProducts);

// GET /api/products/search - Search products by name
router.get('/search', searchProducts);

module.exports = router;
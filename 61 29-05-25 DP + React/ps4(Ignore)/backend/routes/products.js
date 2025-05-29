const express = require('express');
const { getProducts, searchProducts, updateProduct, deleteProduct } = require('../controllers/products.js');
const router = express.Router();

// GET /api/products - Get all products with pagination
router.get('/', getProducts);

// GET /api/products/search - Search products by name
router.get('/search', searchProducts);

// PUT /api/products/:id - Update a product
router.put('/:id', updateProduct);

// DELETE /api/products/:id - Delete a product
router.delete('/:id', deleteProduct);

module.exports = router;
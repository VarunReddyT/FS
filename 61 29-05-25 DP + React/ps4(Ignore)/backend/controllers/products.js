const Product = require('../models/Product');

// Get all products with pagination and search
const getProducts = async (req, res) => {
  try {
    const { name = '', page = 1, limit = 10 } = req.query;
    
    const query = {};
    if (name) {
      query.name = { $regex: name, $options: 'i' };
    }

    const options = {
      page: parseInt(page),
      limit: parseInt(limit),
      sort: { createdAt: -1 },
    };

    // Use the paginate method provided by mongoose-paginate-v2
    const products = await Product.paginate(query, options);
    
    res.json({
      products: products.docs,
      totalProducts: products.totalDocs,
      totalPages: products.totalPages,
      currentPage: products.page,
    });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

// Search products by name
const searchProducts = async (req, res) => {
  try {
    const { name } = req.query;
    const products = await Product.find({
      name: { $regex: name, $options: 'i' },
    }).limit(10);
    res.json(products);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

// Update product by ID
const updateProduct = async (req, res) => {
  try {
    const { id } = req.params;
    const updatedProduct = await Product.findByIdAndUpdate(id, req.body, {
      new: true,
    });
    res.json(updatedProduct);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

// Delete product by ID
const deleteProduct = async (req, res) => {
  try {
    const { id } = req.params;
    await Product.findByIdAndDelete(id);
    res.json({ message: 'Product deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

module.exports = {
  getProducts,
  searchProducts,
  updateProduct,
  deleteProduct
};
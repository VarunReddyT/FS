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

module.exports = {
  getProducts,
  searchProducts,
};
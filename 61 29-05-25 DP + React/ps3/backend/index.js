const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
require('dotenv').config();
const connectDB = require('./config/db.js');
const productRoutes = require('./routes/products.js');

dotenv.config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Database connection
connectDB();

// Routes
app.use('/api/products', productRoutes);

const PORT = 4000;

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
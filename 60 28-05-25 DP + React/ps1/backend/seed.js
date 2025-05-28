require('dotenv').config();
const mongoose = require('mongoose');
const Product = require('./models/Product');
const { faker } = require('@faker-js/faker');

// Database connection
mongoose.connect(process.env.MONGODB_URI)
  .then(() => {
    console.log('Connected to MongoDB for seeding');
    seedDatabase();
  })
  .catch((error) => {
    console.error('Error connecting to MongoDB:', error.message);
  });

async function seedDatabase() {
  try {
    // Clear existing products
    await Product.deleteMany({});
    console.log('Cleared existing products');

    // Generate 100 sample products
    const categories = ['Electronics', 'Clothing', 'Home', 'Books', 'Toys'];
    const products = [];

    for (let i = 0; i < 100; i++) {
      products.push({
        name: faker.commerce.productName(),
        price: parseFloat(faker.commerce.price(10, 1000, 2)),
        category: categories[Math.floor(Math.random() * categories.length)],
        inStock: faker.datatype.boolean()
      });
    }

    // Insert products
    await Product.insertMany(products);
    console.log('Seeded 100 products successfully');

    // Close connection
    mongoose.connection.close();
  } catch (error) {
    console.error('Error seeding database:', error);
    mongoose.connection.close();
  }
}
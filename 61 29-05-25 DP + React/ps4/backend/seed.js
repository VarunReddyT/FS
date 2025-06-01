require('dotenv').config();
const mongoose = require('mongoose');
const Product = require('./models/Product');

const categories = ['Electronics', 'Clothing', 'Books', 'Home', 'Toys'];

function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

async function seedProducts() {
  await mongoose.connect(process.env.MONGO_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });

  await Product.deleteMany({});

  const products = Array.from({ length: 100 }, (_, i) => ({
    name: `Product ${i + 1}`,
    price: getRandomInt(10, 1000),
    category: categories[getRandomInt(0, categories.length - 1)],
    inStock: Math.random() > 0.3,
  }));

  await Product.insertMany(products);
  console.log('Seeded 100 products');
  mongoose.disconnect();
}

seedProducts();

const mongoose = require('mongoose');
const connectDB = async () => {
  try {
    await mongoose.connect("mongodb://localhost:27017/fs", {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
    console.log('MongoDB Connected...');
  } catch (err) {
    console.error(err.message);
    process.exit(1);
  }
};

module.exports = connectDB;
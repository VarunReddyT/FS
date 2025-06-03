require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const cors = require('cors');
const multer = require('multer');
const path = require('path');
const fs = require('fs');
const FormData = require('form-data');
const axios = require('axios');

const app = express();
app.use(express.json());
app.use(cors());

const JWT_SECRET = 'varun';

// MongoDB connection
mongoose.connect('mongodb://127.0.0.1:27017/pdf-chatbot')
  .then(() => console.log('MongoDB connected'))
  .catch(err => console.error('MongoDB connection error:', err));

// Models
const User = require('./models/User');
const PDF = require('./models/PDF');

// Middleware for authentication
const authenticate = (req, res, next) => {
  const token = req.header('Authorization')?.replace('Bearer ', '');
  if (!token) return res.status(401).send('Access denied');

  try {
    const decoded = jwt.verify(token, JWT_SECRET);
    req.user = decoded;
    next();
  } catch (err) {
    res.status(400).send('Invalid token');
  }
};

// Middleware for role-based access
const authorize = (roles) => (req, res, next) => {
  if (!roles.includes(req.user.role)) {
    return res.status(403).send('Access forbidden');
  }
  next();
};

// File upload setup
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    const uploadDir = 'uploads/';
    if (!fs.existsSync(uploadDir)) fs.mkdirSync(uploadDir);
    cb(null, uploadDir);
  },
  filename: (req, file, cb) => {
    cb(null, Date.now() + path.extname(file.originalname));
  }
});

const upload = multer({ storage });

// Routes
app.post('/api/register', async (req, res) => {
  try {
    const { email, password, role = 'user' } = req.body;
    
    // Validate role
    if (!['admin', 'user'].includes(role)) {
      return res.status(400).send('Invalid role');
    }

    let user = await User.findOne({ email });
    if (user) return res.status(400).send('User already exists');

    user = new User({
      email,
      password: await bcrypt.hash(password, 10),
      role
    });

    await user.save();

    const token = jwt.sign({ id: user._id, role: user.role }, JWT_SECRET, {
      expiresIn: '1h'
    });

    res.send({ token });
  } catch (err) {
    res.status(500).send(err.message);
  }
});

app.post('/api/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    const user = await User.findOne({ email });
    if (!user) return res.status(400).send('Invalid credentials');

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) return res.status(400).send('Invalid credentials');

    const token = jwt.sign({ id: user._id, role: user.role }, JWT_SECRET, {
      expiresIn: '1h'
    });

    res.send({ token });
  } catch (err) {
    res.status(500).send(err.message);
  }
});

app.post('/api/upload', authenticate, authorize(['admin']), upload.single('pdf'), async (req, res) => {
  try {
    if (!req.file) return res.status(400).send('No file uploaded');

    const formData = new FormData();
    formData.append('pdf', fs.createReadStream(req.file.path));

    const ragResponse = await axios.post('http://localhost:5001/process_pdf', formData, {
      headers: {
        ...formData.getHeaders(),
      },
    });

    const pdf = new PDF({
      filename: req.file.originalname,
      path: req.file.path,
      uploadedBy: req.user.id
    });

    await pdf.save();

    res.send({ message: 'PDF uploaded successfully', pdf });
  } catch (err) {
    console.log(err)
    res.status(500).send(err.message);
  }
});

app.get('/api/pdfs', authenticate, async (req, res) => {
  try {
    const pdfs = await PDF.find().select('filename uploadedAt');
    res.send(pdfs);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

app.post('/api/chat', authenticate, async (req, res) => {
  try {
    const { question } = req.body;
    
    if (!question) return res.status(400).send('No question provided');

    // Call Python RAG service
    const ragResponse = await axios.post('http://localhost:5001/ask', {
      question: question
    });
    res.send({
      answer: ragResponse.data.answer,
      sources: ragResponse.data.sources
    });
  } catch (err) {
    res.status(500).send(err.message);
  }
});

const PORT = 5000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
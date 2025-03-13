const express = require('express');
const mysql = require('mysql2');
const bcrypt = require('bcrypt');
const cors = require('cors');


const app = express();
app.use(express.json());
app.use(cors())
// MySQL Database Connection
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'testdb',
    port: 3306
});

db.connect(err => {
    if (err) {
        console.error('Database connection failed:', err.stack);
        return;
    }
    console.log('Connected to MySQL database');
});

// Secure Login Endpoint (Prevents SQL Injection)
app.post('/login', (req, res) => {
    const { username, password } = req.body;
    
    const query = `SELECT * FROM users WHERE username = ?`;
    db.query(query, [username], async (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database error' });
        }
        
        if (results.length > 0) {
            const user = results[0];
            const passwordMatch = await bcrypt.compare(password, user.password);
            
            if (passwordMatch) {
                res.json({ message: 'Login successful', user: { id: user.id, username: user.username } });
            } else {
                res.status(401).json({ error: 'Password is Wrong' });
            }
        } else {
            res.status(401).json({ error: 'Username is Wrong' });
        }
    });
});

// Secure Route to Add a User (Prevents SQL Injection & Hashes Passwords)
app.post('/add-user', async (req, res) => {
    const { username, password } = req.body;
    
    try {
        const hashedPassword = await bcrypt.hash(password, 10);
        const query = `INSERT INTO users (username, password) VALUES (?, ?)`;
        
        db.query(query, [username, hashedPassword], (err, results) => {
            if (err) {
                return res.status(500).json({ error: 'Database error' });
            }
            res.json({ message: 'User added successfully', userId: results.insertId });
        });
    } catch (error) {
        res.status(500).json({ error: 'Server error' });
    }
});

// Start Server
const PORT = 5000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

/*
 * Security Fixes Applied:
 * 1. Used Prepared Statements (Prevents SQL Injection)
 * 2. Used bcrypt for Password Hashing (Prevents Plaintext Storage)
 * 3. Removed Direct String Concatenation in Queries
 * 4. Limited Response Data (Prevents Data Exposure)
 */

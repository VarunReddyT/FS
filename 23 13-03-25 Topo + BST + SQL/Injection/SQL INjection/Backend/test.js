const express = require('express');
const mysql = require('mysql');
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

// Vulnerable Login Endpoint
app.post('/login', (req, res) => {
    const { username, password } = req.body;
    
    // **Vulnerable Query (Susceptible to SQL Injection)**
    const query = `SELECT * FROM users WHERE username = '${username}' AND password = '${password}'`;
    console.log({ username, password });
    console.log("QUERY: "+query);
    db.query(query, (err, results) => {
        if (err) {
            res.status(500).json({ error: 'Database error' });
        } else if (results.length > 0) {
            res.json({ message: 'Login successful', user: results[0] });
        } else {
            res.status(401).json({ error: 'Invalid credentials' });
        }
    });
});

// Route to Add a User (Vulnerable to SQL Injection)
app.post('/add-user', (req, res) => {
    const { username, password } = req.body;
    
    // **Vulnerable Query (No Input Sanitization)**
    const query = `INSERT INTO users (username, password) VALUES ('${username}', '${password}')`;
    
    db.query(query, (err, results) => {
        if (err) {
            res.status(500).json({ error: 'Database error' });
        } else {
            res.json({ message: 'User added successfully', userId: results.insertId });
        }
    });
});

// Start Server
const PORT = 5000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

/*
 * How to exploit:
 * Send a POST request to /login with the following body:
 * { "username": "admin' -- ", "password": "anything" }
 * This bypasses authentication if a user "admin" exists in the database.
 *
 * To exploit user creation:
 * Send a POST request to /add-user with:
 * { "username": "hacker' -- ", "password": "password" }
 * This can manipulate SQL statements and insert unintended values.
 */

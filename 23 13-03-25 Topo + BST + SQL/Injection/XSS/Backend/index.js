// Problem Statement: 
// Create a MERN stack web application that demonstrates Cross-Site Scripting (XSS).
// The application allows users to submit comments, but does not sanitize input, making it vulnerable to XSS.
// Stored XSS occurs when a user submits a comment with a script tag that executes when another user views the page.

// Backend - Express & MySQL
const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// MySQL Database Connection
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'xss_demo'
});

db.connect(err => {
    if (err) {
        console.error('Database connection failed:', err.stack);
        return;
    }
    console.log('Connected to MySQL database');
});

// Vulnerable Route to Add Comment (Allows XSS)
app.post('/add-comment', (req, res) => {
    const { username, comment } = req.body;
    console.log({ username, comment });
    const query = `INSERT INTO comments (username, comment) VALUES ('${username}', '${comment}')`;
    console.log(query);
    db.query(query, (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database error' });
        }
        res.json({ message: 'Comment added successfully' });
    });
});

// Route to Fetch Comments (Displays Stored XSS)
app.get('/comments', (req, res) => {
    const query = `SELECT * FROM comments`;
    db.query(query, (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database error' });
        }
        res.json(results);
    });
});

// Start Server
const PORT = 5000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

/*
 * Exploiting XSS:
 * 1. Send a POST request to `/add-comment` with the following JSON payload:
 *    { "username": "attacker", "comment": "<script>alert('XSS Attack!')</script>" }
 * 2. Visit `/comments` in the frontend to see the alert execute.
 *
 * This demonstrates **Stored XSS**, where the malicious script is stored in the database and executed when viewed by other users.
 */

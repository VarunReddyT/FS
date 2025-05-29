# Product Catalog Management System - Backend

## Setup

1. Install dependencies:
   npm install express mongoose cors dotenv

2. Set your MongoDB URI in `.env` (already set to local by default).

3. Seed the database:
   node seed.js

4. Start the server:
   node server.js

## Endpoints

- `GET /api/products` — Returns all products as JSON.

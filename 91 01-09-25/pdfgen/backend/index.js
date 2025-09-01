const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const Ticket = require('./models/Ticket');

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// MongoDB Connection
mongoose.connect('mongodb://127.0.0.1:27017/trainTickets')
  .then(() => console.log('Connected to MongoDB'))
  .catch(err => console.error('MongoDB connection error:', err));

// Sample data insertion
const sampleTickets = [
  {
    pnr: "12345",
    trainDetails: {
      trainNumber: "12650",
      trainName: "Sampark Kranti Express",
      fromStation: "New Delhi",
      toStation: "Bangalore",
      departureTime: "16:50",
      arrivalTime: "20:30",
      journeyDate: new Date("2025-09-15"),
      distance: 2150
    },
    passengers: [
      {
        name: "Rahul Kumar",
        age: 28,
        gender: "Male",
        seatNumber: "24",
        coachNumber: "B2"
      },
      {
        name: "Priya Kumar",
        age: 25,
        gender: "Female",
        seatNumber: "25",
        coachNumber: "B2"
      }
    ],
    fareDetails: {
      baseFare: 1250,
      gst: 62.5,
      totalFare: 1312.5
    },
    bookingDetails: {
      bookingDate: new Date("2025-08-25"),
      status: "Confirmed",
      class: "3A"
    }
  },
  {
    pnr: "10000",
    trainDetails: {
      trainNumber: "12951",
      trainName: "Rajdhani Express",
      fromStation: "Mumbai Central",
      toStation: "New Delhi",
      departureTime: "14:30",
      arrivalTime: "08:45",
      journeyDate: new Date("2025-09-20"),
      distance: 1384
    },
    passengers: [
      {
        name: "Amit Shah",
        age: 45,
        gender: "Male",
        seatNumber: "12",
        coachNumber: "A1"
      }
    ],
    fareDetails: {
      baseFare: 2450,
      gst: 122.5,
      totalFare: 2572.5
    },
    bookingDetails: {
      bookingDate: new Date("2025-08-28"),
      status: "Confirmed",
      class: "2A"
    }
  },
  {
    pnr: "12346",
    trainDetails: {
      trainNumber: "12651",
      trainName: "Sampark Kranti Express",
      fromStation: "New Delhi",
      toStation: "Bangalore",
      departureTime: "16:50",
      arrivalTime: "20:30",
      journeyDate: new Date("2025-09-15"),
      distance: 2150
    },
    passengers: [
      {
        name: "Rahul Kumar",
        age: 28,
        gender: "Male",
        seatNumber: "24",
        coachNumber: "B2"
      },
      {
        name: "Priya Kumar",
        age: 25,
        gender: "Female",
        seatNumber: "25",
        coachNumber: "B2"
      }
    ],
    fareDetails: {
      baseFare: 1250,
      gst: 62.5,
      totalFare: 1312.5
    },
    bookingDetails: {
      bookingDate: new Date("2025-08-25"),
      status: "Confirmed",
      class: "3A"
    }
  },
  {
    pnr: "12347",
    trainDetails: {
      trainNumber: "12951",
      trainName: "Rajdhani Express",
      fromStation: "Mumbai Central",
      toStation: "New Delhi",
      departureTime: "14:30",
      arrivalTime: "08:45",
      journeyDate: new Date("2025-09-20"),
      distance: 1384
    },
    passengers: [
      {
        name: "Amit Shah",
        age: 45,
        gender: "Male",
        seatNumber: "12",
        coachNumber: "A1"
      }
    ],
    fareDetails: {
      baseFare: 2450,
      gst: 122.5,
      totalFare: 2572.5
    },
    bookingDetails: {
      bookingDate: new Date("2025-08-28"),
      status: "Confirmed",
      class: "2A"
    }
  }
];

// Insert sample data if collection is empty
async function insertSampleData() {
  const count = await Ticket.countDocuments();
  if (count === 0) {
    await Ticket.insertMany(sampleTickets);
    console.log('Sample ticket data inserted');
  }
}

insertSampleData().catch(console.error);

// Routes
app.get('/api/ticket/:pnr', async (req, res) => {
  try {
    const ticket = await Ticket.findOne({ pnr: req.params.pnr });
    if (!ticket) {
      return res.status(404).json({ message: 'Ticket not found' });
    }
    res.json(ticket);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
});

const PORT = 5000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

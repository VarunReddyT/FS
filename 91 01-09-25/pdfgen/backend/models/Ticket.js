const mongoose = require('mongoose');

const ticketSchema = new mongoose.Schema({
  pnr: {
    type: String,
    required: true,
    unique: true,
    match: /^[0-9]{5}$/
  },
  trainDetails: {
    trainNumber: {
      type: String,
      required: true
    },
    trainName: {
      type: String,
      required: true
    },
    fromStation: {
      type: String,
      required: true
    },
    toStation: {
      type: String,
      required: true
    },
    departureTime: {
      type: String,
      required: true
    },
    arrivalTime: {
      type: String,
      required: true
    },
    journeyDate: {
      type: Date,
      required: true
    },
    distance: {
      type: Number,
      required: true
    }
  },
  passengers: [{
    name: {
      type: String,
      required: true
    },
    age: {
      type: Number,
      required: true
    },
    gender: {
      type: String,
      required: true,
      enum: ['Male', 'Female', 'Other']
    },
    seatNumber: {
      type: String,
      required: true
    },
    coachNumber: {
      type: String,
      required: true
    }
  }],
  fareDetails: {
    baseFare: {
      type: Number,
      required: true
    },
    gst: {
      type: Number,
      required: true
    },
    totalFare: {
      type: Number,
      required: true
    }
  },
  bookingDetails: {
    bookingDate: {
      type: Date,
      required: true,
      default: Date.now
    },
    status: {
      type: String,
      required: true,
      enum: ['Confirmed', 'Waitlisted', 'RAC', 'Cancelled']
    },
    class: {
      type: String,
      required: true,
      enum: ['1A', '2A', '3A', 'SL', 'CC', 'EC']
    }
  }
});

module.exports = mongoose.model('Ticket', ticketSchema);

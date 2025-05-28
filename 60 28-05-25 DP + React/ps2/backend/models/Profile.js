const mongoose = require('mongoose');

const profileSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  email: {
    type: String,
    required: true,
    unique: true
  },
  phone: {
    type: String,
    required: true
  },
  education: {
    degree: String,
    institution: String,
    year: String
  },
  interests: [String],
  achievements: [String]
});

const Profile = mongoose.model('Profile', profileSchema);

module.exports = Profile;
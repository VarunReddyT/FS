const mongoose = require('mongoose');
const userSchema = new mongoose.Schema({
    username: {
        type: String,
        required: true,
        trim: true
    },
    password : {
        type : String,
        required: true,
    },
    role:{
        type : String, 
        enum : ['admin', 'consumer'],
    }
});

module.exports = mongoose.model('User', userSchema);
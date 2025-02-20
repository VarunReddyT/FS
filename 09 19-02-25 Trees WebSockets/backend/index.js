const ws = require('ws');
const mongoose = require('mongoose');
const server = new ws.Server({ port: 8080 });
const AutoIncrement = require('mongoose-sequence')(mongoose);


mongoose.connect("mongodb://localhost:27017/ws")
    .then(() => {
        console.log("Mongo Connected");
    })
    .catch((err) => {
        console.log(err);
    })

const UserSchema = mongoose.Schema({
    _id : {
        type: Number
    },
    name: {
        type: String
    },
    salary: {
        type: Number
    },
    role : {
        type: String
    },
    department: {
        type: String
    },
    experience: {
        type: Number
    }
})

UserSchema.plugin(AutoIncrement, { id: 'counter', inc_field: '_id' });

const User = mongoose.model('UserWS', UserSchema);

server.on("connection", (socket) => {
    socket.on("connection",()=>{
        socket.send("Connected");
    })
    socket.on("message",async (message)=>{
        let data = message.toString().split(" ");

        if(data[0] === "INSERT"){
            let newUser = new User({name: data[1],salay: data[2],role: data[3],department: data[4],experience: data[5]
            });
            await newUser.save();
            socket.send(`Employee inserted successfully.ID: ${newUser._id}`);
        }
        else if(data[0] === "RETRIEVE_BY_DEPT"){
            let employees = await User.find({department: data[1]});
            employees.forEach((employee)=>{
                socket.send(`ID: ${employee._id}, Name: ${employee.name}, Salary: ${employee.salary}, Role: ${employee.role}, Department: ${employee.department}, Experience: ${employee.experience} years`);
            })
        }
        else if(data[0] === "RETRIEVE"){
            let employees = await User.find();
            employees.forEach((employee)=>{
                socket.send(`ID: ${employee._id}, Name: ${employee.name}, Salary: ${employee.salary}, Role: ${employee.role}, Department: ${employee.department}, Experience: ${employee.experience} years`);
            })
        }
        else{
            socket.send("Invalid command.");
        }
    });
    socket.on("close", () => {
        console.log("Connection closed.");
    })
})
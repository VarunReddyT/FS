const ws = require('ws');
const server = new ws.Server({port: 8080});

let employees = [];
server.on("connection",(socket)=>{
    socket.on("connection",()=>{
        console.log("Connection established");
    });
    socket.on("message",(message)=>{
        let data = message.toString().split(" ");

        if(data[0] === "INSERT"){
            employees.push({ID : employees.length + 1, Name: data[1], Salary: data[2]});
            socket.send("Employee inserted successfully.");
        }
        else if(data[0] === "RETRIEVE"){
            employees.forEach((employee)=>{
                socket.send(`ID: ${employee.ID}, Name: ${employee.Name}, Salary: ${employee.Salary}`);
            })
        }
        else{
            socket.send("Invalid command.");
        }
    });
    socket.on("close",()=>{
        console.log("Connection closed");
    });
})
const ws = require('ws');
const server = new ws.Server({ port: 8080 });

let users = []
server.on("connection", (socket) => {
    users.push(socket);
    socket.on("message",async (message)=>{
        users.forEach((user)=>{
            if(user.readyState === ws.OPEN){
                user.send(message.toString());
            }
        })
    });
    socket.on("close", () => {
        users = users.filter(user => user !== socket);
    })
})
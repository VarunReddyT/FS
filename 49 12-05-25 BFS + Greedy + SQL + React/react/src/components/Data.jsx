import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";  

export default function Data() {
    const [data, setData] = useState([]);

    useEffect(() => {
        axios.get("https://jsonplaceholder.typicode.com/users")
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    }, []);


    return (
        <div>
            <h1>Users</h1>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Website</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map(user => (
                        <tr key={user.id}>
                            <td>{user.name}</td>
                            <td>{user.email}</td>
                            <td>{user.website}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
import React from "react";
import { useState } from "react";

export default function Form() {
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [emailError, setEmailError] = useState("");
    const [usernameError, setUsernameError] = useState("");
    const [passwordError, setPasswordError] = useState("");

    const handleEmailChange = (e) => {
    const value = e.target.value;
    setEmail(value);
    if (!/^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(value)) {
        setEmailError("Email must contain letters, numbers, dots, underscores and follow format: example@domain.com");
    } else {
        setEmailError("");
    }
};


    const handleUsernameChange = (e) => {
    const value = e.target.value;
    setUsername(value);
    if (!/^[a-zA-Z][a-zA-Z0-9]*$/.test(value)) {
        setUsernameError("Username must start with a letter and contain only letters and digits.");
    } else {
        setUsernameError("");
    }
};


    const handlePasswordChange = (e) => {
    const value = e.target.value;
    setPassword(value);
    if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8}$/.test(value)) {
        setPasswordError("Password must be exactly 8 characters long and include: 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character.");
    } else {
        setPasswordError("");
    }
};


    const handleSubmit = (e) => {
        e.preventDefault();
        if (emailError || usernameError || passwordError) {
            alert("Please fix the errors before submitting");
        } else {
            alert(`
                Email: ${email}
                Username: ${username}
                Password: ${password}
                `);
        }
    };

    return (
        <div>
            <h1>Form Validation</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Email:</label>
                    <input type="text" value={email} onChange={handleEmailChange} required />
                    {emailError && <span style={{ color: "red" }}>{emailError}</span>}
                </div>
                <div>
                    <label>Username:</label>
                    <input type="text" value={username} onChange={handleUsernameChange} required />
                    {usernameError && <span style={{ color: "red" }}>{usernameError}</span>}
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={handlePasswordChange} required />
                    {passwordError && <span style={{ color: "red" }}>{passwordError}</span>}
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );

}
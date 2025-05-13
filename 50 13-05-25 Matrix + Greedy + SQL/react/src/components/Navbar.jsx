import React from "react";
import { Link } from "react-router-dom";
export default function Navbar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">1 Button</Link>
                </li>
                <li>
                    <Link to="/display2">2 Buttons</Link>
                </li>
                <li>
                    <Link to="/calculator">Calculator</Link>
                </li>
                <li>
                    <Link to="/form">Form</Link>
                </li>
            </ul>
        </nav>
    );
}
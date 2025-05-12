import React from "react";
import { Link } from "react-router-dom";
export default function Navbar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Greetings</Link>
                </li>
                <li>
                    <Link to="/counter">Counter</Link>
                </li>
                <li>
                    <Link to="/data">Data</Link>
                </li>
            </ul>
        </nav>
    );
}
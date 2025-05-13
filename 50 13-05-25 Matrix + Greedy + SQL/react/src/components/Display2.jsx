import React from "react";
import { useState } from "react";

export default function Display(){
    const [display, setDisplay] = useState(false);
    const [display2, setDisplay2] = useState(false);
    return(
        <div>
            <button onClick={() => setDisplay(!display)}>
                {display ? "Hide" : "Show"}
            </button>
            <button onClick={() => setDisplay2(!display2)}>
                {display2 ? "Hide" : "Show"}
            </button>
            {display && display2 && (
                <h1>Welcome to my page!</h1>
            )}
        </div>
    )
}
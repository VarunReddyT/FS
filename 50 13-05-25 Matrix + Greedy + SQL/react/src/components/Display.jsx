import React from "react";
import { useState } from "react";

export default function Display(){
    const [display, setDisplay] = useState(false);

    return(
        <div>
            <button onClick={() => setDisplay(!display)}>
                {display ? "Hide" : "Show"}
            </button>
            {display && (
                <h1>Welcome to my page!</h1>
            )}
        </div>
    )
}
import React from "react";
import {useState} from "react";

export default function Calculator(){
    const [num1, setNum1] = useState(null);
    const [num2, setNum2] = useState(null);
    const [result, setResult] = useState(null);

    const handleNum1Change = (e) => {
        setNum1(e.target.value);
    };

    const handleNum2Change = (e) => {
        setNum2(e.target.value);
    };

    const handleCalculate = (operator) => {
        if(num1 === '' || num2 === ''){
            alert("Please enter both numbers");
            return;
        }
        let n1 = parseFloat(num1);
        let n2 = parseFloat(num2);
        if(n2 === 0 && operator == '/'){
            alert("Cannot divide by zero");
            return;
        }
        let res;
        switch (operator) {
            case '+':
                res = n1 + n2;
                break;
            case '-':
                res = n1 - n2;
                break;
            case '*':
                res = n1 * n2;
                break;
            case '/':
                res = n1 / n2;
                break;
            default:
                res = 0;
        }
        setResult(res);
    };

    return(
        <div>
            <h1>Calculator</h1>
            <input type="number" value={num1} onChange={handleNum1Change} required />
            <input type="number" value={num2} onChange={handleNum2Change} required />
            <div>
                <button onClick={() => handleCalculate('+')}>+</button>
                <button onClick={() => handleCalculate('-')}>-</button>
                <button onClick={() => handleCalculate('*')}>*</button>
                <button onClick={() => handleCalculate('/')}>/</button>
            </div>
            <h2>Result: {result}</h2>
        </div>
    )
}
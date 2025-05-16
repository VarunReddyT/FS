import React, { useContext, useState } from 'react';
import { CalculatorContext } from './CalculatorContext';
import './Calculator.css';
export default function Calculator() {
    const { state, dispatch } = useContext(CalculatorContext);
    return (
        <div className="calculator">
            <div className="display">
                <input type="text" value={state.currentInput} readOnly />
            </div>
            <div className="buttons">
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '1' })}>1</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '2' })}>2</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '3' })}>3</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '4' })}>4</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '5' })}>5</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '6' })}>6</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '7' })}>7</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '8' })}>8</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '9' })}>9</button>
                <button onClick={() => dispatch({ type: 'SET_DIGIT', digit: '0' })}>0</button>
                <button onClick={() => dispatch({ type: 'SET_OPERATION', operator: '+' })}>+</button>
                <button onClick={() => dispatch({ type: 'SET_OPERATION', operator: '-' })}>-</button>
                <button onClick={() => dispatch({ type: 'SET_OPERATION', operator: '*' })}>*</button>
                <button onClick={() => dispatch({ type: 'SET_OPERATION', operator: '/' })}>/</button>
                <button onClick={() => dispatch({ type: 'EQUALS' })}>=</button>
            </div>
            <div className="buttons">
                <button onClick={() => dispatch({ type: 'CLEAR' })}>C</button>
            </div>
        </div>
    )
}

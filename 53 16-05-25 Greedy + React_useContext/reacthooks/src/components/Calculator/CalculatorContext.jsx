import React, { createContext, useState } from 'react';
import { useReducer } from 'react';

export const CalculatorContext = createContext();


const initialState = {
    currentInput: '',
    previousInput: '',
    operator: null,
}
const reducer = (state, action) => {
    switch (action.type) {
        case 'SET_DIGIT':
            if (action.digit === '0' && state.currentInput === '') {
                return state
            }
            return {
                ...state,
                currentInput: state.currentInput + action.digit
            }
        case 'SET_OPERATION':
            if (state.currentInput === '') {
                return state
            }
            if (state.previousInput !== '') {
                return {
                    ...state,
                    previousInput: state.previousInput,
                    operator: action.operator,
                    currentInput: ''
                }
            }
            return {
                ...state,
                previousInput: state.currentInput,
                operator: action.operator,
                currentInput: ''
            }
        case 'EQUALS':
            if (state.currentInput === '' || state.previousInput === '') {
                return state
            }
            let result
            const prev = parseFloat(state.previousInput)
            const current = parseFloat(state.currentInput)
            switch (state.operator) {
                case '+':
                    result = prev + current
                    break
                case '-':
                    result = prev - current
                    break
                case '*':
                    result = prev * current
                    break
                case '/':
                    result = prev / current
                    break
                default:
                    return state
            }
            return {
                ...state,
                previousInput: '',
                operator: null,
                currentInput: result.toString()
            }
        case 'CLEAR':
            return initialState
        default:
            return state
    }
}
export const CalculatorProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState)

    return(
        <CalculatorContext.Provider value={{ state, dispatch }}>
            {children}
        </CalculatorContext.Provider>
    )
}
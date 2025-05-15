import React from 'react'
import { useReducer } from 'react'
import './Calculator.css'
export default function Calculator() {

    const initialState = {
        currentInput: '',
        previousInput: '',
        operator: null,
    }
    
    const reducer = (state,action)=>{
        switch(action.type){
            case 'SET_DIGIT':
            if(action.digit === '0' && state.currentInput === ''){
                return state
            }
            return{
                ...state,
                currentInput: state.currentInput + action.digit
            }
            case 'SET_OPERATION':
            if(state.currentInput === ''){
                return state
            }
            if(state.previousInput !== ''){
                return{
                    ...state,
                    previousInput: state.previousInput,
                    operator: action.operator,
                    currentInput: ''
                }
            }
            return{
                ...state,
                previousInput: state.currentInput,
                operator: action.operator,
                currentInput: ''
            }
            case 'EQUALS':
            if(state.currentInput === '' || state.previousInput === ''){
                return state
            }
            let result
            const prev = parseFloat(state.previousInput)
            const current = parseFloat(state.currentInput)
            switch(state.operator){
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
                    return{
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
            
            const [state, dispatch] = useReducer(reducer, initialState)
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

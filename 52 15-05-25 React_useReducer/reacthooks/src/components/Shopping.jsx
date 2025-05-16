// Display available products, cart, total cost
// Cart - reducer
// display cart - product - count * price
// Below display cost 
// Remove button form cart

import React from 'react'
import { useReducer } from 'react'
const products = [
    { id: 1, name: 'Apple', price: 150 },
    { id: 2, name: 'Mangoes', price: 250 },
    { id: 3, name: 'Cherries', price: 100 },
]

export default function Shopping() {

    const initialState = {
        cart: [],
        totalCost: 0,
    }

    const reducer = (state,action)=>{
        switch(action.type){
            case 'ADD_TO_CART':
                const index = state.cart.findIndex(item => item.id === action.payload.id)
                var existingCart = [...state.cart]
                if(index !== -1){
                    existingCart[index].count += 1;
                }
                else{
                    existingCart.push({...action.payload, count : 1});
                }
                
                var totalCost = existingCart.reduce((sum,item)=>{
                    return sum + (item.price*item.count)
                },0);

                return{
                    ...state,
                    cart:existingCart,
                    totalCost
                }
            case 'REMOVE_FROM_CART':
                var existingCart = [...state.cart];

                let updatedCart = existingCart.filter((item) => item.id !== action.payload.id);

                var totalCost = updatedCart.reduce((sum,item)=>{
                    return sum + (item.price*item.count)
                },0)

                return{
                    ...state,
                    cart:updatedCart,
                    totalCost
                }
        }
    }

    const [state,dispatch] = useReducer(reducer,initialState);

    return (
        <div className="container p-5">
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product) => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>
                                <button className="btn btn-primary" 
                                    onClick={()=>dispatch({type : "ADD_TO_CART", payload : product})}
                                >Add to Cart</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="row">
                <div className="col-md-6">
                    <h2>Cart</h2>
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Count</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {/* {state.cart.map(product,index)=>{}} */}
                            {state.cart.map((product) => (
                                <tr key={product.id}>
                                    <td>{product.name}</td>
                                    <td>{product.count}</td>
                                    <td>{product.price}</td>
                                    <td>
                                        <button className='btn btn-danger'
                                            onClick={()=>dispatch({type:"REMOVE_FROM_CART",payload : {id : product.id}})}
                                        >
                                            Remove
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
            <div className="row">
                <div className="col-md-6">
                    <h2>Total Cost</h2>
                    <p>{state.totalCost}</p>
                </div>
            </div>
        </div>
    )
}

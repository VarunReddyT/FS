import React, {useState, useContext} from 'react';
import { ThemeContext } from './ThemeContext';

export default function ThemedBox() {
    const {theme} = useContext(ThemeContext);

    const styles = {
        padding: '20px',
        backgroundColor: theme === 'light' ? '#fff' : '#333',
        color: theme === 'light' ? '#000' : '#fff',
        border: '1px solid',
        borderColor: theme === 'light' ? '#ccc' : '#555',
        borderRadius: '5px'
    };
    return (
        <div style={styles}>
            This is a {theme} themed box!
        </div>
    );
}
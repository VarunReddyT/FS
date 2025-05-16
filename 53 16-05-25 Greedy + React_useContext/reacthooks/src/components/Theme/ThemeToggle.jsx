import React, {useContext} from 'react';
import { ThemeContext } from './ThemeContext';

export default function ThemeToggle() {
    const {toggleTheme} = useContext(ThemeContext);
    return (
        <button onClick={toggleTheme} className="theme-toggle">
            Toggle Theme
        </button>
    );
}
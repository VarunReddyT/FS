import ThemedBox from "./components/Theme/ThemedBox"
import ThemeToggle from "./components/Theme/ThemeToggle"
import { ThemeProvider } from "./components/Theme/ThemeContext"

import { CalculatorProvider } from "./components/Calculator/CalculatorContext"
import Calculator from "./components/Calculator/Calculator"
function App() {
  return (
    // <ThemeProvider>
    //     <ThemeToggle />
    //     <ThemedBox />
    // </ThemeProvider>
    <CalculatorProvider>
        <Calculator />
    </CalculatorProvider>
  )
}

export default App

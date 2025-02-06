/*
Given an array of product objects, each with name, price, 
and inStock (boolean). Use filter to include only products 
that are in stock, map to get their quantities, and reduce 
to find the total quantity of these in-stock products.


Sample Input:
-------------
5
Laptop 1000 5 true
Phone 500 3 false
Tablet 300 8 true
Monitor 200 6 false
Keyboard 50 12 true

Sample Output: 
--------------
25

Explanation:
------------
In-stock products are Laptop, Tablet, and Keyboard.
Quantities are 5, 8, and 12.
Total quantity = 5 + 8 + 12 = 25

*/
const readline = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout
  });
  
  function solution(products) {
    //Write your code here and return number
    const inStockProducts = products.filter(product => product.inStock == true);
    const quantities = inStockProducts.map(product => product.quantity);
    const totalQuantity = quantities.reduce((acc,num)=>acc+num,0);
    return totalQuantity;
    
  }
  
  readline.question("", (N) => {
    N = parseInt(N);
    let products = [];
    let count = 0;
  
    readline.on("line", (line) => {
      const [name, price, quantity, inStock] = line.split(" ");
      products.push({ name, price: parseFloat(price), quantity: parseInt(quantity), inStock: inStock === 'true' });
      count++;
  
      if (count === N) {
        const totalQuantity = solution(products);
        console.log(totalQuantity);
        readline.close();
      }
    });
  });
  
  
  
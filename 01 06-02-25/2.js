/*
You have an array of product objects, each with name, price, 
and sold (number of units sold). Use map to calculate the 
revenue for each product, filter to include only products 
with a revenue of $50 or more, and reduce to find the total 
revenue from these high-selling products.


Sample Input: 
-------------
5
Shampoo 5 15
Soap 2 20
Toothpaste 3 10
Conditioner 10 2
Lotion 8 10

Sample Output: 
--------------
155

Explanation:
------------
Shampoo revenue = 5 * 15 = 75
Soap revenue = 2 * 20 = 40 (not included, as it’s less than 50)
Toothpaste revenue = 3 * 10 = 30 (not included)
Conditioner revenue = 10 * 2 = 20 (not included)
Lotion revenue = 8 * 10 = 80

*/

const readline = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout
  });
  
  function solution(products) {
    //Write your code here and return number
    const revenue = products.map(product => product.price*product.sold);
    const filtered = revenue.filter((rev) => rev>=50);
    const totalRevenue = filtered.reduce((acc,num)=>acc+num,0);
    return totalRevenue;
   
  }
  
  readline.question("", (N) => {
    N = parseInt(N);
    let products = [];
    let count = 0;
  
    readline.on("line", (line) => {
      const [name, price, sold] = line.split(" ");
      products.push({ name, price: parseFloat(price), sold: parseInt(sold) });
      count++;
  
      if (count === N) {
        const totalRevenue = solution(products);
        console.log(totalRevenue);
        readline.close();
      }
    });
  });
  
  
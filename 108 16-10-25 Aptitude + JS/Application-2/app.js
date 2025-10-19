// ========== Shop-Site Application Logic ==========
// NOTE TO STUDENTS:
// You must implement ALL the functions below.
// The HTML, CSS, and products.js files are already provided.
// DO NOT modify those files — only edit this file (app.js).

// ========== Navigation ==========

/**
 * goToProducts(category)
 * ----------------------
 * Redirect user to products.html with the selected category.
 * Example: products.html?category=Fruits
 */
function goToProducts(category) {
  // TODO: Implement redirection logic
  window.location.href = `products.html?category=${category}`;
}

// ========== Rendering Products ==========

/**
 * renderProducts(list)
 * --------------------
 * Display all products in the given list as cards inside #product-list.
 * Each card should contain:
 *  - Product image
 *  - Product name
 *  - Product brand
 *  - Product cost
 *  - "Add to Cart" button (button should call addToCart(index))
 */
function renderProducts(list) {
  // TODO: Implement product rendering
  const pl = document.getElementById("product-list");
  pl.innerHTML = "";
  list.forEach((product, index) => {
    const card = document.createElement("div");
    card.className = "item";
    card.innerHTML = `
      <img src="${product.image}" alt="${product.name}" class="product-image">
      <h3 class="product-name">${product.name}</h3>
      <p class="product-brand">${product.brand}</p>
      <p class="product-cost">₹${product.cost.toFixed(2)}</p>
      <button onclick="addToCart(${index})">Add to Cart</button>
    `;
    pl.appendChild(card);
  });
}

// ========== Sorting ==========

/**
 * sortProducts()
 * --------------
 * Sort the current product list based on the selected option:
 *  - Name (A-Z)
 *  - Price (Low to High)
 *  - Price (High to Low)
 * Then re-render the products.
 */
function sortProducts() {
  // TODO: Implement sorting
  const s = document.getElementById("sort-select").value;
  if(s === "name") {
    currentDisplayList.sort((a, b) => a.name.localeCompare(b.name));
  } else if(s === "price-asc") {
    currentDisplayList.sort((a, b) => a.cost - b.cost);
  } else if(s === "price-desc") {
    currentDisplayList.sort((a, b) => b.cost - a.cost);
  }
  renderProducts(currentDisplayList);
}

// ========== Filtering ==========

/**
 * filterProducts()
 * ----------------
 * Filter the product list by brand based on the dropdown value (#filter-select).
 * If "all" is selected, show all products for the current category.
 * Otherwise, show only products of the chosen brand.
 */
function filterProducts() {
  // TODO: Implement filtering
  const f = document.getElementById("filter-select").value;
  if(f === "all") {
    currentDisplayList = [...filteredByCategory];
  } else {
    currentDisplayList = filteredByCategory.filter(product => product.brand === f);
  }
  renderProducts(currentDisplayList);
}

// ========== Cart Management ==========

let cart = [];

/**
 * addToCart(index)
 * ----------------
 * Add the product at the given index (from filteredByCategory) to the cart.
 * If the product is already in the cart, increase its quantity.
 * Otherwise, add it with quantity = 1.
 */
function addToCart(index) {
  // TODO: Implement add-to-cart logic
  // HINT: Use cart.find() to check if the item exists
  const product = currentDisplayList[index];
  const cartItem = cart.find(item => item.product.name === product.name);
  if(cartItem) {
    cartItem.quantity++;
  } else {
    cart.push({  product, quantity: 1 });
  }
  renderCart();
}

/**
 * renderCart()
 * ------------
 * Render the cart table inside #cart-items.
 * Each row should show:
 *  - Product image
 *  - Product name
 *  - Product brand
 *  - Product cost
 *  - Quantity
 *  - Total (cost × quantity)
 */
function renderCart() {
  // TODO: Implement cart rendering
  const ci = document.getElementById("cart-items");
  ci.innerHTML = "";
  cart.forEach(item => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td><img src="${item.product.image}" alt="${item.product.name}" class="cart-image"></td>
      <td>${item.product.name}</td>
      <td>${item.product.brand}</td>
      <td>₹${item.product.cost.toFixed(2)}</td>
      <td>${item.quantity}</td>
      <td>₹${(item.product.cost * item.quantity).toFixed(2)}</td>
    `;
    ci.appendChild(row);
  });
}

// ========== Initialization ==========

const urlParams = new URLSearchParams(window.location.search);
const category = urlParams.get("category");

let filteredByCategory = [];
let currentDisplayList = [];

/**
 * On page load:
 *  - If a category is selected (from URL), set the page title
 *  - Load products of that category into filteredByCategory
 *  - Render them using renderProducts()
 */
if (category) {
  // TODO: Implement initial category setup
  document.getElementById("category-title").innerText = category;
  filteredByCategory = products.filter(product => product.category === category);
  currentDisplayList = [...filteredByCategory];
  renderProducts(currentDisplayList);
}
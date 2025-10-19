function goToProducts(category) {
  window.location.href = `products.html?category=${category}`;
}

const urlParams = new URLSearchParams(window.location.search);
const category = urlParams.get("category");
let filteredByCategory = [];
let cart = [];


function renderProducts(list) {
  const productList = document.getElementById("product-list");
  if (!productList) return;
  productList.innerHTML = "";
  list.forEach((product, index) => {
    const div = document.createElement("div");
    div.className = "item";
    div.innerHTML = `
      <img src="${product.image}" alt="${product.name}">
      <p><strong>${product.name}</strong></p>
      <p>Brand: ${product.brand}</p>
      <p>Cost: ₹${product.cost}</p>
      <button onclick="addToCart(${index})">Add to Cart</button>
    `;
    productList.appendChild(div);
  });
}
function sortProducts() {
  const sortValue = document.getElementById("sort-select").value;
  if (sortValue === "name") {
    filteredByCategory.sort((a, b) => a.name.localeCompare(b.name));
  } else if (sortValue === "price-asc") {
    filteredByCategory.sort((a, b) => a.cost - b.cost);
  } else if (sortValue === "price-desc") {
    filteredByCategory.sort((a, b) => b.cost - a.cost);
  }
  renderProducts(filteredByCategory);
}
function filterProducts() {
  const filterValue = document.getElementById("filter-select").value;
  filteredByCategory = (filterValue === "all")
    ? products.filter(p => p.category === category)
    : products.filter(p => p.category === category && p.brand === filterValue);
  renderProducts(filteredByCategory);
}
function addToCart(index) {
  const product = filteredByCategory[index];
  const existing = cart.find(item => item.name === product.name);
  if (existing) {
    existing.quantity += 1;
  } else {
    cart.push({ ...product, quantity: 1 });
  }
  renderCart();
}
function renderCart() {
  const cartTable = document.getElementById("cart-items");
  if (!cartTable) return;
  cartTable.innerHTML = "";
  cart.forEach(item => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td><img src="${item.image}" class="cart-img"></td>
      <td>${item.name}</td>
      <td>${item.brand}</td>
      <td>₹${item.cost}</td>
      <td>${item.quantity}</td>
      <td>₹${item.cost * item.quantity}</td>
    `;
    cartTable.appendChild(row);
  });
}
if (category) {
  document.getElementById("category-title").textContent = `${category} Products`;
  filteredByCategory = products.filter(p => p.category === category);
  renderProducts(filteredByCategory);
}
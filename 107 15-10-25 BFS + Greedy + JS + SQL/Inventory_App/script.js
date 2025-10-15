'use strict';

/**
 * ============================================================
 *  Inventory Cards — Student Starter (implement TODOs)
 *  NOTE: renderGrid() is fully implemented for you.
 *  Implement: init(), addToCart(), removeOne(), renderCart()
 *  Keep DOM structure/classes as-is (tests rely on them).
 * ============================================================
 */

/* ------------------ DOM Elements ------------------ */
const els = {
    grid: document.getElementById('grid'),
    cartList: document.getElementById('cartList'),
    cartCount: document.getElementById('cartCount'),
};

/* ------------------ App State ------------------ */
/** Cart structure: plain object keyed by id
 * {
 *   [id]: { id: number, name: string, price: number, qty: number }
 * }
 */
let CART = {};
let ITEMS = [];

/* ------------------ REQUIRED: init() ------------------ */
async function init() {
    // TODO: fetch data.json, call renderGrid(items) and renderCart()
    try{
        const response = await fetch('./data.json');
        ITEMS = await response.json();
        renderGrid(ITEMS);
        // renderCart();
        console.log();
    }   
    catch(e){
        console.log(e);
    }
}

/* ------------------ PROVIDED: renderGrid() ------------------ */
/**
 * Already implemented for you. Do not modify.
 * - Renders cards (3 per row via CSS grid)
 * - Adds click handler: clicking a card should add item to cart (+1)
 */
function renderGrid(items) {
    els.grid.innerHTML = items
        .map(
            (item) => `
  <article class="card" data-id="${item.id}" data-name="${item.name}" data-price="${item.price}">
    <div class="row">
      <strong>${item.name}</strong>
      <span class="category">${item.category}</span>
    </div>
    <div class="row" style="margin-top:6px;">
      <span class="price">₹${item.price}</span>
      <span class="muted small">Stock: ${item.stock}</span>
    </div>
    <p class="desc">${item.description}</p>
    <p class="small muted">Click card to add to cart</p>
  </article>`
        )
        .join('');

    // Clicking a card adds that item to the cart
    els.grid.querySelectorAll('.card').forEach((cardEl) => {
        cardEl.addEventListener('click', () => {
            const id = Number(cardEl.dataset.id);
            const name = cardEl.dataset.name;
            const price = Number(cardEl.dataset.price);

            addToCart({
                id,
                name,
                price
            }); // <--- implement below
        });
    });
}

/* ------------------ REQUIRED: addToCart() ------------------ */
/**
 * TODO: Implement addToCart(item)
 * - If item.id not present in CART → create with qty=1
 * - Else increment existing qty by 1
 * - Then call renderCart()
 */
function addToCart(item) {
    // TODO
    if(!CART[item.id]){
        CART[item.id] = {
            ...item,
            qty: Number(1)
        }
    }
    else{
        CART[item.id].qty += isNaN(CART[item.id].qty) ? CART[item.id].qty + 1 : 1;
    }
    console.log(CART);
    renderCart();
}

/* ------------------ REQUIRED: removeOne() ------------------ */
/**
 * TODO: Implement removeOne(id)
 * - Decrement qty for that id by 1
 * - If qty reaches 0 → delete CART[id]
 * - Then call renderCart()
 */
function removeOne(id) {
    // TODO:
    if(CART[id]){
        CART[id].qty -= 1;
        if(CART[id].qty <= 0){
            delete CART[id];
        }
    }
    renderCart();   
}

/* ------------------ REQUIRED: renderCart() ------------------ */
/**
 TODO: Implement renderCart()
  - Convert CART object → array of rows
  - Update #cartCount = sum of all item qty
  - If no rows → show "Cart is empty..." message and return
  - Build cart list HTML with per-item total and grand total:
     <div class="cart-row" data-id="...">
       <span class="name">Item Name</span>
       <span class="qty">x N</span>
       <span class="price">₹ITEM_TOTAL</span>
     </div>
     <hr>
     <div class="cart-row" style="cursor:default;">
       <span class="name">Grand Total</span>
       <span class="price">₹GRAND_TOTAL</span>
     </div>
  - Add click handler to each `.cart-row[data-id]` to call removeOne(id)
 */
function renderCart() {
    const cartItems = Object.values(CART);

    const totalCount = cartItems.reduce((sum, item) => sum + item.qty, 0);
    els.cartCount.textContent = totalCount;

    if(cartItems.length === 0) {
        els.cartList.innerHTML = '<p>Cart is empty...</p>';
        return;
    }

    const grandTotal = cartItems.reduce((sum, item) => sum + (item.qty * item.price), 0);

    els.cartList.innerHTML = cartItems
        .map(item => `
            <div class="cart-row" data-id="${item.id}">
                <span class="name">${item.name}</span>
                <span class="qty">x ${item.qty}</span>
                <span class="price">₹${item.qty * item.price}</span>
            </div>
        `)
        .join('') 
        + 
        `<hr>
        <div class="cart-row" style="cursor:default;">
            <span class="name">Grand Total</span>
            <span class="price">₹${grandTotal}</span>
        </div>`;

    els.cartList.querySelectorAll('.cart-row[data-id]').forEach(row => {
        row.addEventListener('click', () => {
            const id = Number(row.dataset.id);
            removeOne(id);
        });
    });
}

init();
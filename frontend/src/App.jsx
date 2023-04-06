import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import CartPage from "./Pages/CartPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";

import { Routes, Route } from "react-router-dom";
import React from "react";
import CartItemClass from "./Classes/CartItemClass";
import flower_cart from "./assets/flower-cart.png";
import flower_cart_2 from "./assets/flower-cart-2.png"
import CheckoutPage from "./Pages/CheckoutPage";

function App() {
  let one = new CartItemClass(flower_cart, 'jeden', 2, 9.99)
  let two = new CartItemClass(flower_cart_2, "second one", 1, 14.99)
  return (
    <>
      {/* <Navbar /> */}
      <Routes>
        <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
        <Route path="/login" element={<><Navbar /><LoginPage /></>} />
        <Route path="/cart" element={<CartPage one={one} two={two}/>}/>
        <Route path="/checkout" element={<CheckoutPage/>}/>
        <Route path="/" element={<HomePage />} />
      </Routes>
    </>
  );
}

export default App;

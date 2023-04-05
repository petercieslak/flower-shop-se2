import LoginPage from "./Pages/LoginPage";
import CartPage from "./Pages/CartPage";
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
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/cart" element={<CartPage one={one} two={two}/>}/>
        <Route path="/checkout" element={<CheckoutPage/>}/>
      </Routes>
  );
}

export default App;

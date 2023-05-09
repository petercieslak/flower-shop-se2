import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";
import AdminPage from "./Pages/AdminPage";

import CheckoutPage from "./Pages/CheckoutPage"
import CartPage from "./Pages/CartPage";

import CartItemClass from "./Classes/CartItemClass";
import flower_cart from "./assets/flower-cart.png";
import flower_cart_2 from "./assets/flower-cart-2.png"

import { Routes, Route } from "react-router-dom";
import AdminNavbar from "./Pages/AdminNavbar";
import AddProductPage from "./Pages/AddProductPage";
import RegisterPage from "./Pages/RegisterPage";
import { TokenContext, NameContext } from "./ContextStore";
import { useState } from "react";

function App() {
  const [token, setToken] = useState("");
  const [name, setName] = useState("");

  let one = new CartItemClass(flower_cart, 'jeden', 2, 9.99)
  let two = new CartItemClass(flower_cart_2, "second one", 3, 14.99)
  return (
    <TokenContext.Provider value={{ token, setToken }}>
      <NameContext.Provider value={{ name, setName }}>
      {/* <Navbar /> */}
        <Routes>
          <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
          <Route path="/login" element={<><Navbar /><LoginPage /></>} />
          <Route path="/register" element={<><Navbar /><RegisterPage /></>} />
          <Route path="/cart" element={<><Navbar /><CartPage one={one} two={two}/></>} />
          <Route path="/checkout" element={<><Navbar /><CheckoutPage one={one} two={two}/></>} />
          <Route path="/" element={<HomePage />} />
          <Route path="/admin">
            <Route index element={<><AdminNavbar/><AdminPage /></>}/>
            <Route path="addproduct" element={<><AdminNavbar/><AddProductPage /></>} />
          </Route>
        </Routes>
      </NameContext.Provider>
    </TokenContext.Provider>
  );
}

export default App;
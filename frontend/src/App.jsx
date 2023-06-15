import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";
import AdminPage from "./Pages/AdminPage";
import OrdersPage from "./Pages/OrdersPage";
import ClientsOrdersPage from "./Pages/ClientsOrdersPage";
import CheckoutPage from "./Pages/CheckoutPage"
import CartPage from "./Pages/CartPage";
import CartItemClass from "./Classes/CartItemClass";
import ContactPage from "./Pages/ContactPage";
import flower_cart from "./assets/flower-cart.png";
import flower_cart_2 from "./assets/flower-cart-2.png"
import { Routes, Route } from "react-router-dom";
import AdminNavbar from "./Pages/AdminNavbar";
import AddProductPage from "./Pages/AddProductPage";
import RegisterPage from "./Pages/RegisterPage";
import ModifyProductPage from "./Pages/ModifyProductPage";
import {IdContext, TokenContext, NameContext, TypeContext } from "./ContextStore";
import { useState } from "react";
import ModifyOrderPage from "./Pages/ModifyOrderPage";
import ComplaintForm from "./Pages/ComplaintForm";

function App() {
  const [token, setToken] = useState("");
  const [name, setName] = useState("");
  const [id, setId] = useState("");
  const [type, setType] = useState("gift");

  let one = new CartItemClass(flower_cart, 'jeden', 2, 9.99)
  let two = new CartItemClass(flower_cart_2, "second one", 3, 14.99)
  return (
    <TokenContext.Provider value={{ token, setToken }}>
      <NameContext.Provider value={{ name, setName }}>
        <TypeContext.Provider value={{ type, setType }}>
      {/* <Navbar /> */}
          <IdContext.Provider value={{ id, setId }}>
          <Routes>
            <Route path="/complaint" element={<><Navbar /><ComplaintForm /></>} />
            <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
            <Route path="/login" element={<><Navbar /><LoginPage /></>} />
            <Route path="/register" element={<><Navbar /><RegisterPage /></>} />
            <Route path="/cart" element={<><Navbar /><CartPage one={one} two={two}/></>} />
            <Route path="/checkout" element={<><Navbar /><CheckoutPage one={one} two={two}/></>} />
            <Route path="/" element={<HomePage />} />
            <Route path="/orders">
              <Route index element={<><Navbar/><ClientsOrdersPage /></>}/>
              <Route path=":orderID" element={<><Navbar/><ModifyOrderPage /></>} />
            </Route>
            <Route path="/contact" element={<><Navbar/><ContactPage /></>} />
            <Route path="/admin">
              <Route path="products">
                <Route index element={<><AdminNavbar/><AdminPage /></>}/>
                <Route path="addproduct" element={<><AdminNavbar/><AddProductPage /></>} />
                <Route path=":productId" element={<><AdminNavbar/><ModifyProductPage /></>} />
              </Route>
              <Route path="orders">
                <Route index element={<><AdminNavbar/><OrdersPage /></>}/> 
                <Route path=":orderID" element={<><AdminNavbar/><ModifyOrderPage /></>} />
              </Route>
            </Route>
          </Routes>
          </IdContext.Provider>
        </TypeContext.Provider>
      </NameContext.Provider>
    </TokenContext.Provider>
  );
}

export default App;
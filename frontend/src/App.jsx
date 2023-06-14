import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";
import AdminPage from "./Pages/AdminPage";
import OrdersPage from "./Pages/OrdersPage";
import ClientsOrdersPage from "./Pages/ClientsOrdersPage";
import CheckoutPage from "./Pages/CheckoutPage"
import CartPage from "./Pages/CartPage";
import { Routes, Route } from "react-router-dom";
import AdminNavbar from "./Pages/AdminNavbar";
import AddProductPage from "./Pages/AddProductPage";
import RegisterPage from "./Pages/RegisterPage";
import ModifyProductPage from "./Pages/ModifyProductPage";
import {IdContext, TokenContext, NameContext, TypeContext, MailContext } from "./ContextStore";
import { useState } from "react";

function App() {
  const [token, setToken] = useState("");
  const [name, setName] = useState("");
  const [id, setId] = useState("");
  const [type, setType] = useState("gift");
  const [mail, setMail] = useState("");

  return (
    <TokenContext.Provider value={{ token, setToken }}>
      <NameContext.Provider value={{ name, setName }}>
        <TypeContext.Provider value={{ type, setType }}>
      {/* <Navbar /> */}
          <IdContext.Provider value={{ id, setId }}>
            <MailContext.Provider value={{mail, setMail}}>
              <Routes>
                <Route path="/products" element={<><Navbar /><ProductsPage/></>} />
                <Route path="/login" element={<><Navbar /><LoginPage/></>} />
                <Route path="/register" element={<><Navbar /><RegisterPage /></>} />
                <Route path="/cart" element={<><Navbar /><CartPage/></>} />
                <Route path="/checkout" element={<><Navbar /><CheckoutPage/></>} />
                <Route path="/" element={<HomePage />} />
                <Route path="/orders" element={<><Navbar/><ClientsOrdersPage /></>} />
                <Route path="/admin">
                  <Route path="products">
                    <Route index element={<><AdminNavbar/><AdminPage /></>}/>
                    <Route path="addproduct" element={<><AdminNavbar/><AddProductPage /></>} />
                    <Route path=":productId" element={<><AdminNavbar/><ModifyProductPage /></>} />
                  </Route>
                  <Route path="orders" element={<><AdminNavbar/><OrdersPage /></>} />     
                </Route>
              </Routes>
            </MailContext.Provider>
          </IdContext.Provider>
        </TypeContext.Provider>
      </NameContext.Provider>
    </TokenContext.Provider>
  );
}

export default App;
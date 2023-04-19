import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";
import AdminPage from "./Pages/AdminPage";

import { Routes, Route } from "react-router-dom";
import AdminNavbar from "./Pages/AdminNavbar";
import AddProductPage from "./Pages/AddProductPage";

function App() {
  return (
    <>
      {/* <Navbar /> */}
      <Routes>
        <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
        <Route path="/login" element={<><Navbar /><LoginPage /></>} />
        <Route path="/" element={<HomePage />} />
        <Route path="/admin">
          <Route index element={<><AdminNavbar/><AdminPage /></>}/>
          <Route path="addproduct" element={<><AdminNavbar/><AddProductPage /></>} />
        </Route>
      </Routes>
    </>
  );
}

export default App;

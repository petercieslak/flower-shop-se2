import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";
import RegisterPage from "./Pages/RegisterPage";

import { Routes, Route } from "react-router-dom";


function App() {
  return (
    <div className="w-screen h-screen flex justify-center">
      {/* <Navbar /> */}
      <Routes>
        <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
        <Route path="/login" element={<><Navbar /><LoginPage /></>} />
        <Route path="/register" element={<><Navbar /><RegisterPage/></>} />
        <Route path="/" element={<HomePage />} />
      </Routes>

      
    </div>
  );
}

export default App;

import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";
import AddProductForm from "./Components/AddProductForm";

import { Routes, Route } from "react-router-dom";
import RegisterPage from "./Pages/RegisterPage";

function App() {
  return (
    <div className="w-screen h-screen flex justify-center">
      {/* <Navbar /> */}
      {/* <Routes>
        <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
        <Route path="/login" element={<><Navbar /><LoginPage /></>} />
        <Route path="/" element={<HomePage />} />
      </Routes> */}
      <Navbar/>
      {/* <AddProductForm /> */}
      <RegisterPage/>
    </div>
  );
}

export default App;

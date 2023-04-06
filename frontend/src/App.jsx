import ProductsPage from "./Pages/ProductsPage";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import Navbar from "./Pages/Navbar";

import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      {/* <Navbar /> */}
      <Routes>
        <Route path="/products" element={<><Navbar /><ProductsPage /></>} />
        <Route path="/login" element={<><Navbar /><LoginPage /></>} />
        <Route path="/" element={<HomePage />} />
      </Routes>
    </>
  );
}

export default App;

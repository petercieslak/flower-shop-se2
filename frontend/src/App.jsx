import HomePage from "./Pages/HomePage";
import LoginPage from "./Pages/LoginPage";
import Navbar from "./Pages/Navbar";
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </>
  );
}

export default App;

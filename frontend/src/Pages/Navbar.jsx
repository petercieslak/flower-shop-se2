import User from "../Icons/User";
import Cart from "../Icons/Cart";
import contact from "../assets/contact.png"
import { NavLink, useNavigate } from "react-router-dom";
import { IdContext, NameContext, TokenContext, TypeContext } from "../ContextStore";
import { useContext, useState } from "react";

function Navbar() {
  const { name, setName } = useContext(NameContext);
  const { token, setToken } = useContext(TokenContext);
  const { id, setId } = useContext(IdContext);
  const { type, setType } = useContext(TypeContext);
 
  const navigate = useNavigate();

  const [showDropdown, setShowDropdown] = useState(false);

  const toggleDropdown = () => {
    setShowDropdown(true);
  };

  const hideDropdown = () => {
    setShowDropdown(false);
  };

  const logoutHandle = () => {
    setToken("");
    setName("");
    setId("");
  };

  const handleGardenClick = () => {
    setType("garden");
    navigate("/products");
  };

  const handleGiftClick = () => {
    setType("gift");
    navigate("/products");
  };

  const handlePottedClick = () => {
    setType("potted");
    navigate("/products");
  };

  return (
    <nav className=" bg-[#F8F2E9] w-screen fixed flex shadow-xl h-12 px-9 items-center justify-between font-montserrat font-medium text-lg">
      <NavLink to={"/"}>
        <p className=" font-bold">KWIACIARNIA</p>
      </NavLink>
      <ul className="flex gap-3">
        <li>
          <button
            onClick={handleGiftClick}
            className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]"
          >
            Prezenty
          </button>
        </li>
        <li>
          <button
            onClick={handlePottedClick}
            className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]"
          >
            Doniczkowe
          </button>
        </li>
        <li>
          <button
            onClick={handleGardenClick}
            className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]"
          >
            Ogrodowe
          </button>
        </li>
      </ul>
      <ul className="flex gap-5">
        <li>
          <NavLink to={"/contact"}>
          <img src={contact} className="w-7 pt-1" />
          </NavLink>
        </li>
        <li>
          <NavLink to={"/cart"}>
            <Cart />
          </NavLink>
        </li>
        <li>
          <div onMouseEnter={toggleDropdown} className=" cursor-pointer">
            <User />
          </div>
          {showDropdown &&
            (name === "" ? (
              <ul
                className=" z-40 absolute right-0 mt-2 w-48 bg-[#F8F2E9] rounded-md shadow-lg border border-black"
                onMouseLeave={hideDropdown}
              >
                <li
                  className="px-4 py-2 hover:bg-gray-100 cursor-pointer rounded-lg"
                  onClick={() => navigate("/login")}
                >
                  Log in
                </li>
              </ul>
            ) : (
              <ul
                className="absolute right-0 mt-2 w-48 bg-[#F8F2E9] rounded-md shadow-lg border border-black"
                onMouseLeave={hideDropdown}
              >
                <li className="px-4 py-2">Hi, {name}!</li>
                <hr className=" border-gray-300" />
                <li className="px-4 py-2">
                  <NavLink to={"/orders"}>Orders</NavLink>
                </li>
                <hr className=" border-gray-300" />
                <li
                  className="px-4 py-2 hover:bg-gray-100 cursor-pointer rounded-b-lg"
                  onClick={logoutHandle}
                >
                  Logout
                </li>
              </ul>
            ))}
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;

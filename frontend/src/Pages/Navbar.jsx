import User from "../Icons/User";
import Cart from "../Icons/Cart";
import { NavLink, useNavigate } from "react-router-dom";
import { NameContext, TokenContext } from "../ContextStore";
import { useContext, useState } from "react";

function Navbar() {
  const {name, setName} = useContext(NameContext);
  const {token, setToken} = useContext(TokenContext);
  const navigate = useNavigate();

  const [showDropdown, setShowDropdown] = useState(false);

  const toggleDropdown = () => {
    if(name === ""){
      navigate('/login');
    } else
      setShowDropdown(!showDropdown);
  }

  const logoutHandle = () =>{
    setToken(""); 
    setName("");
  }

  return (
    <nav className=" bg-[#F8F2E9] w-screen fixed flex shadow-xl h-12 px-9 items-center justify-between font-montserrat font-medium text-lg">
      <NavLink to={"/"}>
        <p className=" font-bold">KWIACIARNIA</p>
      </NavLink>
      <ul className="flex gap-3">
        <li>
          <button className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]">
            Prezenty
          </button>
        </li>
        <li>
          <button className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]">
            Doniczkowe
          </button>
        </li>
        <li>
          <button onClick={() => navigate('/products')} className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]">
            Ogrodowe
          </button>
        </li>
      </ul>
      <ul className="flex gap-5">        
        <li>
          <NavLink to={"/cart"}>
            <Cart />
          </NavLink>
        </li>
        <li>
          <div onClick={toggleDropdown} className=" cursor-pointer">
            <User />
          </div>
          {showDropdown && 
          (
            name === "" ? 
            (
              <></>
            )
            : 
            (
              <ul className="absolute right-0 mt-2 w-48 bg-[#F8F2E9] rounded-md shadow-lg border border-black" on>
                <li className="px-4 py-2">Hi, {name}!</li>
                <hr className=" border-gray-300"/>
                <li className="px-4 py-2 hover:bg-gray-100 cursor-pointer rounded-b-lg" onClick={logoutHandle}>Logout</li>
              </ul>
            ) 
          )}
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;

import User from "../Icons/User";
import Cart from "../Icons/Cart";
import contact from "../assets/contact.png"
import { NavLink, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { IdContext, NameContext, TokenContext, TypeContext } from "../ContextStore";

function Navbar() {
  const { name, setName } = useContext(NameContext);
  const { token, setToken } = useContext(TokenContext);
  const { id, setId } = useContext(IdContext);
  const { type, setType } = useContext(TypeContext);
 
  const navigate = useNavigate();

  const [showDropdown, setShowDropdown] = useState(false);

  const [hasNewsletter, setNewsletter] = useState(false);


  const fetchClient = () => {
    fetch(
      `http://localhost:8080/api/clients/${id}`)
      .then((response) => {
        console.log(response);
        return response.json();
      })
      .then((data) => {
        setNewsletter(data);
      });
  };

  const editNewsletter = (updatedNewsletter) => {
    fetch(`http://localhost:8080/api/clients/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json; charset=UTF-8"
      },
      body: JSON.stringify(updatedNewsletter
      ),
    })
      .then(response => {
        console.log(response);
      })
  }


  const newsHandle = () => {
    const updatedNewsletter = !hasNewsletter;
    setNewsletter(updatedNewsletter);
    editNewsletter(updatedNewsletter);
  };

  

  const toggleDropdown = () => {
    if(name !== ""){
      fetchClient();
    }
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
        <p className="text-[#3B1F2B] font-bold">KWIACIARNIA</p>
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
                <li className="px-4 py-2 text-[#3B1F2B]" >Hi, {name}!</li>
                <hr className="border-1 border-[#3B1F2B]" />
                <li
                  className="px-4 py-2 hover:bg-gray-100 cursor-pointer rounded-b-lg"
                  onClick={newsHandle}
                >
                {hasNewsletter ? (
                <input checked disabled id="link-checkbox" type="checkbox" value="" class="w-4 h-4 mt-2 text-green-600 bg-gray-100 border-gray-300 rounded focus:ring-[#014325]"/>
                ) : (
                  <input disabled  id="link-checkbox" type="checkbox" value="" class="w-4 h-4 mt-2 text-green-600 bg-gray-100 border-gray-300 rounded focus:ring-[#014325]"/>
                )}
                  <label for="link-checkbox" class="ml-2 ">Newsletter</label>
                </li>
                <hr className=" border-b-gray-300" />
                <li className="px-4 py-2  hover:bg-gray-100 cursor-pointer rounded-b-lg">
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

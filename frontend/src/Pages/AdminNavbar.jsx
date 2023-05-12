import { NameContext, TokenContext } from "../ContextStore";
import { useContext, useState } from "react";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function AdminNavbar() {
  const {name, setName} = useContext(NameContext);
  const {token, setToken} = useContext(TokenContext);
  const navigate = useNavigate();

  return (
    <nav className="bg-white shadow-lg fixed top-0 w-screen">
      <div className="flex">
        <div className="ml-6 flex space-x-8">
          <NavLink
            to={"/admin/products"}
            className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700"
          >
            Products
          </NavLink>
          <NavLink
            to={"/admin/orders"}
            className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700"
          >
            Orders
          </NavLink>
          <a
            href="#"
            className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700"
            onClick={()=>{
              setToken(""); 
              setName("");
              navigate('/login');
            }}
          >
            Logout
          </a>
        </div>
      </div>
    </nav>
  );
}

export default AdminNavbar;

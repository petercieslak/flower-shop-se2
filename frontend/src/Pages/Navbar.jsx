import User from "../Icons/User";
import Cart from "../Icons/Cart";
import { NavLink, useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();
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
          <NavLink to={"/login"}>
            <User />
          </NavLink>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;

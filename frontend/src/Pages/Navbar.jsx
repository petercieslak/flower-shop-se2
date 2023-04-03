import User from "../Icons/User"
import Cart from "../Icons/Cart"

function Navbar() {
  return (
    <nav className=" bg-[#F8F2E9] w-screen fixed flex shadow-xl h-12 px-9 items-center justify-between font-montserrat font-medium text-lg">
      <p className=" font-bold">KWIACIARNIA</p>
      <ul className="flex gap-3"> 
        <li>
          <button className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]">Prezenty</button>
        </li>
        <li>
          <button className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]">Doniczkowe</button>
        </li>
        <li>
          <button className=" h-10 w-36 rounded-md hover:bg-gradient-radial from-green-200 via-green-100 to-[#F8F2E9]">Ogrodowe</button>
        </li>
      </ul>
      <ul className="flex gap-5">
        <li>
          <Cart/>
        </li>
        <li>
          <User/>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;

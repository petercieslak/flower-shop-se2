import HomePage from "./HomePage.jsx";
import AdminNavbar from "./AdminNavbar.jsx";
import AdminProductTable from "../Components/AdminProductTable.jsx";
import LoginInput from "../Components/LoginInput.jsx";
import { useContext, useState } from "react";
import { TokenContext } from "../ContextStore.jsx";


function AddProductPage() {
  const [flowerName, setFlowerName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState(0);

  const {token, setToken} = useContext(TokenContext);

  const fetchProducts = () => {
    fetch("http://localhost:8080/api/products", {
      method: "POST",
      body: JSON.stringify({
          "name": flowerName,
          "description": description,
          "image": "",
          "price": price
      }),          
      headers: {
        "Content-type": "application/json; charset=UTF-8",
        "Authorization": "Bearer " + token,
    },
    })
      .then(response => {
        console.log(response);
      })
  }

  const addProduct = () => {
    fetchProducts();
  }

  return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex justify-center items-center">
      <div className="bg-white h-1/2 w-2/3 shadow-xl flex justify-center items-center">
        <div className="w-4/5 mt-5">
          <input type="text" placeholder="Flower name" value={flowerName} onChange={(e)=>{setFlowerName(e.target.value)}} className="w-full p-3 border mb-4" />
          <input type="text" placeholder="Description" value={description} onChange={(e)=>{setDescription(e.target.value)}} className="w-full p-3 border mb-4"/>
          <input type="number" placeholder="Price" value={price} onChange={(e)=>{setPrice(e.target.value)}} className="w-full p-3 border mb-4 "/>
          <button onClick={addProduct} className="bg-[#014325] w-full h-16 text-white rounded-md">Add</button>
        </div>
      </div>
    </div>
  );
}
export default AddProductPage;

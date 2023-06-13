import HomePage from "./HomePage.jsx";
import AdminNavbar from "./AdminNavbar.jsx";
import AdminProductTable from "../Components/AdminProductTable.jsx";
import LoginInput from "../Components/LoginInput.jsx";
import { useContext, useState } from "react";
import { TokenContext } from "../ContextStore.jsx";
import { useNavigate } from "react-router-dom";


function AddProductPage() {
  const [flowerName, setFlowerName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState(0);
  const [type, setType] = useState("");
  const [image, setImage] = useState("");

  const {token, setToken} = useContext(TokenContext);
  const navigate = useNavigate();

  const fetchProducts = () => {
    fetch("http://localhost:8080/api/products", {
      method: "POST",
      body: JSON.stringify({
          "name": flowerName,
          "description": description,
          "flowerType": type,
          "image": image,
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
    navigate('/admin/products');
  }

  const changeFlowerType = (e) => {
    setType(e.target.value);
  }

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();

    reader.onload = function(e) {
      const arrayBuffer = e.target.result;
      const byteArray = new Uint8Array(arrayBuffer);
      const base64String = btoa(String.fromCharCode.apply(null, byteArray));

      setImage(base64String);
    };

    reader.readAsArrayBuffer(file);
  }

  return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex justify-center items-center">
      <div className="bg-white py-10 w-2/3 shadow-xl flex justify-center items-center font-montserrat">
        <div className="w-4/5">
          <input className="block w-full p-3 border mb-4 cursor-pointer " type="file" onChange={handleImageUpload}/>
          <select onChange={changeFlowerType} id="countries" className="w-full p-3 border mb-4">
            <option>Choose flower type</option>
            <option value="gift">gift</option>
            <option value="potted">potted</option>
            <option value="garden">garden</option>
          </select>
          <input type="text" placeholder="Flower name" value={flowerName} onChange={(e)=>{setFlowerName(e.target.value)}} className="w-full p-3 border mb-4" />
          <input type="text" placeholder="Description" value={description} onChange={(e)=>{setDescription(e.target.value)}} className="w-full p-3 border mb-4"/>
          <input type="number" placeholder="Price" value={price} onChange={(e)=>{setPrice(e.target.value)}} className="w-full p-3 border mb-4 "/>
          <button onClick={addProduct} className="hover:scale-105 ease-in-out duration-200 bg-[#014325] w-full h-16 text-white rounded-md">Add</button>
        </div>
      </div>
    </div>
  );
}
export default AddProductPage;

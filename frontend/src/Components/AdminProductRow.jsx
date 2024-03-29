import AdminPage from "../Pages/AdminPage.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import AdminProductTable from "../Components/AdminProductTable.jsx";
import LoginInput from "../Components/LoginInput.jsx";
import { useContext, useState } from "react";
import { TokenContext } from "../ContextStore.jsx";
import {Link} from "react-router-dom";

function AdminProductRow(prod) {  
  const {token, setToken} = useContext(TokenContext);


  const fetchProducts = () => {
    fetch("http://localhost:8080/api/products/" + prod.productId, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json; charset=UTF-8",
        "Authorization": "Bearer " + token,
    },
    })
      .then(response => {
        console.log(response);
      })
  }

  const deleteProduct = () => {
    fetchProducts();
  }

  return (
    <tr className="">

        <td className=" h-8 w-8">
            <img className="object-cover rounded-lg" src={"data:image/png;base64," + prod.image} />
        </td>
        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
      <div className="text-sm text-center font-medium text-gray-900">
                {prod.name}
            </div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
          {prod.price}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
          {prod.description}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
          {prod.quantity}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
        <Link to={prod.productId} className="text-indigo-600 hover:text-indigo-900">
          Edit
        </Link>
        <span className="text-gray-300 mx-2">|</span>
        <a href="#" className="text-red-600 hover:text-red-900" onClick={deleteProduct}>
          Delete
        </a>
      </td>
    </tr>
  );
}
export default AdminProductRow;

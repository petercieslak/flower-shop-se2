import AdminProductRow from "./AdminProductRow.jsx";
import {useContext, useEffect, useState} from "react";
import {TokenContext} from "../ContextStore.jsx";
import ProductComponent from "./ProductComponent.jsx";

function AdminProductTable() {
  const [products, setProducts] = useState([]);
  const [pageSize, setPageSize] = useState(5);
  const {token, setToken} = useContext(TokenContext);

  const fetchProducts = () => {
    fetch(`http://localhost:8080/api/products`)
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(data => {
          setProducts(data);
        })
  }

  useEffect(() => {
    fetchProducts();
  }, [])

  return (
    <div className="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg mt-4">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th
                scope="col"
                className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Image
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Name
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Price
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Description
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Quantity
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Actions
            </th>
          </tr>
        </thead>

        <tbody className="bg-white divide-y divide-gray-200">
        {products.map(product => (
            <AdminProductRow productId={product.productId} description={product.description} name={product.name} price={product.price} image={product.image} quantity={product.quantity}/>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminProductTable;

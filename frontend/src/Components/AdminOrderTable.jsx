import AdminOrderRow from "./AdminOrderRow.jsx";
import {useContext, useEffect, useState} from "react";
import {TokenContext} from "../ContextStore.jsx";
import ProductComponent from "./ProductComponent.jsx";

function AdminOrderTable() {
  const [orders, setOrders] = useState([]);
  const [pageNo, setPageNo] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const {token, setToken} = useContext(TokenContext);

  const fetchOrders = () => {
    fetch(`http://localhost:8080/api/orders?pageNo=${pageNo}&pageSize=${pageSize}`, {
    headers: {
      "Content-type": "application/json; charset=UTF-8",
      "Authorization": "Bearer " + token
    }
    })
    .then(response => {
      console.log(response);
      return response.json();
    })
    .then(data => {
      setOrders(data);
    });
  }

  useEffect(() => {
    fetchOrders();
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
              Status
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Address
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Client
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Products
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
        {orders.map(order => (
            <AdminOrderRow status={order.status}/>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminOrderTable;
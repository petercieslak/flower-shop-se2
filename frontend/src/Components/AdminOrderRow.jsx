import AdminPage from "../Pages/AdminPage.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {Link, useParams} from "react-router-dom";
import { TokenContext } from "../ContextStore.jsx";

function AdminOrderRow(props) {
  const [fullName, setFullName] = React.useState("");
  const [status, setStatus] = React.useState(props.order.status);
  const {token, setToken} = React.useContext(TokenContext);

  const fetchName = () => {
    fetch(
      `http://localhost:8080/utils/${props.order.clientId}`, {
        headers: {
          "Authorization": "Bearer " + token,
      },
      })
      .then((response) => {
        
        return response.text();
      })
      .then((data) => {
        console.log(data);
        setFullName(data);
      });
  };

    const handleStatusChange = () => {
        // Logic to change the order status
        // For example, you can make an API call to update the status on the server

        // Placeholder code to toggle the status between "Pending" and "Completed"

        const newStatus = status == "ACTIVE" ? "ON HOLD" : "ACTIVE";
        fetch(`http://localhost:8080/api/orders/${props.order.orderId}/status`, {
            method: "PUT",
            body: JSON.stringify({
                "orderStatus": newStatus
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": "Bearer " + token,
            },
        })
            .then(response => {
                console.log(response);
            })
        setStatus(newStatus);
    }
  React.useEffect(() => {
    fetchName();
  }, []);

  return (
    <tr>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
      <div className="text-sm text-center font-medium text-gray-900 cursor-pointer" onClick={handleStatusChange}>
                {status}
            </div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
      {props.order.orderId}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        {fullName}
      </td>
      {/* <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        Produkty
      </td> */}
      <td className="px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
        <Link to={props.order.orderId} state={{ products: props.order.products }} className="text-indigo-600 hover:text-indigo-900">
          Edit
        </Link>
        <span className="text-gray-300 mx-2">|</span>
        <a href="#" className="text-red-600 hover:text-red-900">
          Delete
        </a>
      </td>
    </tr>
  );
}
export default AdminOrderRow;
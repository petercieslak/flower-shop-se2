import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {Link} from "react-router-dom";
import { TokenContext } from "../ContextStore.jsx";

function OrderRow(props) {
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
  
  
  return (
    <tr>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
      <div className="text-sm text-center font-medium text-gray-900">
                {props.order.status}
            </div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
      {props.order.orderId}
      </td>
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
export default OrderRow;
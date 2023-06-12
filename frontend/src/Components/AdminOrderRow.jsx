import AdminPage from "../Pages/AdminPage.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {Link} from "react-router-dom";
import { TokenContext } from "../ContextStore.jsx";

function AdminOrderRow(props) {
  const [fullName, setFullName] = React.useState("");
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
  
  React.useEffect(() => {
    fetchName();
  }, []);

  return (
    <tr>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="flex items-center">
          <div className="flex-shrink-0 h-10 w-10"></div>
          <div className="ml-4">
            <div className="text-sm text-center font-medium text-gray-900">
                {props.order.status}
            </div>
          </div>
        </div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        Adres
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        {fullName}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        Produkty
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
        <Link to={props.order.orderId} className="text-indigo-600 hover:text-indigo-900">
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
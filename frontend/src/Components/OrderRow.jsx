import AdminPage from "../Pages/AdminPage.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {Link} from "react-router-dom";


function OrderRow(props) {
  return (
    <tr>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-center font-medium text-gray-900">
        {props.order.clientId}
      </td>
      {/* <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
          {ord.address}
      </td> */}
      {/* <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
          {fullName}
      </td> */}
      {/* <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
          {ord.products}
      </td> */}
      <td className="w-full">
</td>
<td className="w-full">

</td>
      <td className=" px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
        <a href="#" className="text-indigo-600 hover:text-indigo-900">
          Edit
        </a>
        <span className="text-gray-300 mx-2">|</span>
        <a href="#" className="text-red-600 hover:text-red-900">
          Delete
        </a>
      </td>
      
    </tr>
  );
}
export default OrderRow;

{/* <div className="flex items-center">
          <div className="flex-shrink-0 h-10 w-10"></div>
          <div className="ml-4">
            <div className="text-sm text-center font-medium text-gray-900">
                {ord.status}
            </div>
          </div>
        </div> */}
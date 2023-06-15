import AdminPage from "../Pages/AdminPage.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {Link, useParams} from "react-router-dom";
import { TokenContext } from "../ContextStore.jsx";

function AdminComplaintsRow(props) {
  const [fullName, setFullName] = React.useState("");
  const {token, setToken} = React.useContext(TokenContext);

  const fetchName = () => {
    fetch(
      `http://localhost:8080/utils/${props.complaint.clientId}`, {
        headers: {
          "Authorization": "Bearer " + token,
      },
      })
      .then((response) => {
        
        return response.text();
      })
      .then((data) => {
        setFullName(data);
      });
  };

  React.useEffect(() => {
    fetchName();
  }, []);

  return (
    <tr>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        {fullName}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        {props.complaint.topic}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        {props.complaint.description}
      </td>
    </tr>
  );
}
export default AdminComplaintsRow;
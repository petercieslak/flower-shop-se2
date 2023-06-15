import AdminOrderRow from "./AdminOrderRow.jsx";
import {useContext, useEffect, useState} from "react";
import {TokenContext} from "../ContextStore.jsx";
import ProductComponent from "./ProductComponent.jsx";
import AdminComplaintsRow from "./AdminComplaintsRow.jsx";

function AdminComplaintsTable() {
  const [complaints, setComplaints] = useState([]);
  const {token, setToken} = useContext(TokenContext);

  const fetchComplaints = () => {
    fetch(`http://localhost:8080/api/complaint`, {
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
      setComplaints(data);
    });
  }

  useEffect(() => {
    fetchComplaints();
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
              User
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Topic
            </th>
            <th
              scope="col"
              className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Description
            </th>
          </tr>
        </thead>

        <tbody className="bg-white divide-y divide-gray-200">
        {complaints.map(complaint => (
            <AdminComplaintsRow complaint={complaint}/>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminComplaintsTable;
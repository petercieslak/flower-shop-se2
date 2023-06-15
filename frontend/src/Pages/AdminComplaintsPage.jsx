import AdminOrderTable from "../Components/AdminOrderTable.jsx";
import {Link} from "react-router-dom";
import { TokenContext } from "../ContextStore";
import AdminComplaintsTable from "../Components/AdminComplaintsTable.jsx";


function AdminComplaintsPage() {
  return (
    <div className=" mt-20 w-2/3 mx-auto py-6">
      <div className="flex justify-between">
        <h2 className="text-lg leading-6 font-medium text-gray-900">
            User complaints
        </h2>
      </div>
      <AdminComplaintsTable />
    </div>
  );
}
export default AdminComplaintsPage;
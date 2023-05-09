import AdminProductTable from "../Components/AdminProductTable.jsx";
import {Link} from "react-router-dom";
import { TokenContext } from "../ContextStore";


function AdminPage() {
  return (
    <div className=" mt-20 w-2/3 mx-auto py-6">
      <div className="flex justify-between">
        <h2 className="text-lg leading-6 font-medium text-gray-900">
          Products
        </h2>
        <Link to="addproduct" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
          Add Product
        </Link>
      </div>
      <AdminProductTable />
    </div>
  );
}
export default AdminPage;

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
        <Link to="addproduct" className="text-white bg-[#014325] border-0 py-2 px-6 focus:outline-none rounded">
          Add Product
        </Link>
      </div>
      <AdminProductTable />
    </div>
  );
}
export default AdminPage;

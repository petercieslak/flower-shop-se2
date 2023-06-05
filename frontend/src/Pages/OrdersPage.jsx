import AdminOrderTable from "../Components/AdminOrderTable.jsx";
import {Link} from "react-router-dom";
import { TokenContext } from "../ContextStore";


function OrdersPage() {
  return (
    <div className=" mt-20 w-2/3 mx-auto py-6">
      <div className="flex justify-between">
        <h2 className="text-lg leading-6 font-medium text-gray-900">
            Orders
        </h2>
      </div>
      <AdminOrderTable />
    </div>
  );
}
export default OrdersPage;
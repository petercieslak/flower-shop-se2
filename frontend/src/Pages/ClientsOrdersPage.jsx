import OrderTable from "../Components/OrderTable.jsx";
import { Link } from "react-router-dom";
import { TokenContext } from "../ContextStore";

function OrdersPage() {
  return (
    <div  className="bg-[#F8F2E9] w-full h-screen flex flex-row flex-wrap justify-center p-10">
      <div className=" mt-0 w-2/3 mx-auto py-6">
        <div className="flex justify-between">
          <h2 className="text-lg leading-6 font-medium text-gray-900">
            Orders
          </h2>
        </div>
        <OrderTable />
      </div>
    </div>
  );
}
export default OrdersPage;

import AdminPage from "./AdminPage.jsx";

function AdminProductRow() {
    return(
        <tr>
            <td className="px-6 py-4 whitespace-nowrap">
                <div className="flex items-center">
                    <div className="flex-shrink-0 h-10 w-10">
                        <!--product image-->
                    </div>
                    <div className="ml-4">
                        <div className="text-sm text-center font-medium text-gray-900">
                            kwiaty
                        </div>
                    </div>
                </div>
            </td>
            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
                <!--product price-->
                131
            </td>
            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
                <!--product quantit-->
                123
            </td>
            <td className="px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
                <a href="#" className="text-indigo-600 hover:text-indigo-900">Edit</a>
                <span className="text-gray-300 mx-2">|</span>
                <a href="#" className="text-red-600 hover:text-red-900">Delete</a>
            </td>
        </tr>
    );
}

export default AdminProductRow;

import HomePage from "./HomePage.jsx";


function AdminPage() {
    return (
    <div>
    <nav className="bg-white shadow-lg">
        <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
            <div className="flex justify-between h-16">
                <div className="flex">
                    <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
                        <a href="#" className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700">Products</a>
                        <a href="#" className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700">Users</a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div className="flex justify-between">
            <h2 className="text-lg leading-6 font-medium text-gray-900">Products</h2>
            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                Add Product
            </button>
        </div>
        <div className="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg mt-4">
            <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                <tr>
                    <th scope="col"
                        className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Name
                    </th>
                    <th scope="col"
                        className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Price
                    </th>
                    <th scope="col"
                        className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Description
                    </th>
                    <th scope="col"
                        className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Actions
                    </th>
                </tr>
                </thead>

                <tbody className="bg-white divide-y divide-gray-200">
                <!-- Product Row -->
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

                </tbody>
            </table>
        </div>
    </div>
        );
}




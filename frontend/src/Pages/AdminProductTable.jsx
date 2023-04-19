import AdminProductRow from "./AdminProductRow.jsx";

function AdminProductTable () {
    return (
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
                <AdminProductRow/>
                </tbody>
            </table>
        </div>
    );
}

export default AdminProductTable;

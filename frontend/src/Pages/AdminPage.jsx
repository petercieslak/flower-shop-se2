import HomePage from "./HomePage.jsx";
import AdminNavbar from "./AdminNavbar.jsx";
import AdminProductTable from "./AdminProductTable.jsx";


function AdminPage() {
    return (
        <div>
        < AdminNavbar />

        <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            <div className="flex justify-between">
                <h2 className="text-lg leading-6 font-medium text-gray-900">Products</h2>
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Add Product
                </button>
            </div>
        <AdminProductTable/>
        </div>

        </div>
        );
}
export default AdminPage;






function AdminNavbar() {
    return (
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
    );
}

export default AdminNavbar;

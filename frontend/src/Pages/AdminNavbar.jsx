function AdminNavbar() {
  return (
    <nav className="bg-white shadow-lg fixed top-0 w-screen">
      <div className="flex">
        <div className="ml-6 flex space-x-8">
          <a
            href="#"
            className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700"
          >
            Products
          </a>
          <a
            href="#"
            className="px-3 py-5 text-gray-900 font-medium hover:text-gray-700"
          >
            Users
          </a>
        </div>
      </div>
    </nav>
  );
}

export default AdminNavbar;

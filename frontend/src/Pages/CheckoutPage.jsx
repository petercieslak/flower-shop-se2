
function CheckoutPage(props) {
    return (
    <div className="grid-rows-4 absolute top-32 left-10">
        <p> Personal information</p>
        <div className="flex flex-row m-5">
            <p className="w-20">Name</p>
            <input type="text" className="border-2 border-gray-300 w-80"></input>
        </div>
        <div className="flex flex-row m-5">
            <p className="w-20">Surname</p>
            <input type="text" className="border-2 border-gray-300 w-80"></input>
        </div>
    </div>
    );
  }
  
  export default CheckoutPage;
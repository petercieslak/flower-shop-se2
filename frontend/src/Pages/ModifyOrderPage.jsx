import HomePage from "./HomePage.jsx";
import AdminNavbar from "./AdminNavbar.jsx";
import AdminProductTable from "../Components/AdminProductTable.jsx";
import LoginInput from "../Components/LoginInput.jsx";
import { useLocation, useParams } from "react-router-dom";
import { useContext, useState } from "react";
import { TokenContext } from "../ContextStore.jsx";


function ModifyOrderPage() {
    const { orderID } = useParams();
    const location = useLocation()
    const { products } = location.state

    const [street, setStreet] = useState("");
    const [zipCode, setZipCode] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [prods, setProds] = useState([]);

    const {token, setToken} = useContext(TokenContext);

    const modifyOrder = () => {
        fetch(`http://localhost:8080/api/orders/${orderID}`, {
          method: "PUT",
          body: JSON.stringify({
            "deliveryAddress": {
                "city": city,
                "country": country,
                "postalCode": zipCode,
                "street": street
              },
              "products": products
          }),          
          headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Authorization": "Bearer " + token,
        },
        })
          .then(response => {
            console.log(response);
          })
      }

    const submit = () => {
        setStreet(street);
        setCity(city);
        setCountry(country);
        setZipCode(zipCode);
        modifyOrder();
    }

    return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex justify-center items-center">
        <div className="bg-white h-1/2 w-2/3 shadow-xl flex justify-center items-center font-montserrat">
        <div className="w-4/5 mt-5">
            <input type="text" placeholder="Street" value={street} onChange={(e)=>{setStreet(e.target.value)}} className="w-full p-3 border mb-4" />
            <input type="text" placeholder="Zip Code" value={zipCode} onChange={(e)=>{setZipCode(e.target.value)}} className="w-full p-3 border mb-4"/>
            <input type="text" placeholder="City" value={city} onChange={(e)=>{setCity(e.target.value)}} className="w-full p-3 border mb-4 "/>
            <input type="text" placeholder="Country" value={country} onChange={(e)=>{setCountry(e.target.value)}} className="w-full p-3 border mb-4 "/>
            <button onClick={submit} className="bg-[#014325] w-full h-16 text-white rounded-md">Modify</button>
        </div>
        </div>
    </div>
    );
}
export default ModifyOrderPage;
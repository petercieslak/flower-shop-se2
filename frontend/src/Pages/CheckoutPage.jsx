import { useState, useContext, useEffect } from "react";
import CheckoutItem from "../Components/CheckoutItem";
import { IdContext, TokenContext } from "../ContextStore";

function CheckoutPage() {
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [phonenumber, setPhonenumber] = useState("");
    const [country, setCountry] = useState("");
    const [city, setCity] = useState("");
    const [postalCode, setPostalcode] = useState("");
    const [address, setAddress] = useState("");
    const [address_2, setAddress_2] = useState("");

    const [issaved, setIssaved] = useState(false);
    const [buttonText, setButtonText] = useState("Save shipping information");
    const {id} = useContext(IdContext);
    const {token} = useContext(TokenContext);
    const [productsCheckout, setProductsCheckout] = useState([])

    const [totalState, setTotalState] = useState(0.0);
    let total = 0.0;

    const calculateTotal = (data) => {
        total = total + data;
        setTotalState(total + totalState);
    }

    const saveShippingInformation = function() {
        if(!issaved)
        {
            setIssaved(true);
            setButtonText("Edit shipping information")
        }
        else
        {
            setIssaved(false);
            setButtonText("Save shipping information")
        }
    }

    const fetchProductsCheckout = () => {
        fetch(`http://localhost:8080/api/cart?userId=${id}`, {
            headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Authorization": "Bearer " + token}
        },)
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then((data) => {
            setProductsCheckout(data);
        })
        .catch((error) => {
            console.log(error);
        });
      };

    useEffect(() => {
        fetchProductsCheckout();
    },[]);

    return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex font-montserrat">
        <div className="columns-1 absolute top-20 right-10">
            {productsCheckout.length > 0 && 
            <div>
                {productsCheckout.map(product => (<CheckoutItem product_id={product.cartProductsId.product} quantity={product.quantity} changeFun={calculateTotal}/>))}
            </div>}
                <p className="absolute right-10">Total: {totalState}</p>
            <div className="flex justify-center items-center">
                <button className="w-80 h-12 mt-8 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat">Proceed to payment</button> 
            </div>  
        </div>
        <div className="absolute top-20 left-10 grid-flow-row gap-5">
            <p className="font-bold">Personal information</p>
            <div className="columns-2 gap-y-10">
                <div>
                    <p className="w-20">name</p>
                    <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setName(e.target.value)}></input>
                </div>
                <div>
                    <p className="w-20">surname</p>
                    <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setSurname(e.target.value)}></input>
                </div>
            </div>
            <div className="my-4">
                <p className="w-20">email</p>
                <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setEmail(e.target.value)}></input>
            </div>
            <div className="my-4">
                <p className="w-30">phone number</p>
                <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setPhonenumber(e.target.value)}></input>
            </div>
            <p className="font-bold mt-8">Shipping information</p>
            <div className="my-0">
                <p className="w-30">country</p>
                <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setCountry(e.target.value)}></input>
            </div>
            <div className="my-4">
                <p className="w-30">city</p>
                <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setCity(e.target.value)}></input>
            </div>
            <div className="my-4">
                <p className="w-30">postal code</p>
                <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setPostalcode(e.target.value)}></input>
            </div>
            <div className="columns-2 gap-y-10">
                <div>
                    <p className="w-20">address</p>
                    <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setAddress(e.target.value)}></input>
                </div>
                <div>
                    <p className="w-20">address 2</p>
                    <input type="text" readOnly={issaved} className="bg-[#F8F2E9] border-2 border-gray-300 w-50 rounded" onChange={e => setAddress_2(e.target.value)}></input>
                </div>
            </div>
            <div className="flex justify-center items-center">
                <button className="w-80 h-12 mt-8 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat" onClick={saveShippingInformation}>{buttonText}</button> 
            </div>
        </div>
    </div>
    );
  }
  
  export default CheckoutPage;
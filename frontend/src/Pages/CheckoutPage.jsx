import { useState } from "react";
import CheckoutItem from "../Components/CheckoutItem";

function CheckoutPage(props) {
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

    function calculateTotal(){
        return (props.one.getAmount()*props.one.getPrice() + props.two.getAmount()*props.two.getPrice())
      }

    return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex font-montserrat">
        <div className="columns-1 absolute top-20 right-10">
            <div><CheckoutItem cartClass={props.one}></CheckoutItem></div>
            <div><CheckoutItem cartClass={props.two}></CheckoutItem></div>
                <p className="absolute right-10">Total: {calculateTotal()}</p>
            <div className="flex justify-center items-center">
                <button className="w-80 h-12 mt-8 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat">Proceed to payment</button> 
            </div>  
        </div>
        <div></div>
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
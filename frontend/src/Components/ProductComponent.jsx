import React, { useState, useContext } from "react"
import { IdContext, TokenContext } from "../ContextStore";

function ProductComponent(props) {
    const [showLogInMessage, setShowLogInMessage] = useState(false);
    const {id} = useContext(IdContext);
    const {token} = useContext(TokenContext);

    function addToCart() {
        if(id == ""){
            setShowLogInMessage(true);
            return;
        }
        let requestOptions = {
            method: 'PUT',
            headers: { "Content-type": "application/json; charset=UTF-8",
            "Authorization": "Bearer " + token, }
        };
        fetch('http://localhost:8080/api/cart?productId=' + props.product_id + '&userId=' + id + '&quantity=' + 1, requestOptions)
    }

    const renderPic = () => {
        if(!showLogInMessage)
        {
            return <img className="object-cover rounded-lg h-80 w-60" src={"data:image/png;base64," + props.image} />
        }
        else{ 
            return <div>
                <h1 className="font-montserrat font-bold text-[#3B1F2B] text-center text-red-600">log in to add to cart</h1>
                <img className="object-cover rounded-lg h-70 w-60" src={"data:image/png;base64," + props.image} />
            </div>
        } 
    }

    return (
    <div className="border-2 border-[#3B1F2B] rounded-lg mx-5 m-5 h-[400px]">
        <div className="h-80 w-60">
            {renderPic()}          
        </div>
        <div className="flex justify-between items-center pt-2 pl-1 pr-1 pb-2">
            <h1 className="font-montserrat font-bold text-[#3B1F2B] ml-5">{props.name}</h1>
            <h1 className="font-montserrat font-bold text-[#3B1F2B] mr-5">${props.price}</h1>
        </div>
        <div className="flex justify-center pb-2">
            <button className="hover:scale-105 ease-in-out duration-200 bg-[#014325] text-[#F8F2E9] pl-10 pr-10 rounded-lg font-semibold	" onClick={addToCart}>Add to cart</button>
        </div>
    </div>
    );
}

export default ProductComponent
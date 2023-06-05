import { useState } from "react";
import flower_cart from "../assets/flower-cart.png";
import trash from "../assets/trash.png"

function CheckoutItem(props) {

    return (
        <div className="flex items-center">
            <img src={props.cartClass.getPicture()} className="w-32" />
            <p className="text-m font-normal m-5 w-40">{props.cartClass.getName()}</p>
            <p className="text-center font-normal w-10">{props.cartClass.getAmount()}</p>
            <p className="m-5 w-20 text-center">{props.cartClass.getPrice()}</p>         
        </div>
    );
}

export default CheckoutItem;
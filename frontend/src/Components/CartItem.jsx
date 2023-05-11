import { useState } from "react";
import flower_cart from "../assets/flower-cart.png";
import trash from "../assets/trash.png"

function CartItem(props) {
    const [currentAmount, setCurrentAmount] = useState(props.cartClass.getAmount())
    
    function addClick() {
        props.cartClass.addAmount();
        setCurrentAmount(currentAmount+1);
        props.changeFun();
    }

    function subtractClick() {
        if(currentAmount > 1) {
            props.cartClass.subtractAmount();
            setCurrentAmount(currentAmount-1);
            props.changeFun() 
        }
    }

    return (
        <div className="flex items-center">
            <img src={props.cartClass.getPicture()} className="w-32" />
            <p className="text-m font-normal m-5 w-48">{props.cartClass.getName()}</p>
            <button className="text-m font-semibold w-5" onClick={addClick}>+</button>
            <p className="text-center font-normal w-10">{props.cartClass.getAmount()}</p>
            <button className="text-m font-semibold w-5" onClick={subtractClick}>-</button>
            <p className="m-5 w-20 text-center">{props.cartClass.getPrice()}</p>
            <button className="m-5">
                <img src={trash} className="w-4" />
            </button>          
        </div>
    );
}

export default CartItem;

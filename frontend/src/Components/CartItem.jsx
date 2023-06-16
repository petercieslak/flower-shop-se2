import { useState, useContext, useEffect } from "react";
import trash from "../assets/trash.png"
import { IdContext, TokenContext } from "../ContextStore";

function CartItem(props) {
    const [isVisible, setIsVisible] = useState(true);
    const [productData, setProductData] = useState([])
    const [quantity, setQuantity] = useState(props.quantity)
    const {token} = useContext(TokenContext);
    const {id} = useContext(IdContext)

    function addClick() {
        const requestOptions = {
            method: 'PUT',
            headers: { "Content-type": "application/json; charset=UTF-8",
                        "Authorization": "Bearer " + token, }
        };
        fetch('http://localhost:8080/api/cart?productId=' + props.product_id + '&userId=' + id + '&quantity=' + 1, requestOptions)
        setQuantity(quantity+1);
        props.changeFun(productData.price);
    }

    function subtractClick() {
        if(quantity > 1) {
            const requestOptions = {
                method: 'PUT',
                headers: { "Content-type": "application/json; charset=UTF-8",
                        "Authorization": "Bearer " + token, }
            };
            fetch('http://localhost:8080/api/cart?productId=' + props.product_id + '&userId=' + id + '&quantity=' + -1, requestOptions)
            setQuantity(quantity - 1);
            props.changeFun(-productData.price);
        }
        else{
            trashClick();
        }
    }

    function trashClick() {
        const requestOptions = {
            method: 'DELETE',
            headers: { "Content-type": "application/json; charset=UTF-8",
                        "Authorization": "Bearer " + token, }
        };
        fetch('http://localhost:8080/api/cart?productId=' + props.product_id + "&userId=" + id, requestOptions)
        props.changeFun(-quantity*productData.price);
        setQuantity(0);
        setIsVisible(false);
    } 

    const fetchProduct = () => {
        fetch(`http://localhost:8080/api/products/{productID}?id=` + props.product_id)
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then((data) => {
            setProductData(data);
            return data;
        })
        .then((data) => {
            props.changeFun((data.price * quantity)/2);
        })
        .catch((error) => {
            console.log(error);
        })
      }

    useEffect(() => {
        fetchProduct();
      },[]);

    return (
        <div>
        { isVisible
            ?
        <div className="flex items-center">
            <img src={"data:image/png;base64," + productData.image} className="w-32" />
            <p className="text-m font-normal m-5 w-48">{productData.name}</p>
            <button className="text-m font-semibold w-5" onClick={addClick}>+</button>
            <p className="text-center font-normal w-10">{quantity}</p>
            <button className="text-m font-semibold w-5" onClick={subtractClick}>-</button>
            <p className="m-5 w-20 text-center">{productData.price}</p>
            <button className="m-5" onClick={trashClick}>
                <img src={trash} className="w-4" />
            </button>          
        </div>
        :
        <div></div>
        }
    </div>
    );
}

export default CartItem;

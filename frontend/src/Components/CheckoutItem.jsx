import { useState, useEffect } from "react";

function CheckoutItem(props) {
    const [productData, setProductData] = useState([])

    const fetchProduct = () => {
        fetch(`http://localhost:8080/api/products/{productID}?id=${props.product_id}`)
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then((data) => {
            setProductData(data);
            return data;
        })
        .then((data) => {
            props.changeFun((data.price * props.quantity)/2);
        })
        .catch((error) => {
            console.log(error);
        })
      }

    useEffect(() => {
        fetchProduct();
      },[]);

    return (
        <div className="flex items-center">
            <img src={"data:image/png;base64," + productData.image} className="w-32" />
            <p className="text-m font-normal m-5 w-40">{productData.name}</p>
            <p className="text-center font-normal w-10">{props.quantity}</p>
            <p className="m-5 w-20 text-center">{productData.price}</p>         
        </div>
    );
}

export default CheckoutItem;
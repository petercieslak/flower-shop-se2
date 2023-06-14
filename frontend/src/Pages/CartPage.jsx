import { useEffect, useState, useContext, TokenContext } from "react";
import CartItem from "../Components/CartItem";
import { Link } from 'react-router-dom';
import { MailContext } from "../ContextStore";


function CartPage(props) {
  const [totalState, setTotalState] = useState(0.0)
  const [productsCart, setProductsCart] = useState([])
  const {mail} = useContext(MailContext);
  let total = 0.0;

  const calculateTotal = (data) => {
    total = total + data;
    setTotalState(total + totalState);
  }

  const fetchProductsCart = () => {
    fetch(`http://localhost:8080/api/cart?mail=${mail}`)
      .then(response => {
        console.log(response);
        return response.json();
      })
      .then((data) => {
        setProductsCart(data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    fetchProductsCart();
  },[]);

  return (
  <div className="grid-rows-4 absolute top-40 left-1/4 font-montserrat">
    <div className="columns-3">
      <p className="pl-40">Product</p>
      <p className="pl-28 text-center">Amount</p>
      <p className="pl-4">Price</p>
    </div>
    <div className="flex-col justify-items-stretch">
      <hr/> 
      {productsCart.length > 0 && 
        <div>
          {productsCart.map(product => (<CartItem product_id={product.product_id} quantity={product.quantity} changeFun={calculateTotal}/>))}
        </div>}
      <hr/>
    </div>
    <div className="relative left-40 text-center">
      <p>Total: {totalState}</p>
      {totalState > 0 ?
        <Link to={'/checkout'}>
        <button className="bg-zinc-300 rounded"> Checkout </button>
        </Link>
      :
      <div/>
      }
    </div>
  </div>
  );
}

export default CartPage;

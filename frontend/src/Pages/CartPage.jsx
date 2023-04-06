import { useEffect, useState } from "react";
import CartItem from "../Components/CartItem";
import { Link } from 'react-router-dom';


function CartPage(props) {
  const [total, setTotal] = useState(0)
  const [change, setChange] = useState(false)

  function calculateTotal(){
    return (props.one.getAmount()*props.one.getPrice() + props.two.getAmount()*props.two.getPrice())
  }

  function changeFun(){
    setChange(true);
  }

  useEffect(() => {
    setTotal(calculateTotal());
    setChange(false);
  }),[change];

  return (
  <div className="grid-rows-4 absolute top-40 left-1/4">
    <div className="columns-3">
      <p className="pl-40">Product</p>
      <p className="pl-28 text-center">Amount</p>
      <p className="pl-4">Price</p>
    </div>
    <div className="flex-col justify-items-stretch">
      <hr/>
      <CartItem cartClass={props.one} changeFun={changeFun}/>
      <CartItem cartClass={props.two} changeFun={changeFun}/>
      <hr/>
    </div>
    <div className="relative left-40 text-center">
      <p>Total: {total}</p>
      <Link to={'/Checkout'}>
        <button>Checkout</button>
      </Link>
      
    </div>
  </div>
  );
}

export default CartPage;

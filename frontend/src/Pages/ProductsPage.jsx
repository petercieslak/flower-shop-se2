import { useContext, useEffect, useState } from "react";
import ProductComponent from "../Components/ProductComponent";
import { TokenContext } from "../ContextStore";

function ProductsPage() {
  const [products, setProducts] = useState([]);
  const [pageNo, setPageNo] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const {token, setToken} = useContext(TokenContext);

  const fetchProducts = () => {
    fetch(`http://localhost:8080/api/products?pageNo=${pageNo}&pageSize=${pageSize}`)
      .then(response => {
        console.log(response);
        return response.json();
      })
      .then(data => {
        setProducts(data);
      })
  }

  useEffect(() => {
    fetchProducts();
  }, [pageNo])

  return (
    <div className="bg-[#F8F2E9] w-full h-screen flex flex-row flex-wrap justify-center p-10">
      {products.map(product => (
          <ProductComponent image={product.image} name={product.name} price={product.price}/>
      ))}
      <p className="mx-5 fixed bottom-4 left-96" onClick={()=>{setPageNo(pageNo-1)}}>Previous page</p>
      <p className="mx-5 fixed bottom-4 left-9">{token}</p>
      <p className="mx-5 fixed bottom-4 right-96" onClick={()=>{setPageNo(pageNo+1)}}>Next page</p>
      
    </div>
  );
}

export default ProductsPage;

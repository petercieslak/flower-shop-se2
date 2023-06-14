import { useContext, useEffect, useState } from "react";
import ProductComponent from "../Components/ProductComponent";
import { TokenContext, TypeContext } from "../ContextStore";

function ProductsPage(props) {
  const [products, setProducts] = useState([]);
  const [pageNo, setPageNo] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const {token, setToken} = useContext(TokenContext);
  const {type, setType} = useContext(TypeContext);

  const fetchProducts = () => {
    fetch(`http://localhost:8080/api/products?pageNo=${pageNo}&pageSize=${pageSize}&type=${type}`)
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
  }, [pageNo, type])

  return (
    <div className="bg-[#F8F2E9] w-full h-screen flex flex-row flex-wrap justify-center p-10">
      {products.map(product => (
          <ProductComponent image={product.image} name={product.name} price={product.price}/>
      ))}
      <button className="shadow-[0px_22px_70px_4px_rgba(1,67,37,0.5)] w-32 h-10 rounded-md border border-black bg-[#F8F2E9] mx-5 fixed bottom-4 left-96 customshadow" onClick={()=>{setPageNo(pageNo-1)}}>Previous page</button>
      <button className="shadow-[0px_22px_70px_4px_rgba(1,67,37,0.5)] w-32 h-10 rounded-md border border-black bg-[#F8F2E9] mx-5 fixed bottom-4 right-96 customshadow" onClick={()=>{setPageNo(pageNo+1)}}>Next page</button>
      
    </div>
  );
}

export default ProductsPage;

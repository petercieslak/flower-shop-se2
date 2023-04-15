import { useEffect, useState } from "react";
import ProductComponent from "../Components/ProductComponent";

function ProductsPage() {
  const [products, setProducts] = useState([]);

  const fetchProducts = () => {
    fetch("http://localhost:8080/api/products")
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
  }, [])

  return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex justify-center items-center">
      {products.map(product => (
        <div key={product.productId} className="w-400 h-600 max-w-400 max-h-600 border-2 border-[#3B1F2B] rounded-lg overflow-hidden m-20">
          <ProductComponent image={product.image} name={product.name} price={product.price}/>
        </div>
      ))}
    </div>
  );
}

export default ProductsPage;

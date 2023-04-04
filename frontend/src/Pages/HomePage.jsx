import { useEffect, useState } from "react";

function HomePage() {
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
        <div key={product.productId}>{product.name}</div>
      ))}
    </div>
  );
}

export default HomePage;

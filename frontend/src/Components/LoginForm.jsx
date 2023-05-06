import LoginInput from "./LoginInput";
import flowersvector from "../assets/flowers-login.png";
import { Link } from 'react-router-dom';
import { useContext, useState } from "react";
import { TokenContext } from "../ContextStore";


function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const {token, setToken} = useContext(TokenContext);

  const loginHandling = () => {
    fetch("http://localhost:8080/api/v1/auth/authenticate", {
      method: "POST",
      body: JSON.stringify({
          "email": email,
          "password": password,
      }),          
      headers: {
        "Content-type": "application/json; charset=UTF-8",
    },
    })
      .then(response => {
        return response.json();
      }).then((token) => {
        console.log(token.token);
        setToken(token.token);
        console.log("Success logging in.");
      })
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    loginHandling();
    console.log(email);
    console.log(password);
  };

  return (
    <>
      <img src={flowersvector} className=" w-80 -mb-4" />
      <p className="text-3xl font-bold mb-4 font-montserrat text-[#3B1F2B]">Welcome back!</p>
      <form onSubmit={handleSubmit} className="w-3/4 flex items-center flex-col">
        <LoginInput type="email" placeholder="Email address" value={email} onChange={(string)=>{setEmail(string);}}/>
        <LoginInput type="password" placeholder="Password" value={password} onChange={(string)=>{setPassword(string);}}/>
        <button className=" self-end text-[#014325] mt-2 font-normal font-montserrat">
          Forgot Password?
        </button>
        <button
          className="w-full h-12 mt-8 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat"
        >
          Sign In
        </button>
        <p className=" font-montserrat font-light mt-2 text-[#3B1F2B]">
          Don't have an account?{" "}
          <Link to={'/register'} className=" font-bold text-[#014325]">Sign up here</Link>
        </p>
      </form>
    </>
  );
}

export default LoginForm;

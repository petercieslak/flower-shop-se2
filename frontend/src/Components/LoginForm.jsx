import LoginInput from "./LoginInput";
import flowersvector from "../assets/flowers-login.png";
import { Link, useNavigate } from 'react-router-dom';
import { useContext, useState } from "react";
import { IdContext, NameContext, TokenContext } from "../ContextStore";


function LoginForm() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [credInvalid, setCredInvalid] = useState(false);

  const {token, setToken} = useContext(TokenContext);
  const {name, setName} = useContext(NameContext);
  const {id, setId} = useContext(IdContext);

  const loginHandling = () => {
    fetch("http://localhost:8080/api/users/log_in", {
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
        if(response.status == 200)
          return response.json();
        else
          throw new Error(response.statusText);
      }).then((user) => {
        console.log("Success logging in.");
        setToken(user.token);
        
        if(user.role === "[ADMIN]"){
          navigate('/admin/products');
        }else{
          setId(user.id);
          setName(user.name);
          navigate('/products');
        }
      }).catch((e) => {
        console.log("Error when trying to log in: " + e)
        setCredInvalid(true);
      })
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    loginHandling();
  };

  return (
    <>
      <img src={flowersvector} className=" w-80 -mb-4" />
      <p className="text-3xl font-bold mb-4 font-montserrat text-[#3B1F2B]">Welcome back!</p>
      <form onSubmit={handleSubmit} className="w-3/4 flex items-center flex-col">
        <LoginInput id="email" type="email" placeholder="Email address" value={email} onChange={(string)=>{setEmail(string);}}/>
        <LoginInput id="password" type="password" placeholder="Password" value={password} onChange={(string)=>{setPassword(string);}}/>
        <button className=" self-end text-[#014325] mt-2 font-normal font-montserrat">
          Forgot Password?
        </button>
        <p className={`text-[#962b2b] mt-2 font-normal font-montserrat ${credInvalid ? "":"hidden"}`}>Wrong credentials!</p>
        <button
          className="w-full h-12 mt-4 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat"
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

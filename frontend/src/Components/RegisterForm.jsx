import LoginInput from "./LoginInput";
import flowersvector from "../assets/flowers-login.png";
import { useState, useContext } from "react";
import { useNavigate } from 'react-router-dom';
import { TokenContext } from "../ContextStore";

function RegisterForm() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [newsletter, setNewsletter] = useState(false);
  const [password, setPassword] = useState("");
  const {token, setToken} = useContext(TokenContext);

  const registerHandling = () => {
    fetch("http://localhost:8080/api/v1/auth/register", {
      method: "POST",
      body: JSON.stringify({
        "email": email,
        "firstname": name,
        "hasNewsletterOn": newsletter,
        "lastname": surname,
        "password": password
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
        console.log("Success registering.");
        navigate('/products');
      })
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    registerHandling();
  };

  return (
    <>
      <img src={flowersvector} className=" w-80 -mb-4" />
      <p className="text-3xl font-bold mb-4 font-montserrat text-[#3B1F2B]">
        Nice to see you!
      </p>
      <form
        onSubmit={handleSubmit}
        className="w-3/4 flex items-center flex-col"
      >
        <LoginInput type="email" placeholder="Email address" value={email} onChange={(string)=>{setEmail(string);}}/>
        <LoginInput type="text" placeholder="First name" value={name} onChange={(string)=>{setName(string);}}/>
        <LoginInput type="text" placeholder="Last name" value={surname} onChange={(string)=>{setSurname(string);}}/>
        <LoginInput type="password" placeholder="Password" value={password} onChange={(string)=>{setPassword(string);}}/>
        <div className="self-start mt-6">
          <input onChange={()=>{setNewsletter(!newsletter)}} type="checkbox" id="newsletter" />
          <label for="newsletter" className="ml-3 text-[#014325] mt-2 font-normal font-montserrat">Sign up for newsletter</label>
        </div>
        <button className="w-full h-12 mt-8 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat">
          Sign Up
        </button>
      </form>
    </>
  );
}

export default RegisterForm;

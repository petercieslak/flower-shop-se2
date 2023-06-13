import LoginInput from "./LoginInput";
import flowersvector from "../assets/flowers-login.png";
import { useState, useContext } from "react";
import { useNavigate } from 'react-router-dom';
import { NameContext, TokenContext } from "../ContextStore";

function RegisterForm() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [surname, setSurname] = useState("");
  const [newsletter, setNewsletter] = useState(false);
  const [password, setPassword] = useState("");
  const {token, setToken} = useContext(TokenContext);
  const {name, setName} = useContext(NameContext);

  const [credInvalid, setCredInvalid] = useState(false);
  const [emailTaken, setEmailTaken] = useState(false);

  const registerHandling = () => {
    fetch("http://localhost:8080/api/users/register", {
      method: "POST",
      body: JSON.stringify({
        "email": email,
        "firstname": firstName,
        "hasNewsletterOn": newsletter,
        "lastname": surname,
        "password": password
      }),          
      headers: {
        "Content-type": "application/json; charset=UTF-8",
    },
    })
      .then(response => {
        if(response.status == 200)
          return response.json();
        else if(response.status == 403){
          setEmailTaken(true);
          throw new Error(response.statusText);
        } else{
          setCredInvalid(true);
          throw new Error(response.statusText);
        }
      }).then((user) => {
        console.log("Success registering." + user.token);
        setToken(user.token);
        setName(user.name);
        navigate('/products');
      }).catch((e) => {
        console.log("Error when trying to sign up: " + e);
      })
  }

  const handleSubmit = (event) => {
    setCredInvalid(false);
    setEmailTaken(false);
    event.preventDefault();
    console.log(email + firstName + newsletter + surname + password);
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
        <LoginInput id="email" type="email" placeholder="Email address" value={email} onChange={(string)=>{setEmail(string);}}/>
        <LoginInput id="name" type="text" placeholder="First name" value={firstName} onChange={(string)=>{setFirstName(string);}}/>
        <LoginInput id="surname" type="text" placeholder="Last name" value={surname} onChange={(string)=>{setSurname(string);}}/>
        <LoginInput id="password" type="password" placeholder="Password" value={password} onChange={(string)=>{setPassword(string);}}/>
        <div className="self-start mt-6">
          <input onChange={()=>{setNewsletter(!newsletter)}} type="checkbox" id="newsletter" />
          <label htmlFor="newsletter" className="ml-3 text-[#014325] mt-2 font-normal font-montserrat">Sign up for newsletter</label>
        </div>
        <p className={`text-[#962b2b] mt-2 font-normal font-montserrat ${credInvalid ? "":"hidden"}`}>Wrong credentials!</p>
        <p className={`text-[#962b2b] mt-2 font-normal font-montserrat ${emailTaken ? "":"hidden"}`}>User with given email already exist</p>
        <button className="w-full h-12 mt-4 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat">
          Sign Up
        </button>
      </form>
    </>
  );
}

export default RegisterForm;

import LoginInput from "./LoginInput";
import flowersvector from "../assets/flowers-login.png";

function LoginForm() {

  const handleSubmit = (event) => {
    event.preventDefault();
  };

  return (
    <>
      <img src={flowersvector} className=" w-80 -mb-4" />
      <p className="text-3xl font-bold mb-4 font-montserrat text-[#3B1F2B]">Welcome back!</p>
      <form onSubmit={handleSubmit} className="w-3/4 flex items-center flex-col">
        <LoginInput type="email" placeholder="Email address" />
        <LoginInput type="password" placeholder="Password" />
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
          <button className=" font-bold text-[#014325]">Sign up here</button>
        </p>
      </form>
    </>
  );
}

export default LoginForm;

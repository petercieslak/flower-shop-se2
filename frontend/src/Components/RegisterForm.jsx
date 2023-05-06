import LoginInput from "./LoginInput";
import flowersvector from "../assets/flowers-login.png";

function RegisterForm() {
  const handleSubmit = (event) => {
    event.preventDefault();
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
        <LoginInput type="email" placeholder="Email address" />
        <LoginInput type="text" placeholder="First name" />
        <LoginInput type="text" placeholder="Last name" />
        <LoginInput type="password" placeholder="Password" />
        <div className="self-start mt-6">
          <input type="checkbox" id="newsletter" />
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

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
        Nice to meet you!
      </p>
      <form
        onSubmit={handleSubmit}
        className="w-3/4 flex items-center flex-col"
      >
        <LoginInput type="email" placeholder="Email address" />
        <LoginInput type="password" placeholder="Password" />
        <LoginInput type="text" placeholder="Name" />
        <LoginInput type="text" placeholder="Surname" />
        <div className=" self-start text-[#014325] mt-5 font-normal font-montserrat">
          <input type="checkbox" id="newsletter" />
          <label className="ml-3" for="newsletter">
            Sign up for newsletter
          </label>
        </div>
        <button className="w-full h-12 mt-8 bg-[#014325] text-xl font-bold text-white rounded-xl font-montserrat">
          Sign Up
        </button>
      </form>
    </>
  );
}

export default RegisterForm;

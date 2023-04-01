import LoginInput from "./LoginInput";

function LoginForm() {
  return (
    <>
      <p className=" text-3xl font-bold mb-2">Welcome back!</p>
      <form className="w-full flex items-center flex-col">
        <LoginInput type="email" placeholder="Email address" />
        <LoginInput type="password" placeholder="Password" />
      </form>
    </>
  );
}

export default LoginForm;

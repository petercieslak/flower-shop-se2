import LoginForm from "../Components/LoginForm"

function LoginPage() {
  return (
    <div className="bg-[#f5f5f5] w-screen h-screen flex">
        <div className="w-2/5 h-screen flex justify-center items-center flex-col">
          <LoginForm/>
          <p>output</p>
        </div>
        <div className="w-3/5 h-screen bg-leaves-pattern bg-cover"/>
      </div>
  );
}

export default LoginPage;

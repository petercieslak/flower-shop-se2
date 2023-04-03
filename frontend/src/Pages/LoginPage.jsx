import LoginForm from "../Components/LoginForm"

function LoginPage() {
  return (
    <div className="bg-[#F8F2E9] w-screen h-screen flex">
        <div className="w-2/5 h-screen flex justify-center items-center flex-col">
          <LoginForm/>
        </div>
        <div className="w-3/5 h-screen bg-leaves-pattern bg-cover"/>
      </div>
  );
}

export default LoginPage;

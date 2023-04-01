import LoginForm from "../Components/LoginForm"
import LoginInput from "../Components/LoginInput"

function LoginPage() {
  return (
    <div className="bg-[#f5f5f5] w-screen h-screen">
        <div className="w-2/5 h-screen outline flex justify-center items-center flex-col">
          <LoginForm/>
        </div>
      </div>
  );
}

export default LoginPage;

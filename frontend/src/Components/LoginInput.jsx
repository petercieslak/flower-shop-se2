import { useState } from "react";
import OpenEye from "../Icons/OpenEye";
import ClosedEye from "../Icons/ClosedEye";

function LoginInput(props) {
  const [show, setShow] = useState(false);
  const [inputValue, setInputValue] = useState("");

  const showPassword = (passwdVisibility) => {
    setShow(passwdVisibility);
  };

  const changeInputValue = (event) => {
    props.onChange(event.target.value);        
}

  return (
    <div className="relative mt-6 w-full">
      <input
        autoComplete="off"
        id={props.id}
        type={show ? "text" : props.type}
        className="peer bg-transparent font-montserrat placeholder-transparent border-b-2 border-text-[#3B1F2B] h-9 w-full text-gray-900 focus:outline-none focus:border-[#3B1F2B]"
        placeholder={props.placeholder}
        value={props.value}
        onChange={changeInputValue} 
      />
      <label
        htmlFor={props.id}
        className="absolute left-0 -top-3.5 text-[#3B1F2B] font-montserrat text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
      >
        {props.placeholder}
      </label>
      {props.type === "password" ? (
        show ? (
          <ClosedEye showPassword={showPassword} />
        ) : (
          <OpenEye showPassword={showPassword} />
        )
      ) : (
        <></>
      )}
    </div>
  );
}

export default LoginInput;

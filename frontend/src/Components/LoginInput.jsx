import { useState } from "react";
import OpenEye from "../Icons/OpenEye";
import ClosedEye from "../Icons/ClosedEye";

function LoginInput(props) {
  const [show, setShow] = useState(false);

  const showPassword = (passwdVisibility) => {
    setShow(passwdVisibility);
  };

  return (
    <div className="relative mt-6 w-3/4">
      <input
        id={props.type}
        type={show ? "text" : props.type}
        className="peer bg-transparent placeholder-transparent border-b-2 border-gray-300 h-9 w-full text-gray-900 focus:outline-none focus:borer-rose-600"
        placeholder={props.placeholder}
      />
      <label
        htmlFor={props.type}
        className="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
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

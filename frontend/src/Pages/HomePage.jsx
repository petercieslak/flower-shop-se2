import { useEffect, useState } from "react";
import {Link} from "react-router-dom";

function HomePage() {

  return (
    <div className="fixed flex w-screen h-screen overflow-hidden">
        <div className="absolute z-50">
          <div className="font-montserrat 
          font-bold text-[#3B1F2B] text-9xl
          tracking-widest pl-3">
           FLEURY
          </div>
          <div className="font-montserrat 
          font-semiibold text-[#3B1F2B] text-2xl
          tracking-widest pl-5">
            dive into our colorful world
          </div>
        </div>
        <Link to = {"/"} className="flex w-1/3 h-screen 
        bg-[#F8F2E9] border-l border-r border-[#014325] 
        text-[#014325] text-7xl
         hover:scale-105 ease-in-out duration-300 
         hover:z-40
         hover:bg-[#014325] 
         hover:text-[#F8F2E9] 
         hover:text-8xl
         hover:tracking-widest
         flex-col justify-end items-center
         "> 
            <h1 className="font-montserrat font-regular 
              ">Gift</h1>
            <div className="flex flex-col ">
                <img src="/src/assets/flowers-gift.png" alt="" />
            </div>
        </Link>
        <Link to = {"/"} className="flex w-1/3 h-screen 
        bg-[#F8F2E9] border-l border-r border-[#014325] 
        text-[#014325] text-7xl
         hover:scale-105 ease-in-out duration-300 
         hover:z-40
         hover:bg-[#014325] 
         hover:text-[#F8F2E9] 
         hover:text-8xl
         hover:tracking-widest
         flex-col justify-end items-center
         "> 
            <h1 className="font-montserrat font-regular 
              ">Potted</h1>
            <div className="flex flex-col ">
                <img src="/src/assets/flowers-potted.png" alt="" />
            </div>
        </Link>
        <Link to = {"/"} className="flex w-1/3 h-screen 
        bg-[#F8F2E9] border-l border-r border-[#014325] 
        text-[#014325] text-7xl
         hover:scale-105 ease-in-out duration-300 
         hover:z-40
         hover:bg-[#014325] 
         hover:text-[#F8F2E9] 
         hover:text-8xl
         hover:tracking-widest
         flex-col justify-end items-center
         "> 
            <h1 className="font-montserrat font-regular 
              ">Garden</h1>
            <div className="flex flex-col ">
                <img src="/src/assets/flowers-garden.png" alt="" />
            </div>
        </Link>
    </div>
  );
}

export default HomePage;

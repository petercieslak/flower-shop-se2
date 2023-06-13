import React from "react";
import { NavLink, useNavigate } from "react-router-dom";

function ContactPage() {
    const navigate = useNavigate();
  return (
<div className="bg-[#F8F2E9] w-full h-screen flex flex-row flex-wrap justify-center ">
<div class="container px-40 py-24 mx-aut flex-auto">
    <div class=" border-2 border-[#3B1F2B] bg-[#F8F2E9] rounded-lg p-8 flex flex-col md:ml-auto w-full mt-10 md:mt-0 z-0 relative shadow-2xl">
      <h2 class="text-gray-900 text-lg mb-1 font-medium title-font">Contact us</h2>
      <p class="leading-relaxed mb-5 text-gray-600">Send us an email with your questions/problems. We will send a response to the provided email within 48 hours!</p>
      <div class="relative mb-4">
        <label for="email" class="leading-7 text-sm text-gray-600">Email</label>
        <input type="email" id="email" name="email" class="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
      </div>
      <div class="relative mb-4">
        <label for="message" class="leading-7 text-sm text-gray-600">Message</label>
        <textarea id="message" name="message" class="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 h-32 text-base outline-none text-gray-700 py-1 px-3 resize-none leading-6 transition-colors duration-200 ease-in-out"></textarea>
      </div>
      <button class="text-white bg-[#014325] border-0 py-2 px-6 focus:outline-none rounded" onClick={() => navigate("/products")}>Send</button>
    </div>
  </div>
    </div>
  );
}
export default ContactPage;

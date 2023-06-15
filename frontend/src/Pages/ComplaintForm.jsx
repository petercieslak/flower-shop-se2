import { NavLink, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import {IdContext, TokenContext} from "../ContextStore";
function ComplaintForm() {
    const navigate = useNavigate();
    const { id, setId } = useContext(IdContext);
    const { token, setToken } = useContext(TokenContext);

    const handleSubmit = (event) => {
        event.preventDefault();
        // Get the form data
        const title = event.target.title.value;
        const description = event.target.description.value;

        // Handle the complaints data as desired
        fetch("http://localhost:8080/api/complaint/add", {
            method: "POST",
            body: JSON.stringify({
                "topic": title,
                "description": description,
                "clientId": id
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": "Bearer " + token,
            },
        })
            .then(response => {
                console.log(response);
            })

        // Redirect to the products page after submitting
        navigate("/products");
    };

    return (
        <div className="bg-[#F8F2E9] w-full h-screen flex flex-row flex-wrap justify-center">
            <div className="container px-40 py-24 mx-auto flex-auto">
                <div className="border-2 border-[#3B1F2B] bg-[#F8F2E9] rounded-lg p-8 flex flex-col md:ml-auto w-full mt-10 md:mt-0 z-0 relative shadow-2xl">
                    <h2 className="text-gray-900 text-lg mb-1 font-medium title-font">Complaint</h2>
                    <p className="leading-relaxed mb-5 text-gray-600">Send us a complaint. We will response to it within 48 hours!</p>
                    <form onSubmit={handleSubmit}>
                        {/*<div className="relative mb-4">*/}
                        {/*    <label htmlFor="email" className="leading-7 text-sm text-gray-600">Email</label>*/}
                        {/*    <input type="email" id="email" name="email" className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>*/}
                        {/*</div>*/}
                        <div className="relative mb-4">
                            <label htmlFor="title" className="leading-7 text-sm text-gray-600">Title</label>
                            <input type="text" id="title" name="title" className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                        </div>
                        <div className="relative mb-4">
                            <label htmlFor="description" className="leading-7 text-sm text-gray-600">Description</label>
                            <textarea id="description" name="description" className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 h-32 text-base outline-none text-gray-700 py-1 px-3 resize-none leading-6 transition-colors duration-200 ease-in-out"></textarea>
                        </div>
                        <button type="submit" className="text-white bg-[#014325] border-0 py-2 px-6 focus:outline-none rounded">Send</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default ComplaintForm;
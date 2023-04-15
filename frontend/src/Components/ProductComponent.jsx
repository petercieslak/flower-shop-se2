import React from "react"

function ProductComponent(props) {

    return (
    <>
        <div className="h-2/3">
            <img className="w-1/3 object-fill" src={"data:image/png;base64," + props.image} />
        </div>
        <div className="flex justify-between items-center pt-2 pl-1 pr-1 pb-2">
            <h1 className="font-montserrat font-bold text-[#3B1F2B] ml-5">{props.name}</h1>
            <h1 className="font-montserrat font-bold text-[#3B1F2B] mr-5">${props.price}</h1>
        </div>
        <div className="flex justify-center pb-2">
            <button className="bg-[#014325] text-[#F8F2E9] pl-10 pr-10 rounded-lg font-semibold	">Add to cart</button>
        </div>
    </>
    );
}

export default ProductComponent
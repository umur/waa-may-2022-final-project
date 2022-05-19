import React from "react";

const Property = (props) => {

    return(
        <div>

            <p className="title">{props.name}</p>
            <img src={props.image}></img>
            <p>Property Type : {props.type}</p>
            <p>Bedroom : {props.bedroom}</p>
            <p>Bathroom : {props.bathroom}</p>
            <p>Rent Amount : {props.amount}</p>
            <p>Deposit Amount : {props.deposit}</p>
            <p>Location : {props.state}/{props.city}</p>

        </div>
    );

}

export default Property;
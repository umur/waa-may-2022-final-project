import React from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router';
import axios from 'axios';

const PropertyDetails = () => {
    ///begin
    const convertToBase64 = (file) => {
        return new Promise((resolve, reject) => {
            const fileReader = new FileReader();
            fileReader.readAsDataURL(file);
            fileReader.onload = () => {
                resolve(fileReader.result);
            };
            fileReader.onerror = (error) => {
                reject(error);
            };
        });
    };

    const handleFileUpload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        setProperty({ ...property, image: base64 });
    };

    ///finish
    let navigate = useNavigate();
    const routeChange = () => {
        let path = `/login/landlord`;
        navigate(path);
    }
    const [property, setProperty] = useState({
        propertyName: "", streetAddress: "", city: "", state: "", zipCode: 0, propertyType: "",
        numberOfBedrooms: 0, numberOfBathrooms: 0, rentAmount: 0.0, securityDepositAmount: 0.0, image: [""]
    })

    const addProperty = () => {
        alert("Saved successfully");
        routeChange();

    }

    return (
        <div>
            <h3 style={{ color: "Red" }}>Enter Property Details below</h3><br></br>
            <label >Property name:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, propertyName: event.target.value }
                })
            }} /><br></br>
            <label >Stree address:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, streetAddress: event.target.value }
                })
            }} /><br></br>
            <label >City:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, city: event.target.value }
                })
            }} /><br></br>
            <label >State:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, state: event.target.value }
                })
            }} /><br></br>
            <label >Zip Code:</label><br></br>
            <input type="int" style={{ padding: 10 }} required={true} onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, zipCode: event.target.value }
                })
            }} /><br></br>
            <label >Property type:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, propertyType: event.target.value }
                })
            }} /><br></br>
            <label >Number of Bedrooms:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, numberOfBedrooms: event.target.value }
                })
            }} /><br></br>
            <label >Number of Bathrooms:</label><br></br>
            <input type="int" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, numberOfBathrooms: event.target.value }
                })
            }} /><br></br>
            <label >Property name:</label><br></br>
            <input type="int" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, propertyName: event.target.value }
                })
            }} /><br></br>
            <label >Rent Amount: </label><br></br>
            <input type="double" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, rentAmount: event.target.value }
                })
            }} /><br></br>
            <label >Deposit Amount:</label><br></br>
            <input type="double" style={{ padding: 10 }} required onChange={(event) => {
                setProperty((prevState) => {
                    return { ...prevState, securityDepositAmount: event.target.value }
                })
            }} /><br></br>
            <p>Upload pictures  </p>
            <input type="file" label="Image" name="myFile" accept=".jpeg, .png, .jpg" onChange={(e) => handleFileUpload(e)} /> <br></br>
            <button className='btn' style={{ backgroundColor: "green" }} onClick={addProperty}>Add</button>

        </div>
    )
}

export default PropertyDetails
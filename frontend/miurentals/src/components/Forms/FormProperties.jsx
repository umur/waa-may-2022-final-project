import React from "react";
import { ContainerFormProperties } from "./FormProperties.styled";
import { ContainerPSign } from "./FormLogin.styled";
import { ContainerInput } from "./FormLogin.styled";
import { ContainerInputProperties } from "./FormProperties.styled";
import { ContainerSelectStates } from "./FormProperties.styled";
import { ContainerInputShorts } from "./FormProperties.styled";
import { ContainerSelectTypes } from "./FormProperties.styled";
import { statesOptions } from "../../utils/statesOptions";

const FormProperties = () => {
    return (
        <ContainerFormProperties>
            <ContainerPSign>Add Properties</ContainerPSign>
            <ContainerInputProperties placeholder="Propertie Name" name="propertiesName"></ContainerInputProperties>
            <ContainerInputProperties placeholder="Street Name" name="streetName"></ContainerInputProperties>
            <ContainerInputProperties placeholder="City"></ContainerInputProperties>
            <ContainerSelectStates>
            <option>States</option>  
            {statesOptions.map(statesOption =>(

            <option value={statesOption.value}>{statesOption.title}</option>

            ))} 
            
            </ContainerSelectStates>
            <ContainerInputShorts placeholder="Zip Code" name="zipcode"></ContainerInputShorts>
            <ContainerInputShorts placeholder="N° Bedrooms" name="bedrooms"></ContainerInputShorts>
            <ContainerInputShorts placeholder="N° Bathrooms" name="bathrooms"></ContainerInputShorts>
            <ContainerSelectTypes>
                <option>Type House</option>
                <option value="apt">Apartment</option>
                <option value="town">Town House</option>
                <option value="single">Single Home</option>
            </ContainerSelectTypes>
            <ContainerInputShorts placeholder="Rent Amount" name="rentamount"></ContainerInputShorts>
            <ContainerInputShorts placeholder="Security $" name="securityname"></ContainerInputShorts>
            Photos
            <ContainerInputProperties  name="uploadphotos" type="file"></ContainerInputProperties>
            <ContainerSelectStates>
                <option>Last Rented By</option>
                <option value="apt">Jack</option>
                <option value="town">Jhon</option>
                <option value="single">Mary</option>
            </ContainerSelectStates>
            <ContainerSelectStates>
                <option>Owned By</option>
                <option value="apt">Jack</option>
                <option value="town">Jhon</option>
                <option value="single">Mary</option>
            </ContainerSelectStates>
            <ContainerSelectStates>
                <option>Is Occupied</option>
                <option value="yes">Yes</option>
                <option value="no">No</option>
            </ContainerSelectStates>
        </ContainerFormProperties>
    )
}

export default FormProperties;
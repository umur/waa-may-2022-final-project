
import axios from "axios";
import React, { Fragment, useEffect, useRef, useState } from "react";
import { useNavigate } from 'react-router';
import FileBase64 from 'react-file-base64';
import { getBearer } from "../util/Utility";

const AddProperty = () => {


    const bearer = getBearer();
    const uid =  localStorage.getItem('uid');
    const navigate = useNavigate();
    const data = useRef();
    const [stateSelector, setStateSelector] = useState([]);
    const [citySelector, setCitySelector] = useState([]);
    const [propertyTypeState, setPropertyTypeState] = useState([]);

    const [photoState, setPhotoState] = useState([]);

    const [resultState, setResultState] = useState(
        {
            name: '',
            numberOfBedrooms: 0,
            numberOfBathrooms: 0,
            rentAmount: 0,
            securityDepositAmount: 0,
            photos: [],
            lastRentedBy: {
                id: 1
            },
            ownedBy: {
                id: ''
            },
            city: {
                id: '',
                name: '',
                state: {
                    id: '',
                    name: ''
                }
            },
            rentPeriods: [],
            propertyType: {
                id: '',
                type: ''
            },
            occupied: false
        }
    )

    const persistData = async (event) => {
        event.preventDefault();
        console.log("RESULT: ", resultState);
        persistProperty();


    }

    const getState = async () => {
        let result = await axios.get('http://localhost:8080/api/v1/states', {headers: {Authorization: bearer}});
        setStateSelector(result.data);
    }

    const getPropertyType = async () => {
        let result = await axios.get('http://localhost:8080/api/v1/property-types', {headers: {Authorization: bearer}});
        setPropertyTypeState(result.data);
    }

    const getCity = async (event) => {
        let val = event.target.value;
        console.log("VALUE: ", val)
        let res = val.split(',')[0];
        let result = await axios.get('http://localhost:8080/api/v1/cities/state/' + res, {headers: {Authorization: bearer}});
        setCitySelector(result.data);
        onStateFieldsChanged(event);
    }

    useEffect(() => {
        getState();
        getPropertyType();
    }, [])

    useEffect(() => {
        console.log(photoState);
        onPhotosChanged();
    }, [photoState])

    const persistProperty = async () => {
        let copy = { ...resultState };
        copy['ownedBy'].id = uid;
        setResultState(copy);
        let result = await axios.post('http://localhost:8080/api/v1/properties', resultState, {headers: {Authorization: bearer}});
        navigate('/properties');
    }

    const onPhotosChanged = (event) => {
        let copy = { ...resultState };
        copy['photos'] = photoState;
        setResultState(copy);
    }

    const onFieldsChanged = (event) => {
        let copy = { ...resultState };
        copy[event.target.name] = event.target.value;
        setResultState(copy);
    }

    const onStateFieldsChanged = (event) => {
        let copy = { ...resultState };
        console.log("NAME: ", event.target.value);
        copy['city']['state'].id = event.target.value.split(',')[0];
        copy['city']['state'].name = event.target.value.split(',')[1];
        setResultState(copy);
    }

    const onCityFieldsChanged = (event) => {
        let copy = { ...resultState };
        console.log("NAME: ", event.target.value);
        copy['city'].id = event.target.value.split(',')[0];
        copy['city'].name = event.target.value.split(',')[1];
        setResultState(copy);
    }

    const onPropertyTypeFieldsChanged = (event) => {
        let copy = { ...resultState };
        console.log("NAME: ", event.target.value);
        copy['propertyType'].id = event.target.value.split(',')[0];
        copy['propertyType'].type = event.target.value.split(',')[1];
        setResultState(copy);
    }

    const getFiles = (files) => {
        let base64Files = []
        files.map((f) => {
            base64Files.push(f.base64);
        })
        setPhotoState([...photoState, ...base64Files])
    }


    return (
        <Fragment>
            <h1 className="add-property-title">Add Property</h1>
            <div className="add-property">



                <form ref={data} onSubmit={persistData}>

                    <div >
                        <label htmlFor='name'>Name</label>
                        <input type='text' id='name' name='name' onChange={onFieldsChanged} />
                    </div>

                    <div >
                        <label htmlFor='numberOfBedrooms'>Number of Bedrooms</label>
                        <input type='text' id='numberOfBedrooms' name='numberOfBedrooms' onChange={onFieldsChanged} />
                    </div>

                    <div >
                        <label htmlFor='numberOfBathrooms'>Number of Bathrooms</label>
                        <input type='text' id='numberOfBathrooms' name='numberOfBathrooms' onChange={onFieldsChanged} />
                    </div>

                    <div >
                        <label htmlFor='rentAmount'>Rent Amount</label>
                        <input type='text' id='rentAmount' name='rentAmount' onChange={onFieldsChanged} />
                    </div>

                    <div >
                        <label htmlFor='securityDepositAmount'>Security Deposit Amount</label>
                        <input type='text' id='securityDepositAmount' name='securityDepositAmount' onChange={onFieldsChanged} />
                    </div>

                    <div>
                        <label htmlFor='fileupload'>Images</label>
                        <FileBase64
                            id='fileupload' name='fileupload'
                            multiple={true}
                            onDone={getFiles} />
                    </div>


                    <div>
                        State
                        <select onChange={getCity}>

                            <option defaultValue>Select</option>
                            {stateSelector.map(s => {
                                return (
                                    <option key={s.id} value={[s.id, s.name]}>{s.name}</option>
                                )
                            })}

                        </select>
                    </div>

                    <div>
                        City
                        <select onChange={onCityFieldsChanged}>
                            <option defaultValue>Select</option>
                            {citySelector.map(s => {
                                return (
                                    <option key={s.id} value={[s.id, s.name]} >{s.name}</option>
                                )

                            })}

                        </select>
                    </div>

                    <div>
                        Property Type
                        <select onChange={onPropertyTypeFieldsChanged}>
                            <option defaultValue>Select</option>
                            {propertyTypeState.map(s => {
                                return (
                                    <option key={s.id} value={[s.id, s.type]}>{s.type}</option>
                                )
                            })}

                        </select>
                    </div>

                    <button>Save</button>

                </form>

            </div>
        </Fragment>
    )

}

export default AddProperty;
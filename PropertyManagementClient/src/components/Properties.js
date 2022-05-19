
import React from "react";
import Property from "./Property";
import { Link } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react"
import Filter from "./Filter";
import ReactDOM from 'react-dom';
import { getBearer, getRole } from "../util/Utility";



const Properties = (props) => {

    const bearer = getBearer();
    const role=getRole();
    const uid= localStorage.getItem('uid')
    const [propertyState, setPropertyState] = useState([]);
    //filter
    const [lastTen, setLastTen]= useState(true);
    const [roomFilter, setRoomFilter] = useState(0);
    const [stateFilter, setStateFilter] = useState(0);
    const [cityFilter, setCityFilter] = useState(0);
    const [propertyNotFilterState, setPropertyNotFilterState] = useState([]);
    const onRoomFilterChanged = (event) => {
        let copy = { ...roomFilter };
        copy = event.target.value;
        //console.log("room: ", copy)
        setRoomFilter(copy);
    }
    const onStateFilterChanged = (event) => {
        let copy = { ...stateFilter };
        copy = event.target.value;
        //console.log("state: ", copy, "-", cityFilter)
        setStateFilter(copy);
    }

    const onCityFilterChanged = (event) => {
        let copy = { ...cityFilter };
        copy = event.target.value;
        //console.log("city: ", copy)
        setCityFilter(copy);
    }
    useEffect(() => {
        console.log(localStorage.getItem('token'))
        setCityFilter(0)
    }, [stateFilter])

    const onSearchClicked = () => {
        //let result = await axios.get('http://localhost:8080/api/v1/properties/room/0')//+roomFilter
        if (propertyState.length > propertyNotFilterState.length) {
            setPropertyNotFilterState(propertyState)
            //console.log("set not filtered")
        }
        if (roomFilter != 0) {
            setPropertyState(propertyNotFilterState.filter((p) => p.numberOfBedrooms == roomFilter))
        } else {
            setPropertyState(propertyNotFilterState)
        }
        if (stateFilter != 0) {
            //propertyState.filter( (p) => p[city][state][id]==stateFilter)
            setPropertyState(propertyNotFilterState.filter((p) => p['city']['state']['id'] == stateFilter))
        }
        if (cityFilter != 0) {
            //propertyState.filter( (p) => p[city][state][id]==stateFilter)
            setPropertyState(propertyNotFilterState.filter((p) => p['city']['id'] == cityFilter))
        }
    }
    const onLastTenClicked = async () => {
        setLastTen(!lastTen)
        if(lastTen){
            let result = await axios.get('http://localhost:8080/api/v1/properties/leases', {headers: {Authorization: bearer}})
            setPropertyState(result)
        }else{
            setPropertyState(propertyNotFilterState)
        }
    }
    
    const onSearchResetClicked = () => {
        //let result = await axios.get('http://localhost:8080/api/v1/properties/room/0')//+roomFilter
        if (propertyState.length > propertyNotFilterState.length) {
            setPropertyNotFilterState(propertyState)
            
        }
        setPropertyState(propertyNotFilterState)
        setRoomFilter(0)
        setCityFilter(0)
        setStateFilter(0)
        document.getElementById("stateSelect").selectedIndex = 0
        document.getElementById("citySelect").selectedIndex = 0
        document.getElementById("roomNumberFilter").value ='';
    }



    const fetchProperties = async () => {
        let result;
        if(role==='LANDLORD'){
            result = await axios.get('http://localhost:8080/api/v1/properties/owner/'+uid, {headers: {Authorization: bearer}})
        }else{
            result = await axios.get('http://localhost:8080/api/v1/properties', {headers: {Authorization: bearer}})
        }
        setPropertyState(result.data);
    }

    useEffect(() => {
        fetchProperties();
    }, [])



    const propertyList = propertyState.map(p => {

        return (

            <div key={p.id}>

                <Link to={`${p.id}`} >

                    <Property key={p.id} name={p.name}
                        image={p.photos[0]}
                        type={p.propertyType.type}
                        bedroom={p.numberOfBedrooms}
                        bathroom={p.numberOfBathrooms}
                        amount={p.rentAmount}
                        deposit={p.securityDepositAmount}
                        state={p.city.state.name}
                        city={p.city.name}

                    >


                    </Property>

                </Link>

            </div>



        )

    })


    return <div className="Property-page">
       
        <Filter id="filter"
            roomFilter={roomFilter}
            stateFilter={stateFilter}
            cityFilter={cityFilter}
            roomChange={onRoomFilterChanged}
            stateChanged={onStateFilterChanged}
            cityChanged={onCityFilterChanged}
            onSearchClicked={onSearchClicked}
            onSearchResetClicked={onSearchResetClicked}
            onLastTenClicked={onLastTenClicked}>

        </Filter>
        
        <div className="property-list">
            {propertyList}
        </div>
        
    </div>;


}

export default Properties;


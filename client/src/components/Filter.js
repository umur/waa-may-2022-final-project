import React from "react";
import axios from "axios";
import { useState } from "react"
import { useEffect } from "react";
import { getBearer, getRole } from "../util/Utility";

const Filter = (props) => {
    const bearer = getBearer();
    const [stateSelector, setStateSelector] = useState([]);
    const [citySelector, setCitySelector] = useState([]);

    const getState = async () => {
        let result = await axios.get('http://localhost:8080/api/v1/states', { headers: { Authorization: bearer } });
        setStateSelector(result.data);
    }

    const getCity = async (event) => {

        let val = event.target.value;

        if (val == "0") {
            console.log("event", event.target.value)
            setCitySelector([])
        } else {
            let result = await axios.get('http://localhost:8080/api/v1/cities/state/' + val, { headers: { Authorization: bearer } });
            setCitySelector(result.data);
            props.stateChanged(event);
        }
    }


    useEffect(() => {
        getState();
    }, [])
    useEffect(() => {
        if (props.stateFilter == 0)
            setCitySelector([])
    }, [props.stateFilter])


    return (
        <aside className="filter">
            <div >
                <label htmlFor='roomNumberFilter'>Number of Bedrooms</label>
                <input type='text' id='roomNumberFilter' name='roomNumberFilter' onChange={props.roomChange} />
            </div>
            <div>
                State
                <select onChange={getCity} id="stateSelect">

                    <option defaultValue value="0">Select</option>
                    {stateSelector.map(s => {
                        return (
                            <option key={s.id} value={s.id}>{s.name}</option>
                        )
                    })}

                </select>
            </div>

            <div>
                City
                <select onChange={props.cityChanged} id="citySelect">
                    <option defaultValue value="0">Select</option>
                    {citySelector.map(s => {
                        return (
                            <option key={s.id} value={s.id} >{s.name}</option>
                        )

                    })}

                </select>
            </div>
            <button onClick={props.onSearchClicked}>Search</button>
            <button onClick={props.onSearchResetClicked}>Reset</button>



        </aside>
    );

}

export default Filter; 
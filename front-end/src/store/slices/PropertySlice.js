import { createSlice } from "@reduxjs/toolkit";

const initialState = [];
const propertySlice = createSlice(
    {
        name:'property',
        initialState,
        reducers: {
            get(state,action) {
                console.log(action.payload)
                state = action.payload;
            }
        }
    }
)

export default propertySlice;
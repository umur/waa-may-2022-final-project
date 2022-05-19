import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name:'user',
    initialState: {value:{role:'', email:'', token:'', id:0}},
    reducers:{
        login:(state, action)=>{
            state.value=action.payload
        }
    }

});

export const {login} =userSlice.actions;

export default userSlice.reducer;

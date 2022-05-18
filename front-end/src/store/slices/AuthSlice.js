import { createSlice } from "@reduxjs/toolkit";

const initialAuthState = {isAuthenticated:false,role:""};
const authSlice = createSlice(
    {
        name:'authentication',
        initialState:initialAuthState,
        reducers: {
            loginSuccessful(state, data) {
                state.isAuthenticated = true;
                state.role = data.payload;
            },
            logout(state) {
                state.isAuthenticated = false;
            }
        }
    }
)

export default authSlice;
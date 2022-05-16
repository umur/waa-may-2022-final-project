import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./slices/AuthSlice";
import propertySlice from "./slices/PropertySlice";

const store = configureStore({
    reducer: {
        auth: authSlice.reducer,
        property: propertySlice.reducer
    }
})

export const authActions = authSlice.actions;

export const propertyActions = propertySlice.actions;

export default store;
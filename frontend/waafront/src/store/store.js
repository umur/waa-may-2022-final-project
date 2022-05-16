import { configureStore } from '@reduxjs/toolkit';
import useReducer from '../features/user-slice';

export default configureStore({
    reducer: {
        user: useReducer,

    }
});
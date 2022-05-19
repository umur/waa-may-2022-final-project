import { configureStore } from '@reduxjs/toolkit';
import { setupListeners } from '@reduxjs/toolkit/query';
import useReducer from '../features/user-slice';

import { propertyTypeApi} from '../features/property-type-slice';

export const store = configureStore({
    reducer: {
        user: useReducer,

        [propertyTypeApi.reducerPath]: propertyTypeApi.reducer, 

        // middleware: (getDefaultMiddleware) =>
        // getDefaultMiddleware().concat(propertyTypeApi.middleware),
    },
});

setupListeners(store.dispatch)
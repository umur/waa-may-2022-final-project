import apiURL from '../api/posts';
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

const initialState = {
    id: 0,
    name: ''
}

// const user = useSelector((state) => state.user.value); //token, role, email, id


export const propertyTypeApi = createApi({
    reducerPath: 'propertyTypeApi',

    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080' }),
    endpoints: (builder) => ({
        getAll: builder.query({
            query: () => `/api/v1/property-type`,
        }),

        getBYId: builder.query({
            query: (id) =>  `/api/v1/property-type/${id}`,
            // transformResponse: (response, meta, arg) => response.data,

            
        }),
    }),
})

export const { useGetAllQuery, useGetBYIdQuery } = propertyTypeApi;
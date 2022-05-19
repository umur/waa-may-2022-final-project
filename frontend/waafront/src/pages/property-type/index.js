import React from 'react'
import { useGetAllQuery, useGetBYIdQuery } from '../../features/property-type-slice';


export default function PropertiesType() {

    const { data, error, isLoading } = useGetAllQuery();
    const { dataById, errorById, isLoadingById } = useGetBYIdQuery(1);

    console.log(dataById)
    return (
        <div className="Appp">
            {error ? (
                <>Oh no, there was an error</>
            ) : isLoading ? (
                <>Loading...</>
            ) : data ? (
                <>
                    {
                        data.map((p) => (
                            <h3 key={p.id}>{p.name}</h3>
                        ))
                    }
                </>
            ) : null}
<br/>
<br/>

            <h2>
                    {dataById?.name}
            </h2>
        </div>
    )

}
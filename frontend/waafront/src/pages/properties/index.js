import { useState, useEffect } from 'react';
import axios from "axios";
import PropertiesTable from './propertiesTable';

import { Button } from 'antd';
import { PlusOutlined } from '@ant-design/icons';


function Properties() {

    const [size, setSize] = useState('large');

    const [propertyState, setPropertyState] = useState([
        {
            id: 0,
            name: "",
            street: "",
            city: "",
            state: "",
            zip: "",
            numberOfBedrooms: 0,
            numberOfBathrooms: 0,
            rentAmount: 25,
            securityDepositAmount: 54,
            occupied: false,
            listed: true,
            photos: [],
            propertyType: {
                id: 1,
                name: "Single-Family Homes"
            },
            user: {
                id: 1,
                email: "admin@admin.com",
                firstname: "John",
                lastname: "Doe",
                active: true,
                role: "ADMIN",
                lastLoggedInAt: null
            },
            rent: []
        }

    ]);

    const fetchProducts = async () => {
        const result = await axios.get('http://localhost:8080/api/v1/properties')
        setPropertyState(result.data)
    }

    useEffect(() => {
        fetchProducts();
    }, [])

    return (
        <>
            <div>
                <Button type="primary" icon={<PlusOutlined />} size={size} style={{float: 'right', marginBottom: 20}}>
                    Add Property
                </Button>
            </div>

            <PropertiesTable propertyList={propertyState} ></PropertiesTable>
        </>
    )
}

export default Properties;
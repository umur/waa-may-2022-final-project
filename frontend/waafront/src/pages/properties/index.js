import React from 'react';
import { useState, useEffect } from 'react';
import axios from "axios";
import PropertiesTable from './propertiesTable';

import { Modal, Button } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import { UserOutlined, LockOutlined } from '@ant-design/icons';

import {
    Form,
    Input,
    Radio,
    Select,
    Cascader,
    DatePicker,
    InputNumber,
    TreeSelect,
    Switch,
} from 'antd';

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
        forceUpdate({});
    }, [])

    const showModal = () => {
        setVisible(true);
    };

    const handleOk = () => {
        setModalText('The modal will be closed after two seconds');
        setConfirmLoading(true);
        setTimeout(() => {
            setVisible(false);
            setConfirmLoading(false);
        }, 2000);
    };

    const handleCancel = () => {
        console.log('Clicked cancel button');
        setVisible(false);
    };

    const [visible, setVisible] = React.useState(false);
    const [confirmLoading, setConfirmLoading] = React.useState(false);
    const [modalText, setModalText] = React.useState('Content of the modal');

    const [componentSize, setComponentSize] = useState('default');

    const onFormLayoutChange = ({ size }) => {
        setComponentSize(size);
    };

    const onFinish = (values) => {
        console.log('Finish:', values);
    };

    const [form] = Form.useForm();
    const [, forceUpdate] = useState({});

    return (
        <>
            <div>
                <Button onClick={showModal} type="primary" icon={<PlusOutlined />} size={size} style={{ float: 'right', marginBottom: 20 }}>
                    Add Property
                </Button>
            </div>

            <PropertiesTable propertyList={propertyState} ></PropertiesTable>




            <Modal
                title="Title"
                visible={visible}
                onOk={handleOk}
                confirmLoading={confirmLoading}
                onCancel={handleCancel}
                width={900}
            >
                <Form
                    labelCol={{
                        span: 4,
                    }}
                    wrapperCol={{
                        span: 14,
                    }}
                    layout="inline"
                    initialValues={{
                        size: componentSize,
                    }}
                    onValuesChange={onFormLayoutChange}
                    size={componentSize}

                >

                    <Form.Item
                        name="username"
                        rules={[
                            {
                                required: true,
                                message: 'Please input your username!',
                            },
                        ]}
                    >
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
                    </Form.Item>
                    <Form.Item
                        name="password"
                        rules={[
                            {
                                required: true,
                                message: 'Please input your password!',
                            },
                        ]}
                    >
                        <Input
                            prefix={<LockOutlined className="site-form-item-icon" />}
                            type="password"
                            placeholder="Password"
                        />
                    </Form.Item>
                    <br/>
                    <Form.Item shouldUpdate>
                        {() => (
                            <Button
                                type="primary"
                                htmlType="submit"
                                disabled={
                                    !form.isFieldsTouched(true) ||
                                    !!form.getFieldsError().filter(({ errors }) => errors.length).length
                                }
                            >
                                Log in
                            </Button>
                        )}
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
}

export default Properties;
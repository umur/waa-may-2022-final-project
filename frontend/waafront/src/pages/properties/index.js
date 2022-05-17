import React from "react";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import PropertiesTable from "./propertiesTable";
import { statesOptions } from "./statesOptions";
import { Modal, Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import FormSizeDemo from "./formPropertieStyled";
import formPropertieStyled from "./formPropertieStyled";
import api from "../../api/posts";

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
} from "antd";
import { Alert } from "bootstrap";

let propertiesObjectInitialValue = {
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
    name: "Single-Family Homes",
  },
  user: {
    id: 1,
    email: "admin@admin.com",
    firstname: "John",
    lastname: "Doe",
    active: true,
    role: "ADMIN",
    lastLoggedInAt: null,
  },
  rent: [],
};

function Properties(props) {
  const { propertiesObject } = props;
  const [size, setSize] = useState("large");
  const nameRef = useRef(null);

  const [propertyState, setPropertyState] = useState(propertiesObject);

  const [propertyListState, setPropertyListState] = useState([
    propertiesObject,
  ]);

  const fetchProducts = async () => {
    //const result = await axios.get("http://localhost:8080/api/v1/properties");
    const result = await api.get("api/v1/properties");
    setPropertyListState(result.data);
  };

  useEffect(() => {
    fetchProducts();
    forceUpdate({});
  }, [propertyListState]);

  const showModal = () => {
    setVisible(true);
  };

  const handleOk = async () => {
    try {
      // const { data } = await axios.post(
      //   "http://localhost:8080/api/v1/properties",
      //   propertyState
      // );

      const { data } = await api.post("api/v1/properties", propertyState);
      window.alert("Property added");
      setVisible(false);
      fetchProducts();
      // window.alert("Property added");
      // window.location.replace("http://localhost:3000/properties");
    } catch (e) {
      console.log(e.message);
    }

    // setModalText("The modal will be closed after two seconds");
    // setConfirmLoading(true);
    // setTimeout(() => {
    //   setVisible(false);
    //   setConfirmLoading(false);
    // }, 2000);
  };

  const handleCancel = () => {
    console.log("Clicked cancel button");
    setVisible(false);
  };

  const [visible, setVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  const [modalText, setModalText] = React.useState("Content of the modal");

  const [componentSize, setComponentSize] = useState("default");

  const onFormLayoutChange = ({ size }) => {
    setComponentSize(size);
  };

  const onFinish = (values) => {
    console.log("Finish:", values);
  };

  const [form] = Form.useForm();
  const [, forceUpdate] = useState({});

  const onFieldsChanged = (event) => {
    //alert("I am here");
    let copy = { ...propertyState };
    console.log(copy);
    copy[event.target.name] = event.target.value;
    setPropertyState(copy);
  };

  return (
    <>
      <div>
        <Button
          onClick={showModal}
          type="primary"
          icon={<PlusOutlined />}
          size={size}
          style={{ float: "right", marginBottom: 20 }}
        >
          Add Property
        </Button>
      </div>

      <PropertiesTable propertyList={propertyListState}></PropertiesTable>

      <Modal
        title="Add Property"
        visible={visible}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
        width={900}
      >
        <Form>
          <Form.Item label="Form Size" name="size">
            <Radio.Group>
              <Radio.Button value="small">Small</Radio.Button>
              <Radio.Button value="default">Default</Radio.Button>
              <Radio.Button value="large">Large</Radio.Button>
            </Radio.Group>
          </Form.Item>
          <Form.Item label="Name">
            <Input
              name="name"
              value={propertyState.name}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="Street">
            <Input
              name="street"
              value={propertyState.street}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="City">
            <Input
              name="city"
              value={propertyState.city}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="States">
            <Input
              name="state"
              maxLength={2}
              style={{ width: "7%" }}
              value={propertyState.state}
              onChange={onFieldsChanged}
            />
            {/* <Select
              fieldNames={{
                value: propertyState.state,
                name: propertyState.state,
              }}
              onChange={onFieldsChanged}
            >
              <Select.Option value=""></Select.Option>
              {statesOptions.map((statesOption) => (
                <option value={statesOption.value}>{statesOption.title}</option>
              ))}
            </Select> */}
          </Form.Item>
          <Form.Item label="Zip Code">
            <Input
              name="zip"
              style={{ width: "30%" }}
              value={propertyState.zip}
              onChange={onFieldsChanged}
            />
          </Form.Item>

          <Form.Item label="Number of Bedrooms">
            <InputNumber
              name="numberOfBedrooms"
              style={{ width: "30%" }}
              value={propertyState.numberOfBedrooms}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="Number of Bathrooms">
            <InputNumber
              name="numberOfBathrooms"
              style={{ width: "30%" }}
              value={propertyState.numberOfBathrooms}
              onChange={onFieldsChanged}
            />
          </Form.Item>

          <Form.Item label="Rent Amount">
            <Input
              name="rentAmount"
              style={{ width: "30%" }}
              value={propertyState.rentAmount}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="Security Deposit">
            <Input
              name="securityDepositAmount"
              style={{ width: "30%" }}
              value={propertyState.securityDepositAmount}
              onChange={onFieldsChanged}
            />
          </Form.Item>

          <Form.Item label="Occupied" valuePropName="checked">
            <Switch value={propertyState.occupied} onChange={onFieldsChanged} />
          </Form.Item>
          <Form.Item label="Listed" valuePropName="checked">
            <Switch value={propertyState.listed} onChange={onFieldsChanged} />
          </Form.Item>
          {/* <Form.Item label="Button">
            <Button onClick={myteste()}>Button</Button>
          </Form.Item> */}
        </Form>
      </Modal>
    </>
  );
}
Properties.defaultProps = {
  propertiesObject: propertiesObjectInitialValue,
};
export default Properties;

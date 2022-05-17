import "antd/dist/antd.css";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import axios from "axios";
import React, { useState } from "react";
import { Table, Tag, Space, Switch } from "antd";
import "antd/dist/antd.css";
import { Button, Modal } from "antd";
import { Form, Input, InputNumber } from "antd";

let propertyObjectSave = {
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
function PropertyTable(props) {
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      render: (text) => <a>{text}</a>,
    },
    {
      title: "Street",
      dataIndex: "street",
      key: "street",
    },
    {
      title: "City",
      dataIndex: "city",
      key: "city",
    },
    {
      title: "Type",
      key: "propertyType",
      dataIndex: "propertyType",
      render: (propertyType) => <span>{propertyType.name}</span>,
    },
    {
      title: "Action",
      key: "action",
      render: (text, record) => (
        <Space size="middle">
          <Button
            onClick={() => handleEdit(record)}
            type="primary"
            icon={<EditOutlined />}
          ></Button>
          <Button
            onClick={() => {
              deleteProperty(record);
            }}
            type="danger"
            icon={<DeleteOutlined />}
          ></Button>
        </Space>
      ),
    },
  ];

  const handleEdit = (param) => {
    setPropertyState(param);
    console.log(param);
    setVisible(true);
  };
  const [visible, setVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  const [propertyState, setPropertyState] = useState(propertyObjectSave);

  const onFieldsChanged = (event) => {
    //alert("I am here");
    let copy = { ...propertyState };
    //console.log(copy);
    copy[event.target.name] = event.target.value;
    setPropertyState(copy);
  };

  const handleCancel = () => {
    console.log("Clicked cancel button");
    setVisible(false);
  };

  const handleOk = async () => {
    try {
      const { data } = await axios.put(
        "http://localhost:8080/api/v1/properties/" + propertyState.id,
        propertyState
      );
      window.alert("Property updated");
      window.location.replace("http://localhost:3000/properties");
      setVisible(false);
    } catch (e) {
      console.log(e.message);
    }
  };

  const deleteProperty = async (property) => {
    const id = property.id;
    try {
      const { data } = await axios.delete(
        `http://localhost:8080/api/v1/properties/${id}`
      );
      window.alert("The Properties  was Deleted");
      window.location.replace("http://localhost:3000/properties");
    } catch (e) {}
  };

  return (
    <>
      <Table columns={columns} dataSource={props.propertyList} rowKey="id" />
      <Modal
        title="Update Property Informations"
        visible={visible}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
        width={900}
      >
        <Form>
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
        </Form>
      </Modal>
    </>
  );
}

export default PropertyTable;

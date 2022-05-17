//this is for showing users


import React from "react";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import UsersTable from "./usersTable";

import { Modal, Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";


import api from '../../api/posts'

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

let userObjectSave = {
 
  email: '',
  password: '',
  role: 'ADMIN',
  firstname: '',
  lastname: '',
 
};

let userObjectRender = {
    id:0,
    email: '',
    password: '',
    lastname: '',
    active:'',
    LastLoggedInAt:'',
    role:'',
    rents:{},
    property:{}
  };

function User() {
  const [size, setSize] = useState("large");
  

  const [userState, setUserState] = useState(userObjectSave);

  const [userListState, setUserListState] = useState([
    userObjectRender,
  ]);

  const fetchUsers = async () => {
    //const result = await axios.get("http://localhost:8080/api/v1/properties");
    const result = await api.get('api/v1/users');
    setUserListState(result.data);
    console.log(result.data);
  };

  useEffect(() => {
    fetchUsers();
    forceUpdate({});
  }, []);

  const showModal = () => {
    setVisible(true);
  };

  const handleOk = async () => {
    try {
      // const { data } = await axios.post(
      //   "http://localhost:8080/api/v1/properties",
      //   propertyState
      // );

      const { data } = await api.post('api/v1/users/admin', userState);
      window.alert("User added");
      fetchUsers();
      setVisible(false);
    } catch (e) {
      console.log(e.message);
    }


   
  };

  const handleCancel = () => {
    console.log("Clicked cancel button");
    setVisible(false);
  };

  const [visible, setVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  //const [modalText, setModalText] = React.useState("Content of the modal");

  //const [componentSize, setComponentSize] = useState("default");

  const onFormLayoutChange = ({ size }) => {
    //setComponentSize(size);
  };

  const onFinish = (values) => {
    console.log("Finish:", values);
  };

  const [form] = Form.useForm();
  const [, forceUpdate] = useState({});

  const onFieldsChanged = (event) => {
    //alert("I am here");
    let copy = { ...userState };
    //console.log(copy);
    copy[event.target.name] = event.target.value;
    setUserState(copy);
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
          Add Admin User
        </Button>
      </div>

      <UsersTable userList={userListState}></UsersTable>

       <Modal
        title="Add Admin User"
        visible={visible}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
        width={900}
      >
        <Form>
         
          <Form.Item label="First Name">
            <Input
              name="firstname"
              value={userState.firstname}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="Last Name">
            <Input
              name="lastname"
              value={userState.last}
              onChange={onFieldsChanged}
            />
          </Form.Item>
          <Form.Item label="Email">
            <Input
              name="email"
              value={userState.email}
              onChange={onFieldsChanged}
            />
            
          </Form.Item>
         
          <Form.Item label="Password">
            <Input
              name="password"
              type="password"
              value={userState.password}
              onChange={onFieldsChanged}
            />
            
          </Form.Item>        
          
        </Form>
      </Modal> 
    </>
  );
}

export default User;

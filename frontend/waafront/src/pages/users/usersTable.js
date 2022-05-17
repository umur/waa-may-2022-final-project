
import React, { useState } from "react";
import { Table, Tag, Space, Switch, } from "antd";
import "antd/dist/antd.css";
import { Button, Modal } from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import { Form, Input, } from "antd";
import api from '../../api/posts'

let userObjectSave = {
  email: '',
  active: true,
  role: 'ADMIN',
  firstname: '',
  lastname: '',
};

function UsersTable(props) {

  const columns = [
    {
      title: "First Name",
      dataIndex: "firstname",
      key: "firstname",
      render: (text) => <a>{text}</a>,
    },
    {
      title: "Last Name",
      dataIndex: "lastname",
      key: "lastname",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Role",
      key: "role",
      dataIndex: "role",
      //render: (propertyType) => <span>{propertyType.name}</span>,
    },
    // {
    //   title: "Status",
    //   key: "active",
    //   dataIndex: "active",
    //   render: (propertyType) => <span>{propertyType}</span>

    // },
    {
      title: "Action",
      key: "action",
      render: (text, record) => (
        <Space size="middle">
          <Button type="primary" icon={<EditOutlined />} onClick={() => handleEdit(record)} ></Button>
          <Button
            onClick={deleteUser}
            type="danger"
            icon={<DeleteOutlined />}
          ></Button>
          {/* <Button type="danger" icon={<EditOutlined />} onClick={() => handleResetPassword(record)} ></Button> */}
        </Space>
      ),
    },
  ];

  
  const deleteUser = () => {
    //TODO: deleting the user
  }

  const handleEdit = (param) => {
    setUserState(param);
    console.log(param)
    setVisible(true);
  }
  const [visible, setVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  const [userState, setUserState] = useState(userObjectSave);

  const onFieldsChanged = (event) => {
    //alert("I am here");
    let copy = { ...userState };
    //console.log(copy);
    copy[event.target.name] = event.target.value;
    setUserState(copy);
  };

  const handleCancel = () => {
    console.log("Clicked cancel button");
    setVisible(false);
  };

  const handleOk = async () => {
    try {
     
      const { data } = await api.put('api/v1/users/' + userState.id, userState);
      window.alert("User updated");
      
      setVisible(false);
    } catch (e) {
      console.log(e.message);
    }
  };

  
  return (
    <>
      <Table columns={columns} dataSource={props.userList} rowKey="id" />


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
              value={userState.lastname}
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

          {/* <Form.Item label="Password">
                    <Input
                        name="password"
                        type="password"
                        value={'!%()$$('}
                        onChange={onFieldsChanged}
                    />

                </Form.Item> */}

          <Form.Item label="Active" valuePropName="checked">
            <Switch value={userState.active} onChange={onFieldsChanged} />
          </Form.Item>

        </Form>
      </Modal>

    </>
  )
}

export default UsersTable;

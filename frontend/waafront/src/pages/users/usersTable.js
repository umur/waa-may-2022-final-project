import { Table, Tag, Space } from "antd";
import "antd/dist/antd.css";
import { Button } from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";

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
        <Button type="primary" icon={<EditOutlined />}></Button>
        <Button
          onClick={deletePropertie}
          type="danger"
          icon={<DeleteOutlined />}
        ></Button>
      </Space>
    ),
  },
];

const deletePropertie = async () => {
  // alert(name);
};

export default (props) => (
  <Table columns={columns} dataSource={props.userList} rowKey="id" />
);

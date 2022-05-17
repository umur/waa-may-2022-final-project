import { Table, Tag, Space } from "antd";
import "antd/dist/antd.css";
import { Button } from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import axios from "axios";

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
          onClick={() => {
            updateProperty(record);
          }}
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

const deleteProperty = async (property) => {
  const id = property.id;
  try {
    const { data } = await axios.delete(
      `http://localhost:8080/api/v1/properties/${id}`
    );
    window.alert("The Properties  was Deleted");
    window.location.replace("http://localhost:3000/listusers");
  } catch (e) {}
};

const updateProperty = async (property) => {
  const idPro = property.id;
  console.log(idPro);
  try {
    const { data } = await axios.get(
      `http://localhost:8080/api/v1/properties/2`
    );
  } catch (e) {}
};

export default (props) => (
  <Table columns={columns} dataSource={props.propertyList} rowKey="id" />
);

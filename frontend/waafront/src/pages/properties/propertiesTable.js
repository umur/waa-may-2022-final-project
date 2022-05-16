import { Table, Tag, Space } from "antd";
import "antd/dist/antd.css";
import { Button } from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";

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
  <Table columns={columns} dataSource={props.propertyList} rowKey="id" />
);
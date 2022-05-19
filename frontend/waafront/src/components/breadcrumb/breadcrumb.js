import { Layout, Menu, Breadcrumb } from 'antd';

export default () => (
    <>
        <Breadcrumb
            style={{
                margin: '16px 0',
            }}
        >
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>App</Breadcrumb.Item>
        </Breadcrumb>
    </>
)
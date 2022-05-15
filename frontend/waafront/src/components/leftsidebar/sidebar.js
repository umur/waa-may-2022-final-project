import React from 'react';
import { Layout, Menu } from 'antd';
import { UserOutlined, LaptopOutlined, NotificationOutlined } from '@ant-design/icons';

const { Sider } = Layout;


const items2 = [UserOutlined, LaptopOutlined, NotificationOutlined].map((icon, index) => {
    const key = String(index + 1);
    return {
        key: `sub${key}`,
        // icon: React.createElement(icon),
        label: `Search Filters`,
        // children: new Array(4).fill(null).map((_, j) => {
        //     const subKey = index * 4 + j + 1;
        //     return {
        //         key: subKey,
        //         label: `option${subKey}`,
        //     };
        // }),
    };
});



export default () => (
    <Sider width={300} className="site-layout-background">
        <Menu
            mode="inline"
            defaultSelectedKeys={['1']}
            defaultOpenKeys={['sub1']}
            style={{
                height: '100%',
                borderRight: 0,
            }}
            items={items2}
        />
    </Sider>
)
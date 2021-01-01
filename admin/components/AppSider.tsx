import React from 'react';
import Link from 'next/link';
import { Layout, Menu } from 'antd';
import { BarChartOutlined, UserOutlined } from '@ant-design/icons';

const { Sider } = Layout;

const AdminSider: React.FC = () => {
  return (
    <Layout>
      <Sider style={{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }}>
        <div className="logo" />
        <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
          <Menu.Item key="dashboard" icon={<BarChartOutlined />}>
            <Link href="/dashboard">dashBoard</Link>
          </Menu.Item>

          <Menu.Item key="userInfo" icon={<UserOutlined />}>
            userInfo
          </Menu.Item>
        </Menu>
      </Sider>
    </Layout>
  );
};

export default AdminSider;

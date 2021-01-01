import React from 'react';
import { Layout } from 'antd';
import AdminSider from './AdminSider';
import { GithubOutlined } from '@ant-design/icons';
const { Footer, Content, Header } = Layout;

const AdminLayout: React.FunctionComponent = ({ children }) => {
  //const [isLoggedIn, setIsLoggedIn] = useState(false);
  return (
    <Layout>
      <AdminSider />
      <Layout className="site-layout" style={{ marginLeft: 200 }}>
        <Header className="site-layout-background" style={{ padding: 0 }}>
          {' '}
          currentPAge{' '}
        </Header>
        <Content style={{ margin: '24px 16px 0', overflow: 'initial' }}> {children}</Content>
        <Footer>
          mooMarkAdmin |{' '}
          <a href="https://github.com/blackcowmoo/moo-mark" target="_blank" rel="noreferer noopener">
            <GithubOutlined />
          </a>
        </Footer>
      </Layout>
    </Layout>
  );
};

export default AdminLayout;

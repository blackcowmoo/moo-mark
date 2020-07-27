import Link from 'next/link';
import React, { PureComponent, Fragment } from 'react';
import Layout from '../../components/Layout';
import { GithubOutlined } from '@ant-design/icons';
import { Button, Row, Input, Form } from 'antd';
import './index.module.scss';

const FormItem = Form.Item;

const AdminLoginPage = () => {
  return (
    <div className = 'form'>
    <Fragment>
      <Form>
        <FormItem name="username" rules={[{ required: true }]} hasFeedback>
          <Input placeholder={`AdminName`} />
        </FormItem>
        <FormItem name="password" rules={[{ required: true }]} hasFeedback>
          <Input placeholder={`Password`} />
        </FormItem>
      </Form>
        <div className = 'footer'>
            <a href = 'https://github.com/blackcowmoo/moo-mark'>
                <GithubOutlined/>
            </a>
        </div>
    </Fragment>
    </div>
  );
};

export default AdminLoginPage;

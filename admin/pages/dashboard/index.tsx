import Link from 'next/link'
import React, { PureComponent } from 'react';
import Layout from '../../components/Layout'

export default class IndexPage extends PureComponent{
  render(){
    return  <Layout title="dashboard">
    <h1>Hello Next.js 👋</h1>
    <p>
      <Link href="/about">
        <a>dashboard</a>
      </Link>
    </p>
  </Layout>
  }
} 

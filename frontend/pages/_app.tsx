import Head from 'next/head';
import type { AppProps } from 'next/app';
import Layout from '../components/Layout';
import '@styles/app.scss';

const MooMark = ({ Component, pageProps }: AppProps) => (
  <>
    <Head>
      <title>MooMark 🐮</title>
      <meta name="viewport" content="initial-scale=1.0, width=device-width" />
      <meta charSet="utf-8" />
    </Head>
    <Layout>
      <Component {...pageProps} />
    </Layout>
  </>
);

export default MooMark;

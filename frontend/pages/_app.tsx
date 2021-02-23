import Head from 'next/head';
import Layout from '../components/Layout';
import type { AppProps } from 'next/app';

const MooMark = ({ Component, pageProps }: AppProps) =>{
  return(
    <>
      <Head>
        <title> MooMark </title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta charSet="utf-8" />
      </Head>
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </>
  );
}

export default MooMark;
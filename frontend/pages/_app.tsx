import { Layout } from '@/containers/Layout';
import App, { AppContext, AppInitialProps } from 'next/app';
import Head from 'next/head';
import React from 'react';

// const DISABLE_LAYOUT_PAGES = ['/login'];
const DISABLE_LAYOUT_PAGES: string[] = [];

export default class NextAppLayout extends App {
  public static async getInitialProps({ Component, ctx }: AppContext): Promise<AppInitialProps> {
    let pageProps = {};

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps(ctx);
    }

    return { pageProps };
  }

  public render() {
    const { Component, pageProps, router } = this.props;

    return pageProps.statusCode === 404 || DISABLE_LAYOUT_PAGES.includes(router.route) ? (
      <div>
        <Component router={router} {...pageProps} />
      </div>
    ) : (
      <>
        <Head>
          <meta name="viewport" content="width=device-width, initial-scale=1.0" />
          <title>{pageProps.title || 'Moomark'}</title>
        </Head>
        <Layout>
          <Component router={router} {...pageProps} />
        </Layout>
      </>
    );
  }
}

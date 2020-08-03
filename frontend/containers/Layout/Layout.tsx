import client from '@/lib/Apollo';
import { ApolloProvider } from '@apollo/react-hooks';
import React from 'react';

const Layout: React.FunctionComponent<{}> = (props) => {
  const { children } = props;
  return (
    <ApolloProvider client={client}>
      <main>
        <header>Header</header>
        <section>{children}</section>
      </main>
    </ApolloProvider>
  );
};
export default Layout;

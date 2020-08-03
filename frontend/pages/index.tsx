import React from 'react';
import { useQuery, gql } from '@apollo/client';

const query = gql`
  query {
    servers
  }
`;

const IndexPage: React.FunctionComponent<{}> = (props) => {
  const { loading, error, data } = useQuery(query);

  if (loading) return <p>Loading...</p>;
  if (error) {
    return (
      <p>
        {JSON.stringify({ server: process.env.GRAPHQL_SERVER })} Error... {JSON.stringify(error)}
      </p>
    );
  }

  return (
    <div>
      {JSON.stringify(props)} {data.servers} Index
    </div>
  );
};

export default IndexPage;

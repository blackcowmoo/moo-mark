import { ApolloClient, HttpLink, InMemoryCache, NormalizedCacheObject } from '@apollo/client';

const cache = new InMemoryCache();
const link = new HttpLink({ uri: process.env.GRAPHQL_SERVER });

const client: ApolloClient<NormalizedCacheObject> = new ApolloClient({
  cache,
  link,
});

export default client;

import { ApolloClient, HttpLink, InMemoryCache, NormalizedCacheObject } from '@apollo/client';
import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();
const { GRAPHQL_SERVER } = publicRuntimeConfig;

const cache = new InMemoryCache();
const link = new HttpLink({ uri: GRAPHQL_SERVER });

const client: ApolloClient<NormalizedCacheObject> = new ApolloClient({
  cache,
  link,
});

export default client;

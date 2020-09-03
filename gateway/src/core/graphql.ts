import { ApolloServer } from 'apollo-server-express';
import { typeDefs } from '@/graphql/types';
import { resolvers } from '@/graphql/resolvers';

export default new ApolloServer({
  typeDefs,
  resolvers,
});

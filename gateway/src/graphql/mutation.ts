import { makeExecutableSchema, gql } from 'apollo-server-express';

const typeDefs = gql`
  type Mutation {
    login: String
  }
`;

const resolvers = {
  Mutation: {
    login: async () => 'login',
  },
};

export default makeExecutableSchema({ typeDefs, resolvers });

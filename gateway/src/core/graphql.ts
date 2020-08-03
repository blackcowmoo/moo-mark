import { ApolloServer } from 'apollo-server-express';
import schema from '../graphql';

export default new ApolloServer({ schema });

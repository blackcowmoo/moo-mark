import { gql } from 'apollo-server-express';
import auth from './auth';

const rootSchema = gql`
  type Query {
    healthz: String!
    servers: String!
  }
`;

export const typeDefs = [rootSchema, auth];

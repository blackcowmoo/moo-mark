import { endpoints } from '@/core/config';
import axios from 'axios';
import * as auth from './auth';

const rootResolvers = {
  Query: {
    healthz: () => 'OK',
    servers: async () => {
      if (process.env.NODE_ENV === 'production') {
        throw new Error('Invalid query');
      }

      const pings = await Promise.all(
        Object.entries(endpoints).map(async ([key, { endpoint, healthz }]) => {
          try {
            const result = await axios.get(endpoint + healthz);
            if (result.status >= 400) {
              throw new Error(`Error http status code: ${result.status}`);
            }

            return `${key}: OK`;
          } catch (err) {
            return `${key}: ${err}`;
          }
        }),
      );

      return pings.join('\n');
    },
  },
};

export const resolvers = [rootResolvers, auth];

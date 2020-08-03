import { mergeSchemas } from 'apollo-server-express';
import querySchema from './query';
import mutationSchema from './mutation';

export default mergeSchemas({ schemas: [querySchema, mutationSchema] });

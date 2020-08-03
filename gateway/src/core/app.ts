import express from 'express';
import graphqlServer from './graphql';

const app = express();
app.use(express.json());

// For kubernetes
app.get('/healthz', (req, res) => {
  res.send('OK');
});

graphqlServer.applyMiddleware({ app, path: '/graphql' });

export default app;

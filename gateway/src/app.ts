import express from 'express';

const app = express();

// For kubernetes
app.get('/healthz', (req, res) => {
  res.send('OK');
});

export default app;

import express from 'express';
import { getLog, getPod } from './kubectl';
import { renderLogs } from './page';
import MockData from '../test/data/mock.data';

const app = express();
app.use(express.json());

// For kubernetes
app.get('/healthz', (req, res) => {
  res.send('OK');
});

app.get('/test', (req, res) => {
  res.send(renderLogs(MockData));
});

app.get('/:pod', async (req, res) => {
  try {
    const { pod } = req.params;
    const name = await getPod(pod);
    const logs = await getLog(name);
    res.send(renderLogs(logs));
  } catch (expressError) {
    res.status(500).send(`${expressError.message}:\n${JSON.stringify(expressError)}`);
  }
});

export default app;

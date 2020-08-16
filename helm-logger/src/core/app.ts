import { convert } from '@/utils/html';
import express from 'express';
import { getLog, getPod } from './kubectl';

const app = express();
app.use(express.json());

// For kubernetes
app.get('/healthz', (req, res) => {
  res.send('OK');
});

app.get('/:pod', async (req, res) => {
  try {
    const { pod } = req.params;
    const name = await getPod(pod);
    const logs = await getLog(name);

    const style = '<style>body{background-color:#24292e}b{color:#959da5;font-weight:normal;}pre{margin:0;color:#f6f8fa}</style>';

    res.send(style + logs.map(({ timestamp, text }) => `<pre><b>${timestamp.padEnd(30, ' ')}</b> ${convert.toHtml(text)}</pre>`).join(''));
  } catch (expressError) {
    res.status(500).send(`${expressError.message}:\n${JSON.stringify(expressError)}`);
  }
});

export default app;

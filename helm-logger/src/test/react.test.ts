import { renderLogs } from '@/core/page';
import moment from 'moment';

describe.skip('React DOM', () => {
  it('DOM to HTLM', async () => {
    const time = moment().toISOString();
    const html = renderLogs([{ timestamp: time, text: time }]);
  });
});

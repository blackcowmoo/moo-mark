import { assert } from 'chai';
import { getRequest } from '@/test/utils/app';

describe('Express', () => {
  it('Run', async () => {
    const { status } = await getRequest('/healthz');

    assert.equal(status, 200);
  });
});

import { exec } from '@/utils/exec';
import { assert } from 'chai';

describe('Exec', () => {
  it('Success', async () => {
    const text = 'test';
    const result = await exec(`echo ${text}`);
    assert.equal(text, result.trim());
  });

  it('Error', async () => {
    const failText = 'Comment not found';
    try {
      await exec('commentNotFound');
      assert.fail(failText);
    } catch (err) {
      assert.notEqual(err.message, failText);
    }
  });
});

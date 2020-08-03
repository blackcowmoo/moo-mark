import { assert } from 'chai';
import { graphqlRequest, graphql } from '@/test/utils/app';

describe('GraphQL', () => {
  it('Run', async () => {
    const query = graphql`
      query {
        healthz
      }
    `;

    const { status, body } = await graphqlRequest(query);

    assert.equal(status, 200);
    assert.equal(body.data.healthz, 'OK');
  });
});

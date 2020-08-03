import chai from 'chai';
import chaiHttp from 'chai-http';
import { Response } from 'superagent';

import app from '@/core/app';

chai.use(chaiHttp);

interface ChaiRequest {
  headers?: { [key: string]: string };
  body?: { [key: string]: any };
  query?: { [key: string]: any };
}

export const request = async (method: 'get' | 'post', path: string, { headers, body, query }: ChaiRequest) => {
  const request = chai.request(app)[method](path);

  if (headers) {
    for (const [key, value] of Object.entries(headers)) {
      request.set(key, value);
    }
  }

  if (query) {
    request.query(query);
  }

  if (body) {
    request.send(body);
  }

  return new Promise<Response>((resolve, reject) => {
    request.end((err, res) => {
      if (err) {
        reject(err);
      } else {
        resolve(res);
      }
    });
  });
};

export const getRequest = async (path: string, option: ChaiRequest = {}) => request('get', path, option);
export const postRequest = async (path: string, option: ChaiRequest = {}) => request('post', path, option);

interface GraphqlOption {
  headers?: { [key: string]: string };
  variables?: any;
}

export const graphql = (query: TemplateStringsArray) => query.join('');
export const graphqlRequest = async (query: string, { headers, variables }: GraphqlOption = {}) => {
  const body: any = { query };
  if (variables && Object.keys(variables).length) {
    body.variables = variables;
  }

  return postRequest('/graphql', { headers, body });
};

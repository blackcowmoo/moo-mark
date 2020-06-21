import chai from 'chai';
import chaiHttp from 'chai-http';
import { Response } from 'superagent';

import app from '@/app';

chai.use(chaiHttp);

interface ChaiRequest {
  header?: { [key: string]: string };
  body?: { [key: string]: any };
  query?: { [key: string]: any };
}

export const request = async (method: 'get' | 'post', path: string, { header, body, query }: ChaiRequest) => {
  const request = chai.request(app)[method](path);

  if (header) {
    for (const [key, value] of Object.entries(header)) {
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

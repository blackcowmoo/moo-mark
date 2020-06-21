FROM node:12 as builder

COPY . /node
WORKDIR /node

RUN yarn && yarn build

### Production
FROM node:12-alpine

COPY . /node
COPY --from=builder /node/dist /node/dist

WORKDIR /node

RUN yarn --production

EXPOSE 7000
STOPSIGNAL SIGINT

ENTRYPOINT yarn start
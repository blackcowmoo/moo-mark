FROM node:12 as builder

COPY . /node
WORKDIR /node

RUN yarn && yarn build

### Production
FROM node:12-alpine

COPY . /node
COPY --from=builder /node/build /node/build

WORKDIR /node

RUN yarn global add serve

EXPOSE 5000
STOPSIGNAL SIGINT

ENTRYPOINT serve -s
CMD build

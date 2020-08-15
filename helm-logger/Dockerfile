FROM node:12

RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.17.0/bin/linux/amd64/kubectl && \
    mv /kubectl /usr/local/bin/kubectl && \
    chmod +x /usr/local/bin/kubectl

COPY . /node
WORKDIR /node

RUN yarn && yarn build

EXPOSE 7000
STOPSIGNAL SIGINT

ENTRYPOINT yarn start

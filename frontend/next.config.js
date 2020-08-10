module.exports = {
  publicRuntimeConfig: { GRAPHQL_SERVER: process.env.GRAPHQL_SERVER, GOOGLE_OAUTH_CLIENT_ID: process.env.GOOGLE_OAUTH_CLIENT_ID },
  webpack: (config, { webpack }) => {
    config.plugins.push(new webpack.IgnorePlugin(/\/__tests__\//));
    return config;
  },
};

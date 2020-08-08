module.exports = {
  publicRuntimeConfig: {
    GRAPHQL_SERVER: process.env.GRAPHQL_SERVER,
  },
  webpack: (config, { webpack }) => {
    config.plugins.push(new webpack.IgnorePlugin(/\/__tests__\//));
    return config;
  },
};

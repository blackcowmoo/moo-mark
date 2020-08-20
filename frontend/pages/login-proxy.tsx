import React from 'react';
import { GoogleLogin, GoogleLoginResponse, GoogleLoginResponseOffline } from 'react-google-login';
import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();
const { GOOGLE_OAUTH_CLIENT_ID, DEVELOPMENT_MODE } = publicRuntimeConfig;

const LoginProxyPage: React.FunctionComponent<{}> = () => {
  const onLoginGoogle = (result: GoogleLoginResponse | GoogleLoginResponseOffline) => {
    console.log(result);
  };

  console.log(DEVELOPMENT_MODE);

  return (
    <>
      <GoogleLogin
        clientId={GOOGLE_OAUTH_CLIENT_ID || ''}
        onSuccess={(result) => onLoginGoogle(result)}
        onFailure={(result) => console.log(result)}
        cookiePolicy="single_host_origin"
      />
    </>
  );
};

export default LoginProxyPage;

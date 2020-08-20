import React from 'react';
import { GoogleLogin, GoogleLoginResponse, GoogleLoginResponseOffline } from 'react-google-login';
import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();
const { GOOGLE_OAUTH_CLIENT_ID, DEVELOPMENT_MODE } = publicRuntimeConfig;

const LoginProxyPage: React.FunctionComponent<{}> = () => {
  const receiveMessage = (event: Event) => {
    // Do we trust the sender of this message?  (might be
    // different from what we originally opened, for example).
    console.log(event);
    // if (event.origin !== 'http://example.com') return;

    // event.source is popup
    // event.data is "hi there yourself!  the secret response is: rheeeeet!"
  };

  window.addEventListener('message', receiveMessage, false);

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

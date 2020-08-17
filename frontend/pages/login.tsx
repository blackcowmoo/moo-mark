import React from 'react';
import { GoogleLogin } from 'react-google-login';
import getConfig from 'next/config';
// import { WindowPopup } from '@/components/WindowPopup';

const { publicRuntimeConfig } = getConfig();
const { GOOGLE_OAUTH_CLIENT_ID, DEVELOPMENT_MODE } = publicRuntimeConfig;

const LoginPage: React.FunctionComponent<{}> = () => {
  const onLoginGoogle = (result: any) => {
    console.log(result);
  };

  return (
    <>
      {/* <WindowPopup url="url" name="name" specs={{ status: false }} /> */}

      <p>{{ DEVELOPMENT_MODE }}</p>
      <GoogleLogin
        clientId={GOOGLE_OAUTH_CLIENT_ID || ''}
        onSuccess={(result) => onLoginGoogle(result)}
        onFailure={(result) => console.log(result)}
        cookiePolicy="single_host_origin"
      />
    </>
  );
};

export default LoginPage;

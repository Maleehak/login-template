import { useRef, useState } from 'react';
import useScript from '../hooks/useScript';
import {postGoogleLoginToken} from "../helpers/apis"
import { useNavigate } from 'react-router-dom';

export default function GoogleLogin({logIn}) {
const [loginError, setLoginError] = useState('');
  const googleSignInButton = useRef(null);
  const text = "Sign in "
  const navigate = useNavigate();
  
  const onGoogleSignIn = async res => {
    console.log(res)
    const { credential } = res;
    console.log(credential)
    await postGoogleLoginToken(credential);
    logIn();
    navigate("/")
  };

  useScript('https://accounts.google.com/gsi/client', () => {
    window.google.accounts.id.initialize({
      client_id: "<CLIENT_ID>",
      callback: onGoogleSignIn,
    });
    window.google.accounts.id.renderButton(
      googleSignInButton.current,
      { theme: 'filled_blue', size: 'large', text, width: '250' }, 
    );
  });

  return (
    <>
        <div ref={googleSignInButton} className="group relative w-full flex justify-center px-4 text-sm font-medium rounded-md text-white"></div>
        {loginError && <p style={{ color: 'red' }}>{loginError}</p>}
    </> 
    )
}
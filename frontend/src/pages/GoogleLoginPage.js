import { useNavigate } from 'react-router-dom';
import GoogleLogin from '../components/GoogleLogin';
import {postLoginToken} from "../helpers/apis"

export default function GoogleLoginPage({ logIn }) {
  const navigate = useNavigate();

  const onGoogleSignIn = async res => {
    const { credential } = res;
    await postLoginToken(credential);
    logIn();
    navigate("/")
  };


  return (
    <div>
      <GoogleLogin />
    </div>
  );
}
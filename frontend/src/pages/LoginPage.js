import Header from "../components/Header"
import Login from "../components/Login"
import GoogleLogin from '../components/GoogleLogin';

export default function LoginPage({logIn}){
    return(
        <div className="min-h-full h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-md w-full space-y-8"> 
                <Header
                    heading="Login to your account"
                    paragraph="Don't have an account yet? "
                    linkName="Signup"
                    linkUrl="/signup"
                    />
                <Login logIn={logIn}/>
             
                <div className="group relative w-full flex justify-center px-4 text-sm font-medium rounded-md">OR</div>
                
                <GoogleLogin logIn={logIn}/>
            </div>
        </div>
    )
}
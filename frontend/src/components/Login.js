import { useState } from 'react';
import { loginFields } from "../constants/formFields";
import FormAction from "./FormAction";
import FormExtra from "./FormExtra";
import Input from "./Input";
import {handleLogin} from "../helpers/apis"
import { useNavigate } from "react-router-dom";
const fields=loginFields;

export default function Login({logIn}){
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState('');
    const navigate = useNavigate();

    const handleEmailChange=(e)=>{
        setEmail(e.target.value)
    }

    const handlePasswordChange=(e)=>{
        setPassword(e.target.value)
    }

    const handleSubmit=(e)=>{
        e.preventDefault();
        authenticateUser();
    }

    const authenticateUser = async () =>{
        try {
            await handleLogin(email, password);
            logIn()
            navigate("/");
        } catch (error) {
            setLoginError(error.message);
        }
    }

    return(
        <form className="mt-4 space-y-6" onSubmit={handleSubmit}>
        <div className="-space-y-px">
            <Input
                key={fields["email"].id}
                handleChange={handleEmailChange}
                value={email}
                labelText={fields["email"].labelText}
                labelFor={fields["email"].labelFor}
                id={fields["email"].id}
                name={fields["email"].name}
                type={fields["email"].type}
                isRequired={fields["email"].isRequired}
                placeholder={fields["email"].placeholder}
            />       
            <Input
                key={fields["password"].id}
                handleChange={handlePasswordChange}
                value={password}
                labelText={fields["password"].labelText}
                labelFor={fields["password"].labelFor}
                id={fields["password"].id}
                name={fields["password"].name}
                type={fields["password"].type}
                isRequired={fields["password"].isRequired}
                placeholder={fields["password"].placeholder}
            />
        </div>
        <FormExtra/>
        <FormAction handleSubmit={handleSubmit} text="Login"/>
        {loginError && <p style={{ color: 'red' }}>{loginError}</p>}
      </form>
      
    )
}
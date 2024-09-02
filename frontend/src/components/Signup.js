import { useState } from 'react';
import { signupFields } from "../constants/formFields"
import { useNavigate } from "react-router-dom";
import {handleSignUp} from "../helpers/apis"
import FormAction from "./FormAction";
import Input from "./Input";

const fields= signupFields;

export default function Signup(){
  const [input, setInput] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    password:'',
    password2: '',
    confirmPassword: '',
  });
  const [error, setError] = useState({
    confirmPassword:''
  });
  const [signupError, setSignupError] = useState('');
  const navigate = useNavigate();

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setInput((prev) => ({
      ...prev,
      [name]: value,
    }));
    validateInput(e);
  };

  const validateInput = (e) => {
    let { name, value } = e.target;
    setError((prev) => {
      const stateObj = { ...prev, [name]: '' };
      switch (name) {
        case 'confirmPassword':
          if (input.password && value !== input.password) {
            stateObj[name] = 'Password and Confirm Password does not match.';
          }
          break;

        default:
          break;
      }

      return stateObj;
    });
  };

  const handleSubmit=(e)=>{
    e.preventDefault();
    if(error.confirmPassword === error.password){
      createAccount()
    }   
  }

  const createAccount = async () => {
    try {
      await handleSignUp(input.firstName, input.lastName, input.email, input.password, input.phone)
      navigate("/login");
    } catch (error) {
        setSignupError(error.message);
    }
  }

    return(
        <form className="space-y-2" onSubmit={handleSubmit}>
          <div className="">
          <Input
                key={fields["firstName"].id}
                handleChange={onInputChange}
                value={input.firstName}
                labelText={fields["firstName"].labelText}
                labelFor={fields["firstName"].labelFor}
                id={fields["firstName"].id}
                name={fields["firstName"].name}
                type={fields["firstName"].type}
                isRequired={fields["firstName"].isRequired}
                placeholder={fields["firstName"].placeholder}
            />

            <Input
                key={fields["lastName"].id}
                handleChange={onInputChange}
                value={input.LastName}
                labelText={fields["lastName"].labelText}
                labelFor={fields["lastName"].labelFor}
                id={fields["lastName"].id}
                name={fields["lastName"].name}
                type={fields["lastName"].type}
                isRequired={fields["lastName"].isRequired}
                placeholder={fields["lastName"].placeholder}
            />

            <Input
                key={fields["email"].id}
                handleChange={onInputChange}
                value={input.email}
                labelText={fields["email"].labelText}
                labelFor={fields["email"].labelFor}
                id={fields["email"].id}
                name={fields["email"].name}
                type={fields["email"].type}
                isRequired={fields["email"].isRequired}
                placeholder={fields["email"].placeholder}
            />
            <Input
                key={fields["phone"].id}
                handleChange={onInputChange}
                value={input.phone}
                labelText={fields["phone"].labelText}
                labelFor={fields["phone"].labelFor}
                id={fields["phone"].id}
                name={fields["phone"].name}
                type={fields["phone"].type}
                isRequired={fields["phone"].isRequired}
                placeholder={fields["phone"].placeholder}
            />

            <Input
                key={fields["password"].id}
                handleChange={onInputChange}
                value={input.password}
                labelText={fields["password"].labelText}
                labelFor={fields["password"].labelFor}
                id={fields["password"].id}
                name={fields["password"].name}
                type={fields["password"].type}
                isRequired={fields["password"].isRequired}
                placeholder={fields["password"].placeholder}
            />
            {error.password && <span className="err">{error.password}</span>}

            <Input
                key={fields["confirmPassword"].id}
                handleChange={onInputChange}
                value={input.confirmPassword}
                labelText={fields["confirmPassword"].labelText}
                labelFor={fields["confirmPassword"].labelFor}
                id={fields["confirmPassword"].id}
                name={fields["confirmPassword"].name}
                type={fields["confirmPassword"].type}
                isRequired={fields["confirmPassword"].isRequired}
                placeholder={fields["confirmPassword"].placeholder}
              
            />
           {error.confirmPassword && (<span className="text-sm mt-0 text-red-700">* {error.confirmPassword}</span>)}                 
        </div>
        <FormAction handleSubmit={handleSubmit} text="Signup" />
        {signupError && <p style={{ color: 'red' }}>{signupError}</p>}
      </form>
    )
}
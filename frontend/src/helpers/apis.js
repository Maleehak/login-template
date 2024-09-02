import axios from "axios";

export const handleLogin = async (email, pass) => {

    const loginPayload = {
      email: email,
      password: pass
    }

    try {
        const response = await axios.post("http://localhost:8080/api/v1/auth/login", loginPayload);
        const token  =  response.data.token;
        localStorage.setItem("token", token);
    } catch (error) {
        if (error.response) {
            throw new Error(error.response.data.message || 'Login failed. Please try again.');
        } else if (error.request) {
            throw new Error('No response from the server. Please check your network connection.');
        } else {
            throw new Error('An unexpected error occurred. Please try again.');
        }
    }
};

  export const handleSignUp = async (firstName,lastName, email, pass, phone) => {

    const signupPayload = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: pass,
      phoneNumber: phone
    }

    try {
        await axios.post("http://localhost:8080/api/v1/auth/register", signupPayload);
    } catch (error) {
        if (error.response) {
            throw new Error(error.response.data.message || 'Signup failed. Please try again.');
        } else if (error.request) {
            throw new Error('No response from the server. Please check your network connection.');
        } else {
            throw new Error('An unexpected error occurred. Please try again.');
        }
    }  
  };

  export const postGoogleLoginToken = async (idToken) => {
    try {
      await axios.post("http://localhost:8080/api/v1/auth/login/google",
        { "idToken": idToken },{
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      }
    });
    } catch (e) {
      throw new Error(e.message)
    }
  };

import React , {useState, useEffect} from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";
import SignupPage from "./pages/SignupPage";
import PrivateRoute from "./helpers/PrivateRoute";
import { history } from "./helpers/history";

const App = () => {
  const [userLogged, setUserLogged] = useState(
    JSON.parse(localStorage.getItem("userLogged"))
  );

  useEffect(() => {
    localStorage.setItem("userLogged", JSON.stringify(userLogged));
  }, [userLogged]);

  const logIn = () => setUserLogged(true);
  const logOut = () => setUserLogged(false);

  return (
    <Router history ={history}>
        {" "}
        <Routes>
          <Route element={<PrivateRoute />}>
            <Route path="/" element={<HomePage/>} />
          </Route>
          <Route path="/login" element={<LoginPage logIn={logIn}/>} />
          <Route path="/signup" element={<SignupPage />} />
        </Routes>
    </Router>
  );
};

export default App;

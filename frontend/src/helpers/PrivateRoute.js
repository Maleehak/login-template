import { Outlet, Navigate } from "react-router-dom";

const PrivateRoutes = () => {
  const userLogged = JSON.parse(localStorage.getItem("userLogged"));

  return userLogged ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoutes;
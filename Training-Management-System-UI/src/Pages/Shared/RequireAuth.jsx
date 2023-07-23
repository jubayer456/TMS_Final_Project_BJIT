import { Navigate } from "react-router-dom";

export default function RequireAuth({ children }) {
    let auth = localStorage.getItem('accessToken');
  console.log(auth);
    if (auth === null) {
      return <Navigate to="/login"/>;
    }
  
    return children;
  }
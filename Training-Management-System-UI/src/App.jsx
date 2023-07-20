// import './App.css'
import { RouterProvider, } from "react-router-dom";
import router from "./Routes/Routes";
import toast, { Toaster } from 'react-hot-toast';
import UserProvider from './Context/UserProvider'

function App() {

  return (
    <UserProvider>
      <RouterProvider router={router} />
      <Toaster></Toaster>
    </UserProvider>
  )
}

export default App

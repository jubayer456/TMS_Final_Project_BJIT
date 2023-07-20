// UserContext.js
import { createContext, useContext, useEffect, useReducer } from 'react';
import userReducer from './userReducer';

const UserContext = createContext();

 const UserProvider = ({ children }) => {
  const initialState = {
    userDetails: null,
  };

  const [state, dispatch] = useReducer(userReducer, getStateFromLocalStorage());
  useEffect(() => {
    saveStateToLocalStorage(state);
  }, [state]);
  return (
    <UserContext.Provider value={{ state, dispatch }}>
      {children}
    </UserContext.Provider>
  );
};
const saveStateToLocalStorage = (state) => {
  localStorage.setItem('myAppState', JSON.stringify(state));
};

// Retrieve state from local storage when the application loads
const getStateFromLocalStorage = () => {
  const state = localStorage.getItem('myAppState');
  return state ? JSON.parse(state) : null;
};

export const useUser = () => useContext(UserContext);
export default UserProvider;
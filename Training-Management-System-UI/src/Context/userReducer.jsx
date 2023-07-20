// userReducer.js
const userReducer = (state, action) => {
    switch (action.type) {
      case 'SET_USER_DETAILS':
        return {
          ...state,
          userDetails: action.payload,
        };
      case 'LOGOUT':
        return {
        ...state,
        userDetails: null,
      };
      default:
        return state;
    }
  };
  
  export default userReducer;
  
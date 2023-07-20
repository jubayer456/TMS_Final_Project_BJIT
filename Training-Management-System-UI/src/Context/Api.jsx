// Save state to local storage when it changes
const saveStateToLocalStorage = (state) => {
  localStorage.setItem('myAppState', JSON.stringify(state));
};

// Retrieve state from local storage when the application loads
const getStateFromLocalStorage = () => {
  const state = localStorage.getItem('myAppState');
  return state ? JSON.parse(state) : null;
};
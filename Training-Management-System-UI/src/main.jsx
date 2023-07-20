import './index.css'
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import React from 'react';
import {
  QueryClient,
  QueryClientProvider,
} from 'react-query'
const queryClient = new QueryClient()

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
 <QueryClientProvider client={queryClient}>
    <App></App>
  </QueryClientProvider>
  </React.StrictMode>
);

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.scss';

import { createBrowserRouter, Route, RouterProvider } from 'react-router-dom';

import Home from './pages/Home/Home';
import SignIn from './pages/SignIn/SignIn'
import SignUp from './pages/SignUp/SignUp';
import Error404 from './pages/Error/Error404';
import Restaurante from './pages/Restaurant/Restaurante';
import SearchPage from './pages/SearchPage/SearchPage';
import Dashboard from './pages/Dashboard/Dashboard';

// const router = createBrowserRouter([
//     {
//       path: "/",
//       element: <Home />,
//     },
//     {
//       path: "/login",
//       element: <SignIn />,
//     }
// ])

const router = createBrowserRouter([
  
  {
    path: "/",
    element: <Home />,
    errorElement: <Error404 />,
  },
  {
    path: "/login",
    element: <SignIn />
  },
  {
    path: "/cadastro",
    element: <SignUp />,
  },
  {
    path: "/restaurante/:id",
    element: <Restaurante />,
  },
  {
    path: "/search",
    element: <SearchPage />
  },
  {
    path: "/dashboard",
    element: <Dashboard />
  }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

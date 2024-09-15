import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";
import './index.css'
import { createBrowserRouter,RouterProvider } from 'react-router-dom';
import Register from './components/Register/Register.jsx';
import Login from './components/Login/Login.jsx';
import Home from './components/Home/Home.jsx';
const routes = createBrowserRouter([
  {path:'/',element:<Login></Login>},
  {path:"/register",element:<Register></Register>},
  {path:"/home",element:<Home></Home>}
])
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={routes}></RouterProvider>
  </StrictMode>,
)

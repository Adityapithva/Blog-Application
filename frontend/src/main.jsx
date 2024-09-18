import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";
import './index.css'
import { createBrowserRouter,RouterProvider } from 'react-router-dom';
import Register from './components/Register/Register.jsx';
import Login from './components/Login/Login.jsx';
import Home from './components/Home/Home.jsx';
import CreatePost from './components/CreatePost/CreatePost.jsx';
import Profile from './components/Profile/Profile.jsx';
const routes = createBrowserRouter([
  {path:'/',element:<Login></Login>},
  {path:"/register",element:<Register></Register>},
  {path:"/home",element:<Home></Home>},
  {path:"/create-post",element:<CreatePost></CreatePost>},
  {path:"/my-profile",element:<Profile></Profile>}
  
])
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={routes}></RouterProvider>
  </StrictMode>,
)

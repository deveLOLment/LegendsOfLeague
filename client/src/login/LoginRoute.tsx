
import React from 'react'
import { Route, Routes } from 'react-router-dom'
import GoogleLoginReady from './GoogleLoginReady'
import LogOut from './LogOut'
import LoginPage from './LoginPage'
import AdminPage from '../admin/AdminPage'
import SignUp from './SignUp'
import ExLogin from './ExLogin'
import LoginPage2 from './LogIn'


export const LoginRoute = () => (
  <Routes>
    <Route path="login/google" element={<GoogleLoginReady />}></Route>
    <Route path="logout" element={<LogOut />}></Route>
    <Route path="login" element={<LoginPage />}></Route>
    <Route path="admin" element={<AdminPage />}></Route>
    <Route path="signup" element={<SignUp/>}></Route>
    <Route path="ex" element={<ExLogin/>}></Route>
</Routes>
)

export default LoginRoute;

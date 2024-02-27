import React from 'react'
import { Route, Routes } from 'react-router-dom'
import GoogleLoginReady from './GoogleLoginReady'
import LogOut from './LogOut'
import LogIn from './LogIn'
import AdminPage from '../admin/AdminPage'
import SignUp from './SignUp'

export const LoginRoute = () => (<Routes>
    <Route path="login/google" element={<GoogleLoginReady />}></Route>
    <Route path="logout" element={<LogOut />}></Route>
    <Route path="login" element={<LogIn />}></Route>
    <Route path="admin" element={<AdminPage />}></Route>
    <Route path="signup" element={<SignUp/>}></Route>
</Routes>
)

export default LoginRoute;
import React from 'react'
import { Route, Routes } from 'react-router-dom'
import GoogleLoginReady from './GoogleLoginReady'

const LoginRouts = () => {
  return (
    <Routes>
      <Route path="/login/google" element={<GoogleLoginReady/>}></Route>
    </Routes>
  )
}

export default LoginRouts
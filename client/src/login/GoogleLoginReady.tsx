import React from 'react'

import { useNavigate } from 'react-router-dom';

  
  

const googleLoginReady = () => {

  

    const onNaverLogin = () => {

  

        window.location.href = "http://localhost:8080/oauth2/authorization/google"

    }

  return (

    <div>googleLoginReady

        <button onClick={onNaverLogin}>googleLogin</button>

    </div>

  )

}

  

export default googleLoginReady
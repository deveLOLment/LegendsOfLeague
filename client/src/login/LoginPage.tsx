import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

// 구글 로그인 버튼 이미지 경로
// import googleLoginImage from '/login/googleLogin.png';

const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const url = "http://localhost:8080/login";
      const response = await axios.post(
        url,
        {
          username: username,
          password: password,
        },
        {
          withCredentials: true,
        }
      );

      console.log("응답결과:" + response);
      alert(response.status);
      navigate("/");
    } catch (e) {
      console.log(e);
      alert("아이디나 비밀번호가 올바르지 않습니다.");
      navigate("/login");
    }
  };

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  return (
    <div>
      {/* Header */}
      <header className="header_area">{/* Header content */}</header>

      {/* Banner
      <section className="blog-banner-area" id="category">
        {/* Banner content */}
      {/* </section> */}

      {/* Login Box */}
      <section className="login_box_area section-margin">
        <div className="container">
          <div className="row">
            <div className="col-lg-6">
              <div
                className="image-with-text"
                style={{
                  backgroundImage: `url('/login/soulreaver.jpg')`,
                  backgroundSize: "cover",
                  backgroundPosition: "center",
                  position: "relative",
                  height: "550px",
                  width: "570px",
                }}
              >
                <div
                  className="text-overlay"
                  style={{
                    position: "absolute",
                    top: "50%",
                    left: "50%",
                    transform: "translate(-50%, -50%)",
                    textAlign: "center",
                    color: "#fff",
                  }}
                >
                  <h4>New to our website?</h4>
                  <p>
                    There are advances being made in science and technology
                    everyday, and a good example of this is the
                  </p>
                  <a className="button button-account" href="/signup">
                    Create an Account
                  </a>
                </div>
              </div>
            </div>
            <div className="col-lg-6">
              <div className="login_form_inner">
                <h3>Log in to enter</h3>
                <form className="row login_form" action="#/" id="contactForm">
                  <div className="col-md-12 form-group">
                    <input
                      type="text"
                      className="form-control"
                      id="name"
                      name="name"
                      placeholder="Username"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                    />
                  </div>
                  <div className="col-md-12 form-group">
                    <input
                      type="password"
                      className="form-control"
                      id="name"
                      name="name"
                      placeholder="Password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>
                  <div className="col-md-12 form-group">
                    <div className="creat_account">
                      <input type="checkbox" id="f-option2" name="selector" />
                      <label htmlFor="f-option2">Keep me logged in</label>
                    </div>
                  </div>
                  <div className="col-md-12 form-group">
                    <button
                      type="button"
                      style={{ height: "50px", width: "10px" }}
                      onClick={handleLogin}
                      className="button button-login w-100"
                    >
                      Log In
                    </button>
                    {/* Added margin bottom style */}
                    {/* <br style={{ marginBottom: '10px' }}></br> */}
                    <img
                      src="/login/googleLogin.png"
                      alt="Google Login"
                      onClick={handleGoogleLogin}
                      style={{
                        width: "350px",
                        height: "60px",
                        marginTop: "20px",
                      }}
                    />
                    {/* Changed Forgot Password to Google Login */}
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default LoginPage;

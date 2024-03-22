import React, { FormEvent, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";

function Register() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [nickname, setNickname] = useState(""); // 닉네임 추가

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert("입력한 비밀번호가 일치하지 않습니다.");
      return;
    }

    try {
      const response = await AxiosInstance.post("/register", {
        username,
        email,
        password,
        nickname, // 닉네임도 함께 전송
      });

      console.log(response.data); // 회원가입 성공 시 응답 확인

      // 회원가입 성공 후 다음 페이지로 이동
      navigate("/login");
    } catch (error) {
      console.error("Error registering user:", error);
      alert("회원가입에 실패했습니다.");
    }
  };

  return (
    <div className="login_box_area section-margin">
      <div className="container">
        <div className="row">
          <div className="col-lg-6">
            <div className="login_form_inner register_form_inner">
              <h3>Create an account</h3>
              <form className="row login_form" onSubmit={handleSubmit}>
                <div className="col-md-12 form-group">
                  <input
                    type="text"
                    className="form-control"
                    id="username"
                    name="username"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                  />
                </div>
                <div className="col-md-12 form-group">
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    name="email"
                    placeholder="Email Address"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                  />
                </div>
                <div className="col-md-12 form-group">
                  <input
                    type="text"
                    className="form-control"
                    id="nickname"
                    name="nickname"
                    placeholder="Nickname"
                    value={nickname}
                    onChange={(e) => setNickname(e.target.value)}
                  />
                </div>
                <div className="col-md-12 form-group">
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    name="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="col-md-12 form-group">
                  <input
                    type="password"
                    className="form-control"
                    id="confirmPassword"
                    name="confirmPassword"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="col-md-12 form-group">
                  <button
                    type="submit"
                    className="button button-register w-100"
                  >
                    Register
                  </button>
                </div>
              </form>
            </div>
          </div>
          <div className="col-lg-6">
            <div className="login_box_img">
              <div className="hover">
                <h4>Already have an account?</h4>
                <p>
                  There are advances being made in science and technology
                  everyday, and a good example of this is the
                </p>
                <a className="button button-account" href="/login">
                  Login Now
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;

import React, { FormEvent, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function SignUp() {
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [nickname, setNickname] = useState("");

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert('입력한 비밀번호가 일치하지 않습니다.');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/register', {
                username,
                email,
                password,
                nickname
            });

            console.log(response.data); // 회원가입 성공 시 응답 확인

            // 회원가입 성공 후 다음 페이지로 이동
            navigate("/login");
        } catch (error) {
            console.error('Error registering user:', error);
            alert('회원가입에 실패했습니다.');
        }
    };

    return (
        <div className="main-w3layouts wrapper">
            <h1>Creative SignUp Form</h1>
            <div className="main-agileinfo">
                <div className="agileits-top">
                    <form onSubmit={handleSubmit}>
                        <input className="text" type="text" name="username" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} required />
                        <input className="text email" type="email" name="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                        <input className="text" type="text" name="nickname" placeholder="Nickname" value={nickname} onChange={(e) => setNickname(e.target.value)} required />
                        <input className="text" type="password" name="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                        <input className="text w3lpass" type="password" name="confirmPassword" placeholder="Confirm Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required />
                        <div className="wthree-text">
                            <label className="anim">
                                <input type="checkbox" className="checkbox" required />
                                <span>I Agree To The Terms & Conditions</span>
                            </label>
                            <div className="clear"> </div>
                        </div>
                        <input type="submit" value="SIGNUP" />
                    </form>
                </div>
            </div>
            <div className="colorlibcopy-agile">
                <p>© 2023 Signup Form. All rights reserved | Design by <a href="https://colorlib.com/" target="_blank" rel="noopener noreferrer">Colorlib</a></p>
            </div>
            <ul className="colorlib-bubbles">
                {Array.from({ length: 10 }, (_, index) => (
                    <li key={index}></li>
                ))}
            </ul>
        </div>
    );
}

export default SignUp;

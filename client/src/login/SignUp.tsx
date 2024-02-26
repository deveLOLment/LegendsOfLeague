import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function SignUp() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");

const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        // 정규식 검증
        const usernameRegex = /^[a-zA-Z0-9_]{5,20}$/; // 5~20자의 영문, 숫자와 _만 허용
        const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,}$/; // 이메일 형식
        const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/; // 최소 8자, 최소 하나의 대문자와 소문자, 최소 하나의 숫자

        if (!usernameRegex.test(username)) {
            alert('아이디는 5~20자의 영문, 숫자와 _만 사용할 수 있습니다.');
            return;
        }

        if (!emailRegex.test(email)) {
            alert('이메일 형식이 올바르지 않습니다.');
            return;
        }

        if (!passwordRegex.test(password)) {
            alert('비밀번호는 최소 8자, 최소 하나의 대문자와 소문자, 최소 하나의 숫자를 포함해야 합니다.');
            return;
        }

        const response = await fetch('http://localhost:8080/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password, email, name }),
        });

        if (!response.ok) {
            console.error('회원가입 실패');
            return;
        }

        console.log('회원가입 성공');

        navigate("/");
    };

    return (
        <div>
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    아이디:
                    <input type="text" value={username} 
                           onChange={(e: React.ChangeEvent<HTMLInputElement>) => setUsername(e.target.value)} required />
                </label>
                <label>
                    비밀번호:
                    <input type="password" value={password} 
                           onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)} required />
                </label>
                <label>
                    이메일:
                    <input type="email" value={email} 
                           onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)} required />
                </label>
                <label>
                    이름:
                    <input type="text" value={name} 
                           onChange={(e: React.ChangeEvent<HTMLInputElement>) => setName(e.target.value)} required />
                </label>
                <input type="submit" value="회원가입" />
            </form>
        </div>
    );
}

export default SignUp;

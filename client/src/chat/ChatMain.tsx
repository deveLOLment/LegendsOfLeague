import axios from "axios";
import React, { useEffect, useState, useRef } from 'react';
import SockJS from 'sockjs-client';
import * as WebStomp from 'webstomp-client';
import Chat from "./Chat";
import { Message } from './ChatModel';
import axiosInstance from "../common/AxiosInstance";

const ChatMain = () => {
  const [loading, setLoading] = useState(true);
  const [messages, setMessages] = useState<Message[]>([]);
  const [input, setInput] = useState('');
  const [user, setUser] = useState('');

  const stompClientRef = useRef<WebStomp.Client | null>(null);
  const chatContainerRef = useRef<HTMLDivElement | null>(null); //채팅 컨테이너 참조

  useEffect(() => {
    loadUser();
    loadPreviousChat();
    connectSocket();
  }, []);

  useEffect(() => {
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }
  }, [messages]); // messages가 변경될 때마다 스크롤 위치를 가장 아래로 이동

  const connectSocket = async () => {
    const socket = new SockJS('http://localhost:8080/ws-stomp');
    const stompClient = WebStomp.over(socket);

    stompClient.connect({}, () => {
      stompClient.subscribe('/sub/chat', (messageOutput) => {
        const message = JSON.parse(messageOutput.body);
        setMessages(prev => [...prev, message]);
      });
    });

    stompClientRef.current = stompClient;
  };

  const loadPreviousChat = async () => {
    const url = "http://localhost:8080/chat/previousChat";
    const response = await axiosInstance.get(url);
    console.log('previousChat', response);
    setMessages(response.data);
    setLoading(false);
  };

  const loadUser = async () => {
    const url = "http://localhost:8080/chat/getUser";
    const response = await axiosInstance.get(url);
    console.log('getUser', response);
    setUser(response.data.username);
  }
  
  const submitHandler = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const message = { 
      content: input,
      type: 'TALK' ,
      sender: user
    };
    if (stompClientRef.current) {
      stompClientRef.current.send('/pub/chat/sendMessage', JSON.stringify(message));
    }
    setInput('');
  };

  return (
    <div className="chat-container" ref={chatContainerRef} style={{ display: 'flex', flexDirection: 'column' }}>
      {loading ? (
        <p>Loading...</p>
      ) : (
        messages.map((message, index) => (
            <Chat 
              key={index} 
              content={message.content} 
              type={message.type}
              time={message.time} 
              sender={message.sender}
              isMe={user === message.sender}
            />
        ))
      )}
      <form className="form-container" onSubmit={submitHandler}>
        <input
          className="chat-input"
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
        />
        <button type="submit">전송</button>
      </form>
    </div>
  );
};

export default ChatMain;

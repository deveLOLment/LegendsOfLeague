import React from "react";
import { Message } from "./ChatModel";
import "./ChatMain.css";

type ChatProps = Message & { isMe: boolean };

const getDisplayName = (sender: string) => {
  const prefix = "google ";
  if (sender.startsWith(prefix)) {
    return prefix + sender.slice(prefix.length, prefix.length + 4);
  }
  return sender;
};

const Chat: React.FC<ChatProps> = ({ content, type, time, sender, isMe }) => {
  const date = new Date(time);
  const formattedTime = `${
    date.getMonth() + 1
  }/${date.getDate()} ${date.getHours()}:${
    date.getMinutes() < 10 ? "0" : ""
  }${date.getMinutes()}`;
  const messageClass = isMe ? "my" : "others";
  const displaySender = getDisplayName(sender);

  return (
    <div className={`${messageClass}-chat-box`}>
      <span className={`chat-sender ${messageClass}-sender`}>
        {displaySender}
      </span>
      <div className={`chat-message ${messageClass}-message`}>
        <div>{content}</div>
      </div>
      <span className={`chat-time ${messageClass}-time`}>{formattedTime}</span>
    </div>
  );
};

export default Chat;

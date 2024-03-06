import React from "react";
import { Route, Routes } from "react-router-dom";
import ChatMain from "./ChatMain";

const ChatRouter = () => {
  return (
    <Routes>
      <Route path="/chat" element={<ChatMain></ChatMain>}></Route>
    </Routes>
  );
};

export default ChatRouter;

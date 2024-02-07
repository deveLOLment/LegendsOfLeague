import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Ex from "./Example/Ex";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Ex />}></Route>
    </Routes>
  );
}

export default App;

import { Route, Routes } from "react-router-dom";
import Games from "./Games";

const GameRoutes = () => {
  return (
    <Routes>
      <Route path="lol" element={<Games />} />
    </Routes>
  );
};

export default GameRoutes;

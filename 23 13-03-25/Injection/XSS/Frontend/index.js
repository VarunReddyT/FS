import React from "react";
import ReactDOM from "react-dom/client";
import XSSDemo from "./XSSDemo";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <XSSDemo />
  </React.StrictMode>
);

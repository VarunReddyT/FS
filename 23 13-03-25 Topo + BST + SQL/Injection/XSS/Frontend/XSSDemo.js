/*
import React, { useState, useEffect } from "react";
import axios from "axios";
import DOMPurify from "dompurify";

const XSSDemo = () => {
  const [username, setUsername] = useState("");
  const [comment, setComment] = useState("");
  const [comments, setComments] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchComments();
  }, []);

  const fetchComments = async () => {
    try {
      const response = await axios.get("http://localhost:5000/comments");
      setComments(response.data.map(c => ({
        ...c,
        comment: DOMPurify.sanitize(c.comment)
      })));
    } catch (error) {
      console.error("Error fetching comments", error);
    }
  };

  const submitComment = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const sanitizedComment = DOMPurify.sanitize(comment);
      const response = await axios.post("http://localhost:5000/add-comment", {
        username: DOMPurify.sanitize(username),
        comment: sanitizedComment,
      });
      setMessage(response.data.message);
      setComment("");
    } catch (error) {
      setMessage(error.response?.data?.error || "An error occurred");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Secure XSS Demo (Fixed)</h2>
      <form onSubmit={submitComment}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        /><br /><br />
        <textarea
          placeholder="Enter your comment"
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          required
        ></textarea><br /><br />
        <button type="submit">Submit Comment</button>
      </form>
      <p>{message}</p>
      <h3>Comments</h3>
      <div>
        {comments.map((c, index) => (
          <div key={index}>
            <p><strong>{DOMPurify.sanitize(c.username)}</strong></p>
            {c.comment}
          </div>
        ))}
      </div>
    </div>
  );
};

export default XSSDemo;
*/


import React, { useState, useEffect } from "react";
import axios from "axios";

const Navbar = ({ setActivePage }) => {
  return (
    <nav style={{ textAlign: "center", padding: "10px", background: "#333", color: "#fff" }}>
      <button onClick={() => setActivePage("add")} style={{ marginRight: "10px" }}>Add Comment</button>
      <button onClick={() => setActivePage("display")}>View Comments</button>
    </nav>
  );
};

const AddComment = ({ fetchComments }) => {
  const [username, setUsername] = useState("");
  const [comment, setComment] = useState("");
  const [message, setMessage] = useState("");

  const submitComment = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const response = await axios.post("http://localhost:5000/add-comment", {
        username,
        comment,
      });
      setMessage(response.data.message);
      setUsername("");
      setComment("");
      fetchComments();
    } catch (error) {
      setMessage(error.response?.data?.error || "An error occurred");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Add Comment</h2>
      <form onSubmit={submitComment}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        /><br /><br />
        <textarea
          placeholder="Enter your comment"
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          required
        ></textarea><br /><br />
        <button type="submit">Submit Comment</button>
      </form>
      <p>{message}</p>
    </div>
  );
};

const DisplayComments = ({ comments }) => {
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Comments</h2>
      <div>
        {comments.map((c, index) => (
          <div key={index}>
            <p><strong>{c.username}</strong></p>
            { c.comment }
            
          </div>
        ))}
      </div>
    </div>
  );
};

const XSSDemo = () => {
  const [comments, setComments] = useState([]);
  const [activePage, setActivePage] = useState("add");

  useEffect(() => {
    fetchComments();
  }, []);

  const fetchComments = async () => {
    try {
      const response = await axios.get("http://localhost:5000/comments");
      setComments(response.data);
    } catch (error) {
      console.error("Error fetching comments", error);
    }
  };

  return (
    <div>
      <Navbar setActivePage={setActivePage} />
      {activePage === "add" ? <AddComment fetchComments={fetchComments} /> : <DisplayComments comments={comments} />}
    </div>
  );
};

export default XSSDemo;

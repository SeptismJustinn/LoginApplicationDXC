import React, { useRef } from "react";
import styles from "../css/Login.module.css";
import { fetchData } from "../helpers/common";
import jwtDecode from "jwt-decode";

function Login(props) {
  const usernameRef = useRef("");
  const passwordRef = useRef("");

  async function handleLogin(event) {
    event.preventDefault();
    if (usernameRef.current.value == "") {
      alert("Please enter username!");
      return usernameRef.current.focus();
    } else if (passwordRef.current.value == "") {
      alert("Please enter password!");
      return passwordRef.current.focus();
    }
    try {
      const { ok, data } = await fetchData("/public/login", undefined, "POST", {
        username: usernameRef.current.value,
        password: passwordRef.current.value,
      });

      if (ok) {
        const access = data.access_token;
        localStorage.setItem("access", access);
        const decoded = jwtDecode(access);
        console.log(decoded);
      } else {
        throw new Error(data);
      }
    } catch (error) {
      console.log(error.message);
      alert("Error logging in, please try again!");
    }
  }

  return (
    <div>
      <h2>Login to view profile</h2>
      <form onSubmit={handleLogin}>
        <div className={styles.input_grid}>
          <label htmlFor="username">Username: </label>
          <input
            type="text"
            id="username"
            ref={usernameRef}
            placeholder="Enter Username"
            required
          />
          <label htmlFor="password">Password: </label>
          <input
            type="password"
            id="password"
            ref={passwordRef}
            placeholder="Enter Password"
            required
          />
          <input type="submit" value="Log In" />
        </div>
      </form>
    </div>
  );
}

export default Login;

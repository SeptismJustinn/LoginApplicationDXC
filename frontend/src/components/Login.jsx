import React, { useRef } from "react";
import styles from "../css/Login.module.css";
import { fetchData } from "../helpers/common";
import jwtDecode from "jwt-decode";
import User from "../helpers/objectClasses";

function Login(props) {
  const usernameRef = useRef();
  const passwordRef = useRef();

  async function handleLogin(event) {
    event.preventDefault();
    if (
      localStorage.getItem("access") &&
      usernameRef.current.value == "" &&
      passwordRef.current.value == ""
    ) {
      // Login with existing access token
      if (await tokenLogin()) {
        return;
      }
    }
    if (usernameRef.current.value == "") {
      alert("Please enter username!");
      return usernameRef.current.focus();
    } else if (passwordRef.current.value == "") {
      alert("Please enter password!");
      return passwordRef.current.focus();
    } else if (passwordRef.current.value.length < 8) {
      alert("Password is at least 8 characters long");
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
        props.setUser(new User(decoded.name, decoded.sub, decoded.role));
        alert("Logged in as user: " + decoded.sub);
      } else {
        throw new Error(data);
      }
    } catch (error) {
      alert(error.message + ", please try again!");
    }
  }

  async function tokenLogin() {
    const access = localStorage.getItem("access");
    try {
      const { ok, data } = await fetchData("/public/login", access, "GET");
      if (ok) {
        const decoded = jwtDecode(access);
        props.setUser(new User(decoded.name, decoded.sub, decoded.role));
        alert("Resuming session as user: " + decoded.sub);
        return true;
      } else {
        throw new Error(data);
      }
    } catch (error) {
      console.log(error.message);
      return false;
    }
  }

  return (
    <div>
      <h2>Login to view profile</h2>
      <h3>Leave fields blank to resume old session, if still valid.</h3>
      <form onSubmit={handleLogin}>
        <div className={styles.input_grid}>
          <label htmlFor="username">Username: </label>
          <input
            type="text"
            id="username"
            ref={usernameRef}
            placeholder="Enter Username"
          />
          <label htmlFor="password">Password: </label>
          <input
            type="password"
            id="password"
            ref={passwordRef}
            placeholder="Enter Password"
          />
          <input type="submit" value="Log In" />
        </div>
      </form>
      <input
        type="button"
        value="Register"
        className={styles.register_button}
        onClick={(e) => {
          e.preventDefault();
          props.setShowRegister(true);
        }}
      />
    </div>
  );
}

export default Login;

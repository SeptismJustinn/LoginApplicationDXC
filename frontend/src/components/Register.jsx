import React, { useRef } from "react";
import styles from "../css/Login.module.css";
import { fetchData } from "../helpers/common";

function Register(props) {
  const nameRef = useRef();
  const usernameRef = useRef();
  const passwordRef = useRef();
  const confirmPasswordRef = useRef();

  async function handleRegister(event) {
    event.preventDefault();
    //Validation done in input since registration form must NEVER be empty on submit
    if (passwordRef.current.value !== confirmPasswordRef.current.value) {
      alert("Passwords do not match!");
      return passwordRef.current.focus();
    }

    try {
      const { ok, data } = await fetchData(
        "/public/register",
        undefined,
        "POST",
        {
          name: nameRef.current.value,
          username: usernameRef.current.value,
          password: passwordRef.current.value,
        }
      );

      if (ok) {
        nameRef.current.value = "";
        usernameRef.current.value = "";
        passwordRef.current.value = "";
        confirmPasswordRef.current.value = "";
        return alert(
          "Registered! Please login with " +
            data.username +
            " and respective password."
        );
      } else {
        throw new Error(data);
      }
    } catch (error) {
      return alert(error.message + ", please try again");
    }
  }
  return (
    <div>
      <h2>Register new account</h2>
      <form onSubmit={handleRegister}>
        <div className={styles.input_grid}>
          <label htmlFor="name">Name: </label>
          <input
            type="text"
            id="name"
            ref={nameRef}
            placeholder="Enter name"
            minLength={1}
            required
          />
          <label htmlFor="username">Username: </label>
          <input
            type="text"
            id="username"
            ref={usernameRef}
            placeholder="Enter Username"
            minLength={1}
            required
          />
          <label htmlFor="password">Password: </label>
          <input
            type="password"
            id="password"
            ref={passwordRef}
            placeholder="Enter Password"
            minLength={8}
            required
          />
          <label htmlFor="password2">Confirm Password: </label>
          <input
            type="password"
            id="password2"
            ref={confirmPasswordRef}
            placeholder="Enter Password again"
            required
          />
          <input type="submit" value="Register user" />
        </div>
      </form>
      <input
        type="button"
        value="Return to login"
        className={styles.register_button}
        onClick={(e) => {
          e.preventDefault();
          props.setShowRegister(false);
        }}
      />
    </div>
  );
}

export default Register;

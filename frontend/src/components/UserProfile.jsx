import React from "react";
import styles from "../css/UserProfile.module.css";
import { fetchData } from "../helpers/common";
import User from "../helpers/objectClasses";

function UserProfile(props) {
  async function handleLogout(event) {
    event.preventDefault();
    try {
      const { ok, data } = await fetchData(
        "/protected/logout",
        localStorage.getItem("access")
      );
      if (ok) {
        props.setUser(new User());
        alert("Logged out successfully!");
      } else {
        throw new Error(data);
      }
    } catch (error) {
      alert("Error logging out, try again");
    }
  }
  return (
    <div>
      <input type="button" value="Logout" onClick={handleLogout} />
      <div className={styles.profile_grid}>
        <div className={styles.label}>Name: </div>
        <div>{props.user.name}</div>
        <div className={styles.label}>Username: </div>
        <div>{props.user.username}</div>
        <div className={styles.label}>Role: </div>
        <div>{props.user.role}</div>
      </div>
    </div>
  );
}

export default UserProfile;

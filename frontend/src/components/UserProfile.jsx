import React from "react";
import styles from "../css/UserProfile.module.css";

function UserProfile(props) {
  return (
    <div className={styles.profile_grid}>
      <div className={styles.label}>Name: </div>
      <div>{props.user.name}</div>
      <div className={styles.label}>Username: </div>
      <div>{props.user.username}</div>
      <div className={styles.label}>Role: </div>
      <div>{props.user.role}</div>
    </div>
  );
}

export default UserProfile;

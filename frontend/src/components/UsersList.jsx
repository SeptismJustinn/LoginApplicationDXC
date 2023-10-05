import React, { useEffect, useState } from "react";
import { fetchData } from "../helpers/common";
import styles from "../css/UsersList.module.css";

function UsersList(props) {
  const [usersList, setUsersList] = useState([]);

  async function getAllUsers() {
    try {
      const { ok, data } = await fetchData(
        "/protected/admin",
        localStorage.getItem("access"),
        "GET"
      );
      if (ok) {
        const vettedData = data
          .map((item) => {
            return {
              name: item.name,
              username: item.username,
              role: item.role,
            };
          })
          .filter((item) => {
            return item.username !== props.user.username;
          });
        setUsersList(vettedData);
      } else {
        throw new Error("Unable to fetch users");
      }
    } catch (error) {
      console.log(error.message);
      alert("Error getting users list, please try again!");
    }
  }

  useEffect(() => {
    if (usersList.length < 1) {
      getAllUsers();
    }
  }, [usersList]);
  return (
    <div className={styles.table}>
      <div className={styles.table_header}>
        <h2>Name</h2>
        <h2>Username</h2>
        <h2>Role</h2>
      </div>
      <div className={`${styles.table_row} ${styles.current_user}`}>
        <div>{props.user.name}</div>
        <div>{props.user.username}</div>
        <div>{props.user.role}</div>
      </div>
      {usersList.map((item, idx) => {
        return (
          <div
            className={`${styles.table_row} ${
              idx % 2 == 0 ? "" : styles.odd_row
            }`}
            key={idx}
          >
            <div>{item.name}</div>
            <div>{item.username}</div>
            <div>{item.role}</div>
          </div>
        );
      })}
    </div>
  );
}

export default UsersList;

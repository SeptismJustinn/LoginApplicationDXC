import React, { useState } from "react";
import Login from "./components/Login";
import User from "./helpers/objectClasses";
import UserProfile from "./components/UserProfile";
import UsersListToggle from "./components/UsersListToggle";
import styles from "./css/App.module.css";
import UsersList from "./components/UsersList";
import Register from "./components/Register";
import Encoder from "./components/Encoder";

function App() {
  const [user, setUser] = useState(new User());
  const [showUsers, setShowUsers] = useState(false);
  const [showRegister, setShowRegister] = useState(false);
  if (user.role == "ADMIN") {
    if (showUsers) {
      return (
        <div className={styles.main_page}>
          <UsersListToggle showUsers={showUsers} setShowUsers={setShowUsers} />
          <UsersList user={user} />
        </div>
      );
    } else {
      return (
        <div className={styles.main_page}>
          <UsersListToggle showUsers={showUsers} setShowUsers={setShowUsers} />
          <UserProfile user={user} />
          <Encoder />
        </div>
      );
    }
  } else if (user.role == "USER") {
    return (
      <div className={styles.main_page}>
        <UserProfile user={user} />
        <Encoder />
      </div>
    );
  } else {
    if (showRegister) {
      return (
        <div className={styles.main_page}>
          <Register setShowRegister={setShowRegister} />
        </div>
      );
    } else {
      return (
        <div className={styles.main_page}>
          <Login setUser={setUser} setShowRegister={setShowRegister} />
        </div>
      );
    }
  }
}

export default App;

import React, { useState } from "react";
import Login from "./components/Login";
import User from "./helpers/objectClasses";
import UserProfile from "./components/UserProfile";
import UsersListToggle from "./components/UsersListToggle";
import styles from "./css/App.module.css";

function App() {
  const [user, setUser] = useState(new User());
  const [showUsers, setShowUsers] = useState(false);
  if (user.role == "ADMIN") {
    if (showUsers) {
      return (
        <div className={styles.main_page}>
          <UsersListToggle setShowUsers={setShowUsers} />
          <UsersList />
        </div>
      );
    } else {
      return (
        <div className={styles.main_page}>
          <UsersListToggle setShowUsers={setShowUsers} />
          <UserProfile user={user} />
        </div>
      );
    }
  } else if (user.role == "USER") {
    return (
      <div className={styles.main_page}>
        <UserProfile user={user} />
      </div>
    );
  } else {
    return (
      <div className={styles.main_page}>
        <Login setUser={setUser} />
      </div>
    );
  }
}

export default App;

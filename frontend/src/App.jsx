import React from "react";
import Login from "./components/Login";
import User from "./helpers/objectClasses";
import UserProfile from "./components/UserProfile";
import UsersListToggle from "./components/UsersListToggle";

function App() {
  const [user, setUser] = useState(new User());
  const [showUsers, setShowUsers] = useState(false);
  if (user.role == "ADMIN") {
    if (showUsers) {
      return (
        <div>
          <UsersListToggle setShowUsers={setShowUsers} />
          <UsersList />
        </div>
      );
    } else {
      return (
        <div>
          <UsersListToggle setShowUsers={setShowUsers} />
          <UserProfile user={user} />
        </div>
      );
    }
  } else if (user.role == "USER") {
    return (
      <div>
        <UserProfile user={user} />
      </div>
    );
  } else {
    return (
      <div>
        <Login setUser={setUser} />
      </div>
    );
  }
}

export default App;

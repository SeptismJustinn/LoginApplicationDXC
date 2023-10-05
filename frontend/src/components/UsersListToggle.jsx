import React from "react";

function UsersListToggle(props) {
  function handleClick() {
    props.setShowUsers(!props.showUsers);
  }

  return (
    <input
      type="button"
      value={props.showUsers ? "Hide other users" : "Show all users"}
      onClick={handleClick}
    />
  );
}

export default UsersListToggle;

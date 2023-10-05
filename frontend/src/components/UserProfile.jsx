import React from "react";

function UserProfile(props) {
  return (
    <div>
      <div>
        <div>Name: </div>
        <div>{props.user.name}</div>
      </div>
      <div>
        <div>Username: </div>
        <div>{props.user.username}</div>
      </div>
      <div>
        <div>Role: </div>
        <div>{props.user.role}</div>
      </div>
    </div>
  );
}

export default UserProfile;

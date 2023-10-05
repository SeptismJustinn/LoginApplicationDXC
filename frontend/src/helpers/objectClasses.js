export default class User {
  name;
  username;
  role;

  constructor(name, username, role) {
    this.name = name || "public";
    this.username = username || "None";
    this.role = role || "PUBLIC";
  }
}

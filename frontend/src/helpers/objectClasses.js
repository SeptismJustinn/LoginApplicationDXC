export default class User {
  name;
  username;
  role;

  constructor() {
    this.name = "Public";
    this.username = "None";
    this.role = "PUBLIC";
  }

  constructor(name, username, role) {
    this.name = name;
    this.username = username;
    this.role = role;
  }
}

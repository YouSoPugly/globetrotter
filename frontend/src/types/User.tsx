export class User {
  name: string;
  email: string;
  picture: string;

  constructor(name: string, email: string, picture: string) {
    this.name = name;
    this.email = email;
    this.picture = picture;
  }
}
// src/components/TopBar.tsx
import { useUser } from "../contexts/UserContext";
import { User } from "../types/User";
import { useEffect } from "react";

export default function TopBar() {
  const { user, setUser } = useUser();

  const getUser = () => {
    fetch(window.location.origin + "/api/users/whoami", { credentials: "include" })
      .then((res) => res.json())
      .then((data: { name: string; email: string; profilePicture: string }) => {
        if (data?.email) {
          console.log("WHOAMI response:", data);
          setUser(new User(data.name, data.email, data.profilePicture));
        }
      })
      .catch(() => {
      });
  }

  useEffect(getUser, [setUser]);

  const handleLogin = () => {
    const baseUrl = window.location.origin;
    window.location.href = `${baseUrl}/oauth2/authorization/google`;
  };

  return (
    <div style={{
      position: 'fixed',
      top: 0,
      right: 0,
      left: 0,
      height: 70,
      zIndex: 5,
      display: 'flex',
      justifyContent: 'flex-end',
      alignItems: 'center',
      padding: '0 1rem',
    }}>
      <img
        key={user?.picture}
        src={`${window.location.origin}${user?.picture}` || "/default-avatar.jpg"} // your default icon
        alt="User"
        onClick={handleLogin}
        style={{
          width: 50,
          height: 50,
          borderRadius: "50%",
          cursor: "pointer",
        }}
      />
    </div>
  );
}

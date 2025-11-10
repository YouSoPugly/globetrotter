// src/components/TopBar.tsx
import { useUser } from "../contexts/UserContext";
import { User } from "../types/User";
import { useEffect } from "react";

export default function TopBar() {
  const { user, setUser } = useUser();

  useEffect(() => {
    fetch("/api/whoami", { credentials: "include" })
      .then((res) => res.json())
      .then((data: { name: string; email: string; profilePicture: string }) => {
        if (data?.email) {
          setUser(new User(data.name, data.email, data.profilePicture));
        }
      })
      .catch(() => {
        // not logged in
      });
  }, [setUser]);

  const handleLogin = () => {
    const baseUrl = window.location.origin;
    window.location.href = `${baseUrl}/login/google`;
  };

  return (
    <div style={{
      position: 'fixed',
      top: 0,
      right: 0,
      left: 0,
      height: 60,
      zIndex: 1000,
      display: 'flex',
      justifyContent: 'flex-end',
      alignItems: 'center',
      padding: '0 1rem',
    }}>
      <img
        src={user?.picture || "/default-avatar.png"} // your default icon
        alt="User"
        onClick={handleLogin}
        style={{
          width: 40,
          height: 40,
          borderRadius: "50%",
          cursor: "pointer",
          border: "1px solid #ccc",
        }}
      />
    </div>
  );
}

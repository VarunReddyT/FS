import { useAuth } from "./AuthContext";

const Dashboard = () => {
  const { user } = useAuth();
  return <h1>Welcome, {user?.username}!</h1>;
};

export default Dashboard;

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Navbar from './components/Navbar';
import NewProfilePage from './pages/NewProfilePage';
import AllProfilesPage from './pages/AllProfilesPage';
import ProfileDetailPage from './pages/ProfileDetailPage';
import theme from './theme';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<NewProfilePage />} />
          <Route path="/profile" element={<NewProfilePage />} />
          <Route path="/profiles" element={<AllProfilesPage />} />
          <Route path="/profile/:id" element={<ProfileDetailPage />} />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App;
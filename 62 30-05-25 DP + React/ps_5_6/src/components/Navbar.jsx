import React from 'react';
import { AppBar, Toolbar, Button, Typography } from '@mui/material';

function Navbar({ isAuthenticated, onRegisterClick, onLoginClick, onLogoutClick }) {
  return (
    <AppBar position="static" color="primary">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          Product App
        </Typography>
        {!isAuthenticated ? (
          <>
            <Button color="inherit" onClick={onLoginClick}>Login</Button>
            <Button color="inherit" onClick={onRegisterClick}>Register</Button>
          </>
        ) : (
          <Button color="inherit" onClick={onLogoutClick}>Logout</Button>
        )}
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;
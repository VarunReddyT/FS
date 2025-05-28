import React from 'react';
import { List, ListItem, ListItemText, Divider, Typography, Paper } from '@mui/material';
import { Link } from 'react-router-dom';

const ProfileList = ({ profiles }) => {
  return (
    <Paper elevation={3} sx={{ p: 3, mt: 3 }}>
      <Typography variant="h5" gutterBottom>
        All Profiles
      </Typography>
      <List>
        {profiles.map((profile, index) => (
          <React.Fragment key={profile._id}>
            <ListItem 
              component={Link} 
              to={`/profile/${profile._id}`}
              sx={{ textDecoration: 'none', color: 'inherit' }}
            >
              <ListItemText
                primary={profile.name}
                secondary={profile.email}
              />
            </ListItem>
            {index < profiles.length - 1 && <Divider />}
          </React.Fragment>
        ))}
      </List>
    </Paper>
  );
};

export default ProfileList;
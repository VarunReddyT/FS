import React from 'react';
import { 
  Typography, 
  Paper, 
  Box, 
  Divider, 
  Chip,
  List,
  ListItem,
  ListItemText
} from '@mui/material';

const ProfileDetail = ({ profile }) => {
  return (
    <Paper elevation={3} sx={{ p: 3, mt: 3 }}>
      <Typography variant="h4" gutterBottom>
        Profile of {profile.name}
      </Typography>
      
      <Box sx={{ mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Personal Info
        </Typography>
        <Typography>Email: {profile.email}</Typography>
        <Typography>Phone: {profile.phone}</Typography>
      </Box>
      <Divider sx={{ my: 2 }} />
      
      <Box sx={{ mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Education
        </Typography>
        <Typography>Degree: {profile.education.degree}</Typography>
        <Typography>Institution: {profile.education.institution}</Typography>
        <Typography>Year: {profile.education.year}</Typography>
      </Box>
      <Divider sx={{ my: 2 }} />
      
      <Box sx={{ mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Interests
        </Typography>
        <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 1 }}>
          {profile.interests.map((interest, index) => (
            <Chip key={index} label={interest} />
          ))}
        </Box>
      </Box>
      <Divider sx={{ my: 2 }} />
      
      <Box sx={{ mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Achievements
        </Typography>
        <List>
          {profile.achievements.map((achievement, index) => (
            <ListItem key={index}>
              <ListItemText primary={achievement} />
            </ListItem>
          ))}
        </List>
      </Box>
    </Paper>
  );
};

export default ProfileDetail;
import React, { useState, useEffect } from 'react';
import { getAllProfiles } from '../services/profileService';
import ProfileList from '../components/ProfileList';
import { CircularProgress, Box } from '@mui/material';

const AllProfilesPage = () => {
  const [profiles, setProfiles] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProfiles = async () => {
      try {
        const data = await getAllProfiles();
        setProfiles(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching profiles:', error);
        setLoading(false);
      }
    };

    fetchProfiles();
  }, []);

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="200px">
        <CircularProgress />
      </Box>
    );
  }

  return (
    <div>
      <ProfileList profiles={profiles} />
    </div>
  );
};

export default AllProfilesPage;
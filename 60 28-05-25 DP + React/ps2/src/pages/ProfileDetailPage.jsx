import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getProfileById } from '../services/profileService';
import ProfileDetail from '../components/ProfileDetail';
import { CircularProgress, Box } from '@mui/material';

const ProfileDetailPage = () => {
  const { id } = useParams();
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await getProfileById(id);
        setProfile(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching profile:', error);
        setLoading(false);
      }
    };

    fetchProfile();
  }, [id]);

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="200px">
        <CircularProgress />
      </Box>
    );
  }

  if (!profile) {
    return <div>Profile not found</div>;
  }

  return (
    <div>
      <ProfileDetail profile={profile} />
    </div>
  );
};

export default ProfileDetailPage;
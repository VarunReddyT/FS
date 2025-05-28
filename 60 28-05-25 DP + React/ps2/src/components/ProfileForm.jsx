import React, { useState } from 'react';
import { 
  Stepper, 
  Step, 
  StepLabel, 
  Button, 
  Typography, 
  TextField, 
  Box, 
  Paper,
  Divider,
  Alert
} from '@mui/material';
import { saveProfile } from '../services/profileService';
import { useNavigate } from 'react-router-dom';

const steps = ['Personal Info', 'Education Info', 'Interests', 'Achievements'];

const ProfileForm = () => {
  const [activeStep, setActiveStep] = useState(0);
  const [profile, setProfile] = useState({
    name: '',
    email: '',
    phone: '',
    education: {
      degree: '',
      institution: '',
      year: ''
    },
    interests: '',
    achievements: ''
  });
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  // Validation functions
  const validateName = (name) => {
    if (!name.trim()) return 'Name is required';
    if (!/^[a-zA-Z ]+$/.test(name)) return 'Name should contain only alphabets';
    return '';
  };

  const validateEmail = (email) => {
    if (!email.trim()) return 'Email is required';
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) return 'Invalid email format';
    return '';
  };

  const validatePhone = (phone) => {
    if (!phone.trim()) return 'Phone number is required';
    if (!/^[0-9]{10}$/.test(phone)) return 'Phone number must be 10 digits';
    return '';
  };

  const validateEducation = (education) => {
    const errors = {};
    if (!education.degree.trim()) errors.degree = 'Degree is required';
    if (!education.institution.trim()) errors.institution = 'Institution is required';
    if (!education.year.trim()) errors.year = 'Year is required';
    if (education.year.trim() && !/^[0-9]{4}$/.test(education.year)) {
      errors.year = 'Year must be 4 digits';
    }
    return errors;
  };

  const validateStep = (step) => {
    const newErrors = {};
    
    if (step === 0) {
      newErrors.name = validateName(profile.name);
      newErrors.email = validateEmail(profile.email);
      newErrors.phone = validatePhone(profile.phone);
    } else if (step === 1) {
      const educationErrors = validateEducation(profile.education);
      if (Object.keys(educationErrors).length > 0) {
        newErrors.education = educationErrors;
      }
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleNext = () => {
    if (validateStep(activeStep)) {
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    }
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.includes('education.')) {
      const field = name.split('.')[1];
      setProfile({
        ...profile,
        education: {
          ...profile.education,
          [field]: value
        }
      });
    } else {
      setProfile({
        ...profile,
        [name]: value
      });
    }
  };

  const handleSubmit = async () => {
    if (validateStep(activeStep)) {
      try {
        // Convert comma-separated strings to arrays before saving
        const profileToSave = {
          ...profile,
          interests: profile.interests.split(',').map(item => item.trim()).filter(item => item),
          achievements: profile.achievements.split(',').map(item => item.trim()).filter(item => item)
        };
        await saveProfile(profileToSave);
        navigate('/all-profiles');
      } catch (error) {
        console.error('Error saving profile:', error);
      }
    }
  };

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Typography variant="h6" gutterBottom>
              Step 1: Personal Info
            </Typography>
            
            <TextField
              label="Name"
              name="name"
              value={profile.name}
              onChange={handleChange}
              fullWidth
              required
              error={!!errors.name}
              helperText={errors.name}
              inputProps={{ pattern: "[a-zA-Z ]+" }}
            />
            
            <TextField
              label="Email"
              name="email"
              type="email"
              value={profile.email}
              onChange={handleChange}
              fullWidth
              required
              error={!!errors.email}
              helperText={errors.email}
            />
            
            <TextField
              label="Phone"
              name="phone"
              value={profile.phone}
              onChange={handleChange}
              fullWidth
              required
              error={!!errors.phone}
              helperText={errors.phone}
              inputProps={{ pattern: "[0-9]{10}", maxLength: 10 }}
            />
          </Box>
        );
      case 1:
        return (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Typography variant="h6" gutterBottom>
              Step 2: Education Info
            </Typography>
            
            <TextField
              label="Degree"
              name="education.degree"
              value={profile.education.degree}
              onChange={handleChange}
              fullWidth
              required
              error={!!errors.education?.degree}
              helperText={errors.education?.degree}
            />
            
            <TextField
              label="Institution"
              name="education.institution"
              value={profile.education.institution}
              onChange={handleChange}
              fullWidth
              required
              error={!!errors.education?.institution}
              helperText={errors.education?.institution}
            />
            
            <TextField
              label="Year"
              name="education.year"
              value={profile.education.year}
              onChange={handleChange}
              fullWidth
              required
              error={!!errors.education?.year}
              helperText={errors.education?.year}
              inputProps={{ pattern: "[0-9]{4}", maxLength: 4 }}
            />
          </Box>
        );
      case 2:
        return (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Typography variant="h6" gutterBottom>
              Step 3: Interests
            </Typography>
            
            <TextField
              label="Interests (comma-separated)"
              name="interests"
              value={profile.interests}
              onChange={handleChange}
              fullWidth
              multiline
              rows={3}
              placeholder="e.g., Reading, Traveling, Photography"
            />
            
            <Typography variant="body2" color="text.secondary">
              Separate multiple interests with commas
            </Typography>
          </Box>
        );
      case 3:
        return (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Typography variant="h6" gutterBottom>
              Step 4: Achievements
            </Typography>
            
            <TextField
              label="Achievements (comma-separated)"
              name="achievements"
              value={profile.achievements}
              onChange={handleChange}
              fullWidth
              multiline
              rows={3}
              placeholder="e.g., Employee of the Month, Best Project Award, Published Author"
            />
            
            <Typography variant="body2" color="text.secondary">
              Separate multiple achievements with commas
            </Typography>
            
            {Object.keys(errors).length > 0 && (
              <Alert severity="error" sx={{ mt: 2 }}>
                Please fix all errors before submitting
              </Alert>
            )}
          </Box>
        );
      default:
        return 'Unknown step';
    }
  };

  const isStepValid = (step) => {
    switch (step) {
      case 0:
        return profile.name && profile.email && profile.phone && 
               !errors.name && !errors.email && !errors.phone;
      case 1:
        return profile.education.degree && profile.education.institution && profile.education.year &&
               !errors.education?.degree && !errors.education?.institution && !errors.education?.year;
      case 2:
      case 3:
        return true;
      default:
        return false;
    }
  };

  return (
    <Paper elevation={3} sx={{ p: 3, mt: 3 }}>
      <Stepper activeStep={activeStep} alternativeLabel>
        {steps.map((label) => (
          <Step key={label}>
            <StepLabel>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>
      <Divider sx={{ my: 3 }} />
      <Box>
        {getStepContent(activeStep)}
        <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
          <Button
            disabled={activeStep === 0}
            onClick={handleBack}
            variant="outlined"
          >
            Previous
          </Button>
          {activeStep === steps.length - 1 ? (
            <Button
              variant="contained"
              onClick={handleSubmit}
              disabled={!isStepValid(0) || !isStepValid(1)}
            >
              Submit
            </Button>
          ) : (
            <Button
              variant="contained"
              onClick={handleNext}
            >
              Next
            </Button>
          )}
        </Box>
      </Box>
    </Paper>
  );
};

export default ProfileForm;
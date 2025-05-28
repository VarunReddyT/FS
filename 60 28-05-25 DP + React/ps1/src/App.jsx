import React from 'react';
import ProductTable from './components/ProductTable';
import { Container, Typography } from '@mui/material';

function App() {
  return (
    <Container maxWidth="lg" style={{ marginTop: '2rem' }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Product Catalog
      </Typography>
      <ProductTable />
    </Container>
  );
}

export default App;
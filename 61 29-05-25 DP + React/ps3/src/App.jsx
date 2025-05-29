import { useState, useEffect } from 'react';
import { Container, Typography, TextField, Box, Paper } from '@mui/material';
import ProductTable from './components/ProductTable';
import axios from 'axios';

function App() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [pagination, setPagination] = useState({
    page: 0,
    pageSize: 10,
    total: 0,
  });

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const response = await axios.get(
        `http://10.11.18.16:4000/api/products?page=${pagination.page + 1}&limit=${
          pagination.pageSize
        }&name=${searchTerm}`
      );
      setProducts(response.data.products);
      setPagination(prev => ({
        ...prev,
        total: response.data.totalProducts,
      }));
    } catch (error) {
      console.error('Error fetching products:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [pagination.page, pagination.pageSize, searchTerm]);

  const handleSearch = e => {
    setSearchTerm(e.target.value);
    setPagination(prev => ({ ...prev, page: 0 }));
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        KMIT Group - MERN Product Management App
      </Typography>
      <Box sx={{ mb: 2 }}>
        <TextField
          label="Search Products by Name"
          variant="outlined"
          fullWidth
          value={searchTerm}
          onChange={handleSearch}
          placeholder="Search Products by Name"
        />
      </Box>
      <Paper elevation={3}>
        <ProductTable
          products={products}
          loading={loading}
          pagination={pagination}
          setPagination={setPagination}
        />
      </Paper>
    </Container>
  );
}

export default App;
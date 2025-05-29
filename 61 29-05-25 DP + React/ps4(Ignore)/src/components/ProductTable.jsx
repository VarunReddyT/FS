import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TablePagination,
  LinearProgress,
  TableFooter,
  TextField,
  Button,
  CircularProgress,
} from '@mui/material';
import { useState } from 'react';
import axios from 'axios';

const ProductTable = ({ products, loading, pagination, setPagination, fetchProducts }) => {
  const [editingId, setEditingId] = useState(null);
  const [editData, setEditData] = useState({});
  const [deletingId, setDeletingId] = useState(null);

  const handleChangePage = async(event, newPage) => {
    setPagination(prev => ({ ...prev, page: newPage }));
    await fetchProducts();
  };

  const handleChangeRowsPerPage = async event => {
    setPagination(prev => ({
      ...prev,
      pageSize: parseInt(event.target.value, 10),
      page: 0,
    }));
    await fetchProducts();
  };

  const handleEditClick = (product) => {
    setEditingId(product._id);
    setEditData({
      name: product.name,
      price: product.price,
      category: product.category,
      inStock: product.inStock,
    });
  };

  const handleSaveClick = async (id) => {
    try {
      await axios.put(`http://10.11.18.16:4000/api/products/${id}`, editData);
      setEditingId(null);
      fetchProducts();
    } catch (error) {
      console.error('Error updating product:', error);
    }
  };

  const handleDeleteClick = async (id) => {
    try {
      setDeletingId(id);
      await axios.delete(`http://10.11.18.16:4000/api/products/${id}`);
      // If we're on the last item of a page, go to previous page
      if (products.length === 1 && pagination.page > 0) {
        setPagination(prev => ({ ...prev, page: prev.page - 1 }));
      }
      fetchProducts();
    } catch (error) {
      console.error('Error deleting product:', error);
    } finally {
      setDeletingId(null);
    }
  };

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEditData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }));
  };

  return (
    <TableContainer>
      <Table data-testid="product-table">
        <TableHead>
          <TableRow sx={{ bgcolor: '#1976d2' }}>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Name</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Price</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Category</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>In Stock</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {loading ? (
            <TableRow>
              <TableCell colSpan={5}>
                <LinearProgress />
              </TableCell>
            </TableRow>
          ) : products.length === 0 ? (
            <TableRow>
              <TableCell colSpan={5} align="center">
                No products found
              </TableCell>
            </TableRow>
          ) : (
            products.map(product => (
              <TableRow 
                key={product._id}
                data-testid={`product-row-${product._id}`}
              >
                <TableCell data-testid={`product-name-${product._id}`}>
                  {editingId === product._id ? (
                    <TextField
                      name="name"
                      value={editData.name}
                      onChange={handleInputChange}
                      size="small"
                      inputProps={{ 'data-testid': `edit-name-${product._id}` }}
                    />
                  ) : (
                    product.name
                  )}
                </TableCell>
                <TableCell data-testid={`product-price-${product._id}`}>
                  {editingId === product._id ? (
                    <TextField
                      name="price"
                      type="number"
                      value={editData.price}
                      onChange={handleInputChange}
                      size="small"
                      inputProps={{ 'data-testid': `edit-price-${product._id}` }}
                    />
                  ) : (
                    `$${product.price.toFixed(2)}`
                  )}
                </TableCell>
                <TableCell data-testid={`product-category-${product._id}`}>
                  {editingId === product._id ? (
                    <TextField
                      name="category"
                      value={editData.category}
                      onChange={handleInputChange}
                      size="small"
                      inputProps={{ 'data-testid': `edit-category-${product._id}` }}
                    />
                  ) : (
                    product.category
                  )}
                </TableCell>
                <TableCell data-testid={`product-stock-${product._id}`}>
                  {editingId === product._id ? (
                    <select
                      name="inStock"
                      value={editData.inStock}
                      onChange={handleInputChange}
                      data-testid={`edit-stock-${product._id}`}
                    >
                      <option value={true}>Yes</option>
                      <option value={false}>No</option>
                    </select>
                  ) : (
                    product.inStock ? 'Yes' : 'No'
                  )}
                </TableCell>
                <TableCell>
                  {editingId === product._id ? (
                    <Button
                      variant="contained"
                      color="success"
                      size="small"
                      onClick={() => handleSaveClick(product._id)}
                      data-testid={`save-btn-${product._id}`}
                    >
                      Save
                    </Button>
                  ) : (
                    <>
                      <Button
                        variant="contained"
                        color="primary"
                        size="small"
                        onClick={() => handleEditClick(product)}
                        data-testid={`edit-btn-${product._id}`}
                        sx={{ mr: 1 }}
                      >
                        Edit
                      </Button>
                      <Button
                        variant="contained"
                        color="error"
                        size="small"
                        onClick={() => handleDeleteClick(product._id)}
                        data-testid={`delete-btn-${product._id}`}
                        disabled={deletingId === product._id}
                      >
                        {deletingId === product._id ? (
                          <CircularProgress size={24} color="inherit" />
                        ) : (
                          'Delete'
                        )}
                      </Button>
                    </>
                  )}
                </TableCell>
              </TableRow>
            ))
          )}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TablePagination
              rowsPerPageOptions={[10, 25, 50, 100]}
              colSpan={5}
              count={pagination.total}
              rowsPerPage={pagination.pageSize}
              page={pagination.page}
              onPageChange={handleChangePage}
              onRowsPerPageChange={handleChangeRowsPerPage}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </TableContainer>
  );
};

export default ProductTable;
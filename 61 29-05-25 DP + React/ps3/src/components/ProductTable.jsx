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
} from '@mui/material';
import { useState } from 'react';

const ProductTable = ({ products, loading, pagination, setPagination }) => {
  const handleChangePage = (event, newPage) => {
    setPagination(prev => ({ ...prev, page: newPage }));
  };

  const handleChangeRowsPerPage = event => {
    setPagination(prev => ({
      ...prev,
      pageSize: parseInt(event.target.value, 10),
      page: 0,
    }));
  };

  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow sx={{ bgcolor: '#1976d2' }}>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Name</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Price</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Category</TableCell>
            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>In Stock</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {loading ? (
            <TableRow>
              <TableCell colSpan={4}>
                <LinearProgress />
              </TableCell>
            </TableRow>
          ) : products.length === 0 ? (
            <TableRow>
              <TableCell colSpan={4} align="center">
                No products found
              </TableCell>
            </TableRow>
          ) : (
            products.map(product => (
              <TableRow key={product._id}>
                <TableCell>{product.name}</TableCell>
                <TableCell>${product.price.toFixed(2)}</TableCell>
                <TableCell>{product.category}</TableCell>
                <TableCell>
                    {product.inStock ? 'Yes' : 'No'}
                </TableCell>
              </TableRow>
            ))
          )}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TablePagination
              rowsPerPageOptions={[10, 25, 50, 100]}
              colSpan={4}
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
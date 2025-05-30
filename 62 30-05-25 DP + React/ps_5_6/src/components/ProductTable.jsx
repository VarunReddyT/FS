import React, { useEffect, useState, useCallback, useMemo } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    TablePagination,
    CircularProgress,
    Box,
    TextField,
    InputAdornment,
    Button,
    Switch,
    FormControlLabel
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';
import productService from './productService';

// Custom styling for the table header
const StyledTableHead = styled(TableHead)(({ theme }) => ({
    backgroundColor: '#2196f3',
    '& .MuiTableCell-head': {
        color: theme.palette.common.white,
        fontWeight: 'bold',
    },
}));

const ProductTable = () => {
    const [allProducts, setAllProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [searchTerm, setSearchTerm] = useState('');
    const [editingId, setEditingId] = useState(null);
    const [editedProduct, setEditedProduct] = useState({});

    // Get role from localStorage
    const role = useMemo(() => {
        try {
            return localStorage.getItem('role');
        } catch (e) {
            return null;
        }
    }, []);

    // Fetch all products once on component mount
    const fetchAllProducts = useCallback(async () => {
        setLoading(true);
        try {
            const data = await productService.getProducts();
            setAllProducts(data);
            setLoading(false);
        } catch (error) {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchAllProducts();
    }, [fetchAllProducts]);

    // Filter products based on search term
    const filteredProducts = useMemo(() => {
        if (!searchTerm) return allProducts;
        const lowercasedSearchTerm = searchTerm.toLowerCase();
        return allProducts.filter(product =>
            product.name.toLowerCase().includes(lowercasedSearchTerm)
        );
    }, [allProducts, searchTerm]);

    // Apply pagination logic
    const paginatedProducts = filteredProducts.slice(
        page * rowsPerPage,
        page * rowsPerPage + rowsPerPage
    );

    const handleChangePage = (event, newPage) => setPage(newPage);
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
        setPage(0);
    };

    const handleEditClick = (product) => {
        setEditingId(product._id);
        setEditedProduct({ ...product });
    };

    const handleSaveClick = async (id) => {
        try {
            await productService.updateProduct(id, editedProduct);
            setEditingId(null);
            setEditedProduct({});
            fetchAllProducts();
        } catch (error) {}
    };

    const handleDeleteClick = async (id) => {
        try {
            await productService.deleteProduct(id);
            fetchAllProducts();
        } catch (error) {}
    };

    const handleInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setEditedProduct(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    if (loading) {
        return (
            <Box display="flex" justifyContent="center" alignItems="center" height="200px">
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Paper sx={{ width: '100%', overflow: 'hidden' }}>
            <Box sx={{ p: 2 }}>
                <TextField
                    label="Search Products by Name"
                    variant="outlined"
                    fullWidth
                    value={searchTerm}
                    onChange={handleSearchChange}
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <SearchIcon />
                            </InputAdornment>
                        ),
                    }}
                    sx={{ mb: 2 }}
                />
            </Box>
            <TableContainer sx={{ maxHeight: 600 }}>
                <Table stickyHeader aria-label="product table">
                    <StyledTableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell align="right">Price</TableCell>
                            <TableCell>Category</TableCell>
                            <TableCell>In Stock</TableCell>
                            {/* Only show Actions column for admin */}
                            {role === 'admin' && <TableCell>Actions</TableCell>}
                        </TableRow>
                    </StyledTableHead>
                    <TableBody>
                        {paginatedProducts.map((product) => (
                            <TableRow key={product._id}>
                                {editingId === product._id ? (
                                    <>
                                        <TableCell>
                                            <TextField
                                                name="name"
                                                value={editedProduct.name || ''}
                                                onChange={handleInputChange}
                                                size="small"
                                            />
                                        </TableCell>
                                        <TableCell align="right">
                                            <TextField
                                                name="price"
                                                type="number"
                                                value={editedProduct.price}
                                                onChange={handleInputChange}
                                                size="small"
                                            />
                                        </TableCell>
                                        <TableCell>
                                            <TextField
                                                name="category"
                                                value={editedProduct.category || ''}
                                                onChange={handleInputChange}
                                                size="small"
                                            />
                                        </TableCell>
                                        <TableCell>
                                            <FormControlLabel
                                                control={
                                                    <Switch
                                                        name="inStock"
                                                        checked={editedProduct.inStock || false}
                                                        onChange={handleInputChange}
                                                    />
                                                }
                                                label={editedProduct.inStock ? 'Yes' : 'No'}
                                            />
                                        </TableCell>
                                        {/* Show Save only for admin */}
                                        {role === 'admin' && (
                                            <TableCell>
                                                <Button
                                                    variant="contained"
                                                    color="primary"
                                                    onClick={() => handleSaveClick(product._id)}
                                                    size="small"
                                                >
                                                    Save
                                                </Button>
                                            </TableCell>
                                        )}
                                    </>
                                ) : (
                                    <>
                                        <TableCell>{product.name}</TableCell>
                                        <TableCell align="right">${product.price.toFixed(2)}</TableCell>
                                        <TableCell>{product.category}</TableCell>
                                        <TableCell>{product.inStock ? 'Yes' : 'No'}</TableCell>
                                        {/* Show Edit/Delete only for admin */}
                                        {role === 'admin' && (
                                            <TableCell>
                                                <Button
                                                    variant="outlined"
                                                    color="primary"
                                                    onClick={() => handleEditClick(product)}
                                                    size="small"
                                                    sx={{ mr: 1 }}
                                                >
                                                    Edit
                                                </Button>
                                                <Button
                                                    variant="outlined"
                                                    color="error"
                                                    onClick={() => handleDeleteClick(product._id)}
                                                    size="small"
                                                >
                                                    Delete
                                                </Button>
                                            </TableCell>
                                        )}
                                    </>
                                )}
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 50, 100]}
                component="div"
                count={filteredProducts.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </Paper>
    );
};

export default ProductTable;
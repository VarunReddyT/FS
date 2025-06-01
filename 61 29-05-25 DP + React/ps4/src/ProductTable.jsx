import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, TablePagination, Checkbox, TextField
} from '@mui/material';

const columns = [
  { id: 'name', label: 'Name' },
  { id: 'price', label: 'Price' },
  { id: 'category', label: 'Category' },
  { id: 'inStock', label: 'In Stock' },
];

export default function ProductTable() {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [search, setSearch] = useState('');
  const [editId, setEditId] = useState(null);
  const [editRow, setEditRow] = useState({});

  useEffect(() => {
    axios.get('http://10.11.18.16:5000/api/products')
      .then(res => setProducts(res.data))
      .catch(() => setProducts([]));
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  // Filter products by search (case-insensitive, partial match)
  const filteredProducts = products.filter((p) =>
    p.name.toLowerCase().includes(search.toLowerCase())
  );

  const handleEdit = (row) => {
    setEditId(row._id);
    setEditRow({ ...row });
  };

  const handleEditChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEditRow((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }));
  };

  const handleSave = async (id) => {
    try {
      const res = await axios.put(`http://10.11.18.16:5000/api/products/${id}`, editRow);
      setProducts((prev) => prev.map((p) => (p._id === id ? res.data : p)));
      setEditId(null);
    } catch (err) {
      // handle error if needed
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://10.11.18.16:5000/api/products/${id}`);
      setProducts((prev) => prev.filter((p) => p._id !== id));
    } catch (err) {
      // handle error if needed
    }
  };

  return (
    <Paper sx={{ width: '100%', overflow: 'hidden', marginTop: 4 }}>
      <div style={{ padding: 16, background: 'white' }}>
        <TextField
          label="Search Products by Name"
          variant="outlined"
          value={search}
          onChange={e => { setSearch(e.target.value); setPage(0); }}
          fullWidth
        />
      </div>
      <TableContainer>
        <Table stickyHeader>
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell key={column.id} style={{color:"beige",backgroundColor:"blue"}}><strong>{column.label}</strong></TableCell>
              ))}
              <TableCell style={{color:"beige",backgroundColor:"blue"}}><strong>Actions</strong></TableCell>
            </TableRow>
          </TableHead>
          <TableBody style={{backgroundColor:"beige"}}>
            {filteredProducts.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row) => (
              <TableRow hover key={row._id} style={{color:"black"}}>
                {editId === row._id ? (
                  <>
                    <TableCell>
                      <TextField name="name" value={editRow.name} onChange={handleEditChange} size="small" />
                    </TableCell>
                    <TableCell>
                      <TextField name="price" type="number" value={editRow.price} onChange={handleEditChange} size="small" />
                    </TableCell>
                    <TableCell>
                      <TextField name="category" value={editRow.category} onChange={handleEditChange} size="small" />
                    </TableCell>
                    <TableCell>
                      <TextField name="inStock" value={editRow.inStock ? 'Yes' : 'No'} onChange={e => setEditRow(prev => ({ ...prev, inStock: e.target.value.toLowerCase() === 'yes' }))} size="small" />
                    </TableCell>
                  </>
                ) : (
                  <>
                    <TableCell>{row.name}</TableCell>
                    <TableCell>${row.price}</TableCell>
                    <TableCell>{row.category}</TableCell>
                    <TableCell>{row.inStock ? 'Yes' : 'No'}</TableCell>
                  </>
                )}
                <TableCell>
                  {editId === row._id ? (
                    <>
                      <button onClick={() => handleSave(row._id)} style={{marginRight: 8}}>Save</button>
                      <button onClick={() => setEditId(null)}>Cancel</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEdit(row)} style={{marginRight: 8}}>Edit</button>
                      <button onClick={() => handleDelete(row._id)} style={{color: 'red'}}>Delete</button>
                    </>
                  )}
                </TableCell>
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
        style={{backgroundColor:"blue",color:"beige"}}
      />
    </Paper>
  );
}

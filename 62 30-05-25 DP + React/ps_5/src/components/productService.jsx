import axios from 'axios'; //

const API_BASE_URL =  'http://10.11.25.136:5000'; // Using Vite's env variable
const token = JSON.parse(localStorage.getItem('auth'))?.token



const productService = {
    // getProducts no longer accepts a searchTerm as filtering is done on the frontend
    getProducts: async () => {
        console.log(token);
        try {
            console.log('Fetching all products from:', API_BASE_URL); // Log the API URL for debugging
            const response = await axios.get(API_BASE_URL+`/api/products`); // Always fetch all products
            return response.data;
        } catch (error) {
            console.error('Error fetching products:', error);
            throw error;
        }
    },

    // New function to update a product [cite: 3]
    updateProduct: async (id, productData) => {
        try {
            const response = await axios.put(API_BASE_URL+`/api/products/${id}`, productData); // [cite: 3]
            return response.data;
        } catch (error) {
            console.error("error");
            throw error;
        }
    },

    // New function to delete a product [cite: 4]
    deleteProduct: async (id) => {
        try {
            const response = await axios.delete(API_BASE_URL+`/api/products/${id}`); // [cite: 4]
            return response.data;
        } catch (error) {
            console.error("error");
            throw error;
        }
    }
};

export default productService;
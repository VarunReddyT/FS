import React from 'react'
import { useState } from 'react'

export default function EmployeeForm({ setEmployees }) {
    const pincodePattern = /^[0-9]{6}$/;
    const panPattern = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$/;
    const aadharPattern = /^[0-9]{12}$/;
    const mobilePattern = /^[0-9]{10}$/;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    const [employeeData, setEmployeeData] = useState({
        fullName: '',
        email: '',
        mobileNumber: '',
        dob: '',
        aadhar: '',
        pan: '',
        address: {
            houseNo: '',
            buildingName: '',
            area: '',
            city: '',
            state: '',
            pincode: ''
        }
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEmployeeData({
            ...employeeData,
            [name]: value
        });
    }
    
    const handleAddressChange = (e) => {
        const { name, value } = e.target;
        setEmployeeData({
            ...employeeData,
            address: {
                ...employeeData.address,
                [name]: value
            }
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const { fullName, email, mobileNumber, dob, aadhar, pan, address } = employeeData;
        if (!fullName || !email || !mobileNumber || !dob || !aadhar || !pan || !address.houseNo || !address.buildingName || !address.area || !address.city || !address.state || !address.pincode) {
            alert('All fields are required');
            return;
        }
        if (!emailPattern.test(email)) {
            alert('Invalid email format');
            return;
        }
        if (!mobilePattern.test(mobileNumber)) {
            alert('Invalid mobile number format');
            return;
        }
        if (!aadharPattern.test(aadhar)) {
            alert('Invalid AADHAR number format');
            return;
        }
        if (!panPattern.test(pan)) {
            alert('Invalid PAN number format');
            return;
        }
        if (!pincodePattern.test(address.pincode)) {
            alert('Invalid Pincode format');
            return;
        }

        const employeeId = Date.now();
        const employeeStatus = 'Pending';
        const employee = {
            id: employeeId,
            status: employeeStatus,
            ...employeeData
        };
        
        setEmployees((prevEmployees) => [...prevEmployees, employee]);
        alert('Employee registered successfully');
    }

    return (
        <form onSubmit={handleSubmit} className="container mt-5">
            <h1 className="text-center mb-4">Employee Form</h1>
            
            <div className="row mb-4">
                <div className="col-md-6 mb-3">
                    <label htmlFor="fullName" className="form-label">Full Name</label>
                    <input type="text" className="form-control" id="fullName" name="fullName" 
                           value={employeeData.fullName} onChange={handleChange} required />
                </div>
                <div className="col-md-6 mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input type="email" className="form-control" id="email" name="email" 
                           value={employeeData.email} onChange={handleChange} required />
                </div>
                <div className="col-md-6 mb-3">
                    <label htmlFor="mobileNumber" className="form-label">Mobile Number</label>
                    <input type="text" className="form-control" id="mobileNumber" name="mobileNumber" 
                           value={employeeData.mobileNumber} onChange={handleChange} required />
                </div>
                <div className="col-md-6 mb-3">
                    <label htmlFor="dob" className="form-label">Date of Birth</label>
                    <input type="date" className="form-control" id="dob" name="dob" 
                           value={employeeData.dob} onChange={handleChange} required />
                </div>
                <div className="col-md-6 mb-3">
                    <label htmlFor="aadhar" className="form-label">AADHAR</label>
                    <input type="text" className="form-control" id="aadhar" name="aadhar" 
                           value={employeeData.aadhar} onChange={handleChange} required />
                </div>
                <div className="col-md-6 mb-3">
                    <label htmlFor="pan" className="form-label">PAN</label>
                    <input type="text" className="form-control" id="pan" name="pan" 
                           value={employeeData.pan} onChange={handleChange} required />
                </div>
            </div>

            <h2 className="mb-3">Address</h2>
            <div className="row mb-4">
                <div className="col-lg-4 col-md-6 mb-3">
                    <label htmlFor="houseNo" className="form-label">House No</label>
                    <input type="text" className="form-control" id="houseNo" name="houseNo" 
                           value={employeeData.address.houseNo} onChange={handleAddressChange} required />
                </div>
                <div className="col-lg-4 col-md-6 mb-3">
                    <label htmlFor="buildingName" className="form-label">Building Name</label>
                    <input type="text" className="form-control" id="buildingName" name="buildingName" 
                           value={employeeData.address.buildingName} onChange={handleAddressChange} required />
                </div>
                <div className="col-lg-4 col-md-6 mb-3">
                    <label htmlFor="area" className="form-label">Area</label>
                    <input type="text" className="form-control" id="area" name="area" 
                           value={employeeData.address.area} onChange={handleAddressChange} required />
                </div>
                <div className="col-lg-4 col-md-6 mb-3">
                    <label htmlFor="city" className="form-label">City</label>
                    <input type="text" className="form-control" id="city" name="city" 
                           value={employeeData.address.city} onChange={handleAddressChange} required />
                </div>
                <div className="col-lg-4 col-md-6 mb-3">
                    <label htmlFor="state" className="form-label">State</label>
                    <input type="text" className="form-control" id="state" name="state" 
                           value={employeeData.address.state} onChange={handleAddressChange} required />
                </div>
                <div className="col-lg-4 col-md-6 mb-3">
                    <label htmlFor="pincode" className="form-label">Pincode</label>
                    <input type="text" className="form-control" id="pincode" name="pincode" 
                           value={employeeData.address.pincode} onChange={handleAddressChange} required />
                </div>
            </div>

            <div className="d-grid gap-2 col-md-6 mx-auto">
                <button type="submit" className="btn btn-primary btn-lg">Submit</button>
            </div>
        </form>
    )
}
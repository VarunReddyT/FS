import React from 'react'
import { useState } from 'react'

export default function EmployeeForm() {

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
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            alert('Invalid email format');
            return;
        }
        const mobilePattern = /^[0-9]{10}$/;
        if (!mobilePattern.test(mobileNumber)) {
            alert('Invalid mobile number format');
            return;
        }
        const aadharPattern = /^[0-9]{12}$/;
        if (!aadharPattern.test(aadhar)) {
            alert('Invalid AADHAR number format');
            return;
        }
        const panPattern = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;
        if (!panPattern.test(pan)) {
            alert('Invalid PAN number format');
            return;
        }
        const pincodePattern = /^[0-9]{6}$/;
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
        
        // use State
    }
  return (
    <form onSubmit={handleSubmit} className="container mt-5">
        <h1 className="text-center">Employee Form</h1>
        <div className="mb-3">
            <label htmlFor="fullName" className="form-label">Full Name</label>
            <input type="text" className="form-control" id="fullName" name="fullName" value={employeeData.fullName} onChange={handleChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input type="email" className="form-control" id="email" name="email" value={employeeData.email} onChange={handleChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="mobileNumber" className="form-label">Mobile Number</label>
            <input type="text" className="form-control" id="mobileNumber" name="mobileNumber" value={employeeData.mobileNumber} onChange={handleChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="dob" className="form-label">Date of Birth</label>
            <input type="date" className="form-control" id="dob" name="dob" value={employeeData.dob} onChange={handleChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="aadhar" className="form-label">AADHAR</label>
            <input type="text" className="form-control" id="aadhar" name="aadhar" value={employeeData.aadhar} onChange={handleChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="pan" className="form-label">PAN</label>
            <input type="text" className="form-control" id="pan" name="pan" value={employeeData.pan} onChange={handleChange} required />
        </div>
        <h2>Address</h2>
        <div className="mb-3">
            <label htmlFor="houseNo" className="form-label">House No</label>
            <input type="text" className="form-control" id="houseNo" name="houseNo" value={employeeData.address.houseNo} onChange={handleAddressChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="buildingName" className="form-label">Building Name</label>
            <input type="text" className="form-control" id="buildingName" name="buildingName" value={employeeData.address.buildingName} onChange={handleAddressChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="area" className="form-label">Area</label>
            <input type="text" className="form-control" id="area" name="area" value={employeeData.address.area} onChange={handleAddressChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="city" className="form-label">City</label>
            <input type="text" className="form-control" id="city" name="city" value={employeeData.address.city} onChange={handleAddressChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="state" className="form-label">State</label>
            <input type="text" className="form-control" id="state" name="state" value={employeeData.address.state} onChange={handleAddressChange} required />
        </div>
        <div className="mb-3">
            <label htmlFor="pincode" className="form-label">Pincode</label>
            <input type="text" className="form-control" id="pincode" name="pincode" value={employeeData.address.pincode} onChange={handleAddressChange} required />
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
    </form>
  )
}

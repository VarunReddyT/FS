import React from 'react'

export default function EmployeeList({employees}) {
  return (
    <div className="container mt-5">
      <h2>Employee List</h2>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Mobile Number</th>
            <th>DOB</th>
            <th>Aadhar</th>
            <th>PAN</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee, index) => (
            <tr key={index}>
              <td>{employee.fullName}</td>
              <td>{employee.email}</td>
              <td>{employee.mobileNumber}</td>
              <td>{employee.dob}</td>
              <td>{employee.aadhar}</td>
              <td>{employee.pan}</td>
              <td>{`${employee.address.houseNo}, ${employee.address.buildingName}, ${employee.address.area}, ${employee.address.city}, ${employee.address.state}, ${employee.address.pincode}`}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

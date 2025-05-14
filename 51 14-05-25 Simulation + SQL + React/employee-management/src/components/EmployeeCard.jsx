import React from 'react'

export default function EmployeeCard({employee}) {
  return (
    <div className="modal fade" id="employeeModal" tabIndex="-1" aria-labelledby="employeeModalLabel" aria-hidden="true">
      <div className="modal-dialog modal-lg">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="employeeModalLabel">Employee Details</h5>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div className="modal-body">
            <h4>{employee.fullName}</h4>
            <p><strong>Email:</strong> {employee.email}</p>
            <p><strong>Mobile Number:</strong> {employee.mobileNumber}</p>
            <p><strong>DOB:</strong> {employee.dob}</p>
            <p><strong>Aadhar:</strong> {employee.aadhar}</p>
            <p><strong>PAN:</strong> {employee.pan}</p>
            <h5>Address</h5>
            <p>{`${employee.address.houseNo}, ${employee.address.buildingName}, ${employee.address.area}, ${employee.address.city}, ${employee.address.state}, ${employee.address.pincode}`}</p>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  )
}

import React from 'react'
import EmployeeCard from './EmployeeCard'

export default function HRPanel({employees}) {
  return (
    <div className="container mt-5">
      <h2>HR Panel</h2>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Details</th>
          </tr>
        </thead>
        <tbody>
          {employees.filter(employee => employee.status === 'Approved').map((employee, index) => (
            <tr key={index}>
              <td>{employee.fullName}</td>
              <td>{employee.email}</td>
                <td>
                    <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#employeeModal">
                    View Details
                    </button>
                    <EmployeeCard employee={employee} />
                </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

import React from 'react'
import EmployeeCard from './EmployeeCard';
export default function AdminPanel({ employees, setEmployees }) {

    const handleAction = (employee,index,status) => {
        const updatedEmployees = [...employees];
        updatedEmployees[index].status = status;
        setEmployees(updatedEmployees);
    }
    return (
        <div className="container mt-5">
            <h2>Admin Panel</h2>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee, index) => (
                        <tr key={index}>
                            <td>{employee.fullName}</td> 
                            <td>
                                <span className={`badge ${employee.status === 'Approved' ? 'bg-success' : employee.status === 'Rejected' ? 'bg-danger' : 'bg-warning'}`}>
                                    {employee.status}
                                </span>
                            </td>
                            <td>
                                <button className="btn btn-success" onClick={() => handleAction(employee,index,'Approved')}>Approve</button>
                                <button className="btn btn-danger" onClick={() => handleAction(employee,index,'Rejected')}>Reject</button>
                            </td>
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

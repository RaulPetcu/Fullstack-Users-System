import React, { useState } from 'react'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'

export default function AddUser() {

    let navigate = useNavigate()

    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        email: ""
    })

    const { firstName, lastName, email } = user

    const onINputChange = (event) => {
        setUser({ ...user, [event.target.name]: event.target.value })
    }

    const onSubmit = async (event) => {
        event.preventDefault()
        await axios.post("http://localhost:8080/api/v1/users", user)
        navigate("/")
    }

    const onCancel = () => {
        navigate("/")
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m4">Register User</h2>
                    <form onSubmit={(event) => onSubmit(event)}>
                        <div className="mb-4">
                            <label htmlFor='firstName' className='form-label'>First Name</label>
                            <input type={"text"} className="form-control" placeholder="Enter your first name" name="firstName" value={firstName}
                                onChange={(event) => onINputChange(event)} />
                        </div>
                        <div className="mb-4">
                            <label htmlFor='lastName' className='form-label'>Last Name</label>
                            <input type={"text"} className="form-control" placeholder="Enter your last name" name="lastName" value={lastName}
                                onChange={(event) => onINputChange(event)} />
                        </div>
                        <div className="mb-4">
                            <label htmlFor='email' className='form-label'>Email</label>
                            <input type={"text"} className="form-control" placeholder="Enter your email" name="email" value={email}
                                onChange={(event) => onINputChange(event)} />
                        </div>
                        <button type="submit" className='btn btn-outline-primary'>Submit</button>
                        <Link  className='btn btn-outline-danger mx-3' to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}

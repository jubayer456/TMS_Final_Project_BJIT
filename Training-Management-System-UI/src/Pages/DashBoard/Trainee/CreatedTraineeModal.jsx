import axios from 'axios';
import React from 'react';
import toast from 'react-hot-toast';
import '../../Shared/Register.css'
import { useNavigate } from 'react-router-dom';
const CreatedTraineeModal = ({ setTraineeModal, refetch }) => {
    const navigate=useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const registerData = {
            traineeId: parseInt(e.target.traineeId.value),
            email: e.target.email.value,
            password: e.target.password.value,
            fullName: e.target.fullName.value,
            contactNumber: e.target.contactNumber.value,
            address: e.target.address.value,
            gender: e.target.gender.value,
            dob: e.target.dob.value,
            degreeName: e.target.degreeName.value,
            educationalInstitute: e.target.educationalInstitute.value,
            passingYear: e.target.passingYear.value,
            cgpa: e.target.cgpa.value
        }
        console.log(registerData);
        fetch(`http://localhost:8082/api/trainee`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(registerData)
        })
            .then(res => {
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied please login again`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                if (data.status == 200) {
                    // setTraineeModal(false);
                    toast.success(data.msg)
                }
                else {
                    toast.error(data.msg);
                }
            })
    }
    return (
        <div>
            <input type="checkbox" id="trainee-create-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl'>Trainee Register Form</header>
                    <button onClick={() => setTraineeModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit} className="form">
                        <div className="column">
                            <div className="input-box">
                                <label>Trainee Id</label>
                                <input type="number" name="traineeId" />
                            </div>
                            <div className="input-box">
                                <label>Full Name:</label>
                                <input type="text" name="fullName" placeholder="Full Name:" required />
                            </div>
                        </div>

                        <div className="column">
                            <div className="input-box">
                                <label>Email Address:</label>
                                <input type="email" name="email" placeholder="Enter email address" required />
                            </div>
                            <div className="input-box">
                                <label>Password:</label>
                                <input type="password" name="password" placeholder="Enter Password" required />
                            </div>
                        </div>
                        <div className="column">
                            <div className="input-box">
                                <label>Contact Number:</label>
                                <input type="text" name="contactNumber" placeholder="Enter Contact Number" required />
                            </div>
                            <div className="input-box">
                                <label>Birth Date</label>
                                <input type="date" name="dob" placeholder="Enter birth date" required />
                            </div>
                        </div>
                        <div className="column">
                            <div className="gender-box">
                                <h3>Gender</h3>
                                <div className="gender-option">
                                    <div className="gender">
                                        <input type="radio" id="check-male" name="gender" checked />
                                        <label for="check-male">male</label>
                                    </div>
                                    <div className="gender">
                                        <input type="radio" id="check-female" name="gender" />
                                        <label for="check-female">Female</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="input-box address">
                            <label>Address</label>
                            <input type="text" name="address" placeholder="Enter street address" required />

                            <div className="column">
                                <div className="input-box">
                                    <label>Educational Institute:</label>
                                    <input type="text" name="educationalInstitute" placeholder="Enter Educational Institute" required />
                                </div>
                                <div className="input-box">
                                    <label>Degree Name:</label>
                                    <input type="text" name="degreeName" placeholder="Enter Degree Name" required />
                                </div>
                            </div>
                            <div className="column">
                                <div className="input-box">
                                    <label>Passing Year:</label>
                                    <input type="number" name="passingYear" placeholder="Enter Passing Year" required />
                                </div>
                                <div className="input-box">
                                    <label>CGPA:</label>
                                    <input type="number" name="cgpa" placeholder="Enter CGPA:" required />
                                </div>
                            </div>
                        </div>
                        <button type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default CreatedTraineeModal;
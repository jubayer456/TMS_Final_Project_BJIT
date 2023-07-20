import axios from 'axios';
import React from 'react';
import { toast } from 'react-hot-toast';
import '../../Shared/Register.css'

const CreateTraierModal = ({setTrainerModal,refetch}) => {
    const handleSubmit=(e)=>{
        e.preventDefault();
        const registerData={
            trainerId :e.target.trainerId.value,
            email :e.target.email.value,
            password :e.target.password.value,
            fullName :e.target.fullName.value,
            contactNumber :e.target.contactNumber.value,
            address :e.target.address.value,
            gender :e.target.gender.value,
            designation :e.target.designation.value,
            joiningDate :e.target.joiningDate.value,
            totalYrsExp :e.target.totalYrsExp.value,
            expertises :e.target.expertises.value
        }
         axios.post("http://localhost:8082/api/trainer", registerData,{
                headers: {
                  'Content-Type': 'application/json'
                }
            }).then((response) => {
                refetch();
                setTrainerModal(false);
                toast.success("Successfully Registerd Trainer");
              }) 
              .catch((error) => toast.error(error.response.data.msg));
    }
    return (
        <div>
        <input type="checkbox" id="trainer-create-modal" className="modal-toggle" />
        <div className="modal">
            <div className="modal-box">
            < header className='text-center text-2xl'>Trainer Register Form</header>

            <button onClick={()=>setTrainerModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                     <form onSubmit={handleSubmit}  className="form">
                <div className="column">
                    <div className="input-box">
                        <label>Trainer Id</label>
                        <input type="number" name="trainerId"  placeholder='Enter Trinee Id'/>
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
                        <input type="number" name="contactNumber" placeholder="Enter Contact Number" required />
                    </div>
                    <div className="input-box">
                        <label>Designation</label>
                        <input type="text" name="designation" placeholder="Enter Designation" required />
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
                            <label>Joining Date:</label>
                            <input type="date" name="joiningDate" placeholder="Enter Joining date" required />
                        </div>
                        <div className="input-box">
                            <label>Total Yrs Experince:</label>
                            <input type="number" name="totalYrsExp" placeholder="Enter Total Years Of Exp" required />
                        </div>
                    </div>
                    <div className="column">
                        <div className="input-box">
                            <label>Expertises:</label>
                            <input type="text" name="expertises" placeholder="Enter Expertises" required />
                        </div>
                    </div>
                </div>
                <button htmlFor="trainer-create-modal" type='submit'>Submit</button>
            </form>
            </div>
        </div>
    </div>
    );
};

export default CreateTraierModal;
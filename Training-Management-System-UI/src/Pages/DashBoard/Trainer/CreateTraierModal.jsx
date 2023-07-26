import axios from 'axios';
import React from 'react';
import { toast } from 'react-hot-toast';
import '../../Shared/Register.css'
import { useForm } from 'react-hook-form';

const CreateTraierModal = ({ setTrainerModal, refetch }) => {

    const { register, control, formState: { errors }, handleSubmit } = useForm();


    const validateNonNegative = (value) => {
        const numberValue = parseFloat(value);
        if (isNaN(numberValue) || numberValue < 0) {
            return 'Please enter a non-negative number';
        }
        return true;
    };

    const onsubmit = registerData => {
        console.log(registerData);
   
        axios.post("http://localhost:8082/api/trainer-save", registerData, {
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        }).then((response) => {
            refetch();
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

                    <button onClick={() => setTrainerModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit(onsubmit)} className="form">
                        <div className="column">
                            <div className="input-box">
                                <label>Trainer Id:</label>
                                <input
                                    type="number"
                                    placeholder='Enter Trainer Id'
                                    {...control.register('trainerId', {
                                        required: 'trainerId is required',
                                        validate: validateNonNegative,
                                    })}
                                />
                                <label >
                                    {errors.trainerId && <span className="label-text-alt text-red-500">{errors.trainerId.message}</span>}
                                </label>
                            </div>
                            <div className="input-box">
                                <label>Full Name:</label>
                                <input
                                    type="text"
                                    placeholder='Enter Full Name'
                                    {...control.register('fullName', {
                                        required: 'fullName is required'
                                    })}
                                />
                                <label >
                                    {errors.fullName && <span className="label-text-alt text-red-500">{errors.fullName.message}</span>}
                                </label>
                            </div>
                        </div>

                        <div className="column">
                            <div className="input-box">

                                <label>Email Address:</label>
                                <input
                                    type='email'
                                    placeholder="Your Email"
                                    {...control.register("email", {
                                        required: 'Email is required',
                                        pattern: {
                                            value: /[a-z0-9]+@[a-z]+[a-z]{2,3}/,
                                            message: "Please provide a valid email"
                                        }
                                    })} />
                                <label>
                                    {errors.email?.type === 'required' && <span className="label-text-alt text-red-500">{errors.email.message}</span>}
                                    {errors.email?.type === 'pattern' && <span className="label-text-alt text-red-500">{errors.email.message}</span>}

                                </label>
                            </div>
                            <div className="input-box">
                                <label>Password:</label>
                                <input
                                    type='password'
                                    placeholder="Your Password"
                                    {...control.register("password", {
                                        required: 'password is required',
                                        minLength: {
                                            value: 6,
                                            message: "Please provide mimimum 6 length password"
                                        }
                                    })} />
                                <label >
                                    {errors.password?.type === 'required' && <span className="label-text-alt text-red-500">{errors.password.message}</span>}
                                    {errors.password?.type === 'minLength' && <span className="label-text-alt text-red-500">{errors.password.message}</span>}
                                </label>
                            </div>
                        </div>


                        <div className="column">
                            <div className="input-box">
                                <label>Contact Number:</label>
                                <input
                                    type='number'
                                    placeholder="Enter Your Phone Number"
                                    {...register("contactNumber", {
                                        required: 'trainerId is required',
                                        minLength: {
                                            value: 11,
                                            message: "Please provide mimimum 11 length password"
                                        }
                                    })} />
                                <label >
                                    {errors.contactNumber?.type === 'required' && <span className="label-text-alt text-red-500">{errors.contactNumber.message}</span>}
                                    {errors.contactNumber?.type === 'minLength' && <span className="label-text-alt text-red-500">{errors.contactNumber.message}</span>}
                                </label>
                            </div>
                            <div className="input-box">
                                <label>Designation</label>
                                <input
                                    type="text"
                                    placeholder="Enter designation"
                                    {...control.register('designation', {
                                        required: 'designation is required',
                                    })}
                                />
                                <label >
                                    {errors.designation && <span className="label-text-alt text-red-500">{errors.designation.message}</span>}
                                </label>
                            </div>
                        </div>
                        <div className="column">
                            <div className="gender-box">
                                <h3>Gender</h3>
                                <div className="gender-option">
                                    <div className="gender">
                                        <input
                                            type="radio"
                                            id="check-male"
                                            name="gender"
                                            value="male"
                                            defaultChecked
                                            {...register('gender', { required: 'Gender is required' })}
                                        />
                                        <label htmlFor="check-male">Male</label>
                                    </div>
                                    <div className="gender">
                                        <input
                                            type="radio"
                                            id="check-female"
                                            name="gender"
                                            value="female"
                                            {...register('gender', { required: 'Gender is required' })}
                                        />
                                        <label htmlFor="check-female">Female</label>
                                    </div>
                                </div>
                                <label >{errors.gender && <span className="label-text-alt text-red-500">{errors.gender.message}</span>}</label>
                            </div>
                        </div>
                        <div className="input-box address">
                            <label>Address</label>
                            <input
                                type="text"
                                placeholder="Enter street address"
                                {...control.register('address', {
                                    required: 'address is required',
                                })}
                            />
                            <label >
                                {errors.address && <span className="label-text-alt text-red-500">{errors.address.message}</span>}
                            </label>
                            <div className="column">
                                <div className="input-box">
                                    <label>Joining Date:</label>
                                    <input
                                        type="date"
                                        placeholder="Enter Joining date"
                                        {...control.register('joiningDate', {
                                            required: 'joiningDate is required',
                                        })}
                                    />
                                    <label >
                                        {errors.joiningDate && <span className="label-text-alt text-red-500">{errors.joiningDate.message}</span>}
                                    </label>

                                </div>
                                <div className="input-box">
                                    <label>Total Yrs Experince:</label>
                                    <input
                                        type="number"
                                        placeholder="Enter Total Years Of Experince"
                                        {...control.register('totalYrsExp', {
                                            required: 'Experince is required',
                                            validate: validateNonNegative,
                                        })}
                                    />
                                    <label >
                                        {errors.totalYrsExp && <span className="label-text-alt text-red-500">{errors.totalYrsExp.message}</span>}
                                    </label>
                                </div>
                            </div>
                            <div className="column">
                                <div className="input-box">
                                    <label>Expertises:</label>
                                    <input
                                        type="text"
                                        placeholder="Enter Expertises"
                                        {...control.register('expertises', {
                                            required: 'expertises is required'
                                        })}
                                    />
                                    <label >
                                        {errors.expertises && <span className="label-text-alt text-red-500">{errors.expertises.message}</span>}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <button htmlFor="trainer-create-modal" className='text-white btn btn-primary' type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default CreateTraierModal;
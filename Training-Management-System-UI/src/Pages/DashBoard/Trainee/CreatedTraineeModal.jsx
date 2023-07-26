import axios from 'axios';
import React from 'react';
import toast from 'react-hot-toast';
import '../../Shared/Register.css'
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
const CreatedTraineeModal = ({ setTraineeModal, refetch }) => {

    const { register, control, formState: { errors }, handleSubmit } = useForm();
    const navigate = useNavigate();
  
    const currentYear = new Date().getFullYear();
    const minPassingYear = currentYear - 3;
    const maxPassingYear = currentYear;
    const minCGPA = 0;
    const maxCGPA = 4.0;

    const validatePassingYear = (value) => {
        const year = parseInt(value);
        if (isNaN(year) || year < minPassingYear || year > maxPassingYear) {
            return `Please enter a year between ${minPassingYear} and ${maxPassingYear}`;
        }
        return true;
    };

    const validateBirthYear = (value) => {
        const year = parseInt(value);
        if (isNaN(year) || year >= currentYear ) {
            return `Please enter a year before ${currentYear}`;
        }
        return true;
    };
    const validateCGPA = (value) => {
        const cgpa = parseFloat(value);
        if (isNaN(cgpa) || cgpa < minCGPA || cgpa > maxCGPA) {
            return `Please enter a CGPA between ${minCGPA} and ${maxCGPA}`;
        }
        return true;
    };
      const validateNonNegative = (value) => {
        const numberValue = parseFloat(value);
        if (isNaN(numberValue) || numberValue < 0) {
            return 'Please enter a non-negative number';
        }
        return true;
    };
    const onsubmit = registerData => {
        fetch(`http://localhost:8082/api/trainee-save`, {
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
                    <form onSubmit={handleSubmit(onsubmit)} className="form">
                        <div className="column">
                            <div className="input-box">
                                <label>Trainee Id:</label>
                                <input
                                    type="number"
                                    placeholder='Enter Trainee Id'
                                    {...control.register('traineeId', {
                                        required: 'traineeId is required',
                                        validate: validateNonNegative,
                                    })}
                                />
                                <label >
                                    {errors.traineeId && <span className="label-text-alt text-red-500">{errors.traineeId.message}</span>}
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
                                        required: 'email is required',
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
                                        required: 'contactNumber is required',
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
                                <label>Date Of Birth:</label>
                                <input
                                    type="date"
                                    placeholder="Enter Date Of Birth"
                                    {...control.register('dob', {
                                        required: 'Date Of Birth is required',
                                        validate: validateBirthYear,
                                    })}
                                />
                                <label >
                                    {errors.dob && <span className="label-text-alt text-red-500">{errors.dob.message}</span>}
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
                                    <label>Educational Institute:</label>
                                    <input
                                        type="text"
                                        placeholder="Enter Educational Institute"
                                        {...control.register('educationalInstitute', {
                                            required: 'educational Institute is required'
                                        })}
                                    />
                                    <label >
                                        {errors.educationalInstitute && <span className="label-text-alt text-red-500">{errors.educationalInstitute.message}</span>}
                                    </label>
                                </div>
                                <div className="input-box">
                                    <label>Degree Name:</label>
                                    <input
                                        type="text"
                                        placeholder="Enter Degree Name"
                                        {...control.register('degreeName', {
                                            required: 'degreeName  is required'
                                        })}
                                    />
                                    <label >
                                        {errors.degreeName && <span className="label-text-alt text-red-500">{errors.degreeName.message}</span>}
                                    </label>
                                </div>
                            </div>
                            <div className="column">
                                <div className="input-box">
                                    <label>Passing Year:</label>
                                    <input
                                        type="number"
                                        placeholder='Enter Passing Year'
                                        {...control.register('passingYear', {
                                            required: 'Passing Year is required',
                                            validate: validatePassingYear,
                                        })}
                                    />
                                    <label >
                                        {errors.passingYear && <span className="label-text-alt text-red-500">{errors.passingYear.message}</span>}
                                    </label>
                                </div>
                                <div className="input-box">
                                    <label>CGPA:</label>
                                    <input
                                        type="number"
                                        placeholder='Enter cgpa'
                                        step="0.01"
                                        {...control.register('cgpa', {
                                            required: 'cgpa Year is required',
                                            validate: validateCGPA,
                                        })}
                                    />
                                    <label >
                                        {errors.cgpa && <span className="label-text-alt text-red-500">{errors.cgpa.message}</span>}
                                    </label>
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
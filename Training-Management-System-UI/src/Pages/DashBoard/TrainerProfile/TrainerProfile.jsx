import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import toast from 'react-hot-toast';
import { useQuery } from 'react-query';
import { useUser } from '../../../Context/UserProvider';
import img from '../../../assets/18942381.png'

const TrainerProfile = () => {
    const { register, handleSubmit } = useForm({
    });
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const { data: trainer, refetch, isLoading } = useQuery({
        queryKey: ['getTrainer'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/trainers/${userDetails?.userId}`;

            const headers = {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
            };
            const res = await fetch(url, { headers });
            if (res.status === 401 || res.status === 403) {
                toast.error(`Access denied please login again`);
                localStorage.removeItem('accessToken');
                localStorage.removeItem('myAppState');
                navigate('/login');
            }
            const data = await res.json();
            return data;
        }
    });

    const updateProfile = (e) => {
        e.preventDefault();
        const updatedData = {
            trainerId: e.target.trainerId.value,
            email: e.target.email.value,
            fullName: e.target.fullName.value,
            contactNumber: e.target.contactNumber.value,
            address: e.target.address.value,
            designation: e.target.designation.value,
            joiningDate: e.target.joiningDate.value,
            totalYrsExp: e.target.totalYrsExp.value,
            expertises: e.target.expertises.value
        }
        axios.put(`http://localhost:8082/api/trainer`, updatedData, {
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        }).then((response) => {
            toast.success("Successfully Updated");
        })
            .catch((error) => toast.error(error.response));


    }

    const updateProfilePic = data => {
        const file = data.profilePicture[0];
        const formData = new FormData();
        formData.append('file', file);
        axios.post('http://localhost:8082/api/upload', formData)
            .then((response) => {
                if (response.status == 200) {
                    const updateData = response.data.name;
                    fetch(`http://localhost:8082/api/auth/updatePicture/${userDetails?.userId}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: updateData
                    })
                        .then(res => res.json())
                        .then(data => {
                            console.log(data);
                            if (data.status == 200) {
                                refetch();
                                toast.success(`succesfully Profile Picture Updated`)
                            }
                            else {
                                toast.error(data.msg);
                            }
                        })
                }
                else {
                    toast.error(response.data.msg);
                }
            })
            .catch((error) => {
                console.log(error);
                toast.error("Server Error for Uploading Image");
            });
    };

    return (
        <div className='p-4 m-2'>
            <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
                <h1 className='text-3xl py-4 text-center'>My Profile</h1>
                <div className='hero-content flex-col lg:flex-row-reverse justify-between items-start'>

                    <div>
                        <div className="avatar online">
                            <div className="w-24 rounded-full">
                                {
                                    trainer?.profilePicture ? <img src={`http://localhost:8082/api/download/${trainer?.profilePicture}`} alt="Admin Profile Picture" /> :
                                        <img src={img} alt="Trainee default Profile Picture" />
                                }
                            </div>
                        </div>

                        <form onSubmit={handleSubmit(updateProfilePic)}>
                            <div >
                                <span className="label-text">Change profile Picture</span><br />
                                <input type="file" name="" id=""
                                    {...register('profilePicture')}
                                    required
                                />
                            </div>
                            <input type='submit' value='upload' className='btn btn-sm my-5'></input>
                        </form>
                    </div>
                    <form onSubmit={updateProfile} className="form px-4">
                        <div className="column">
                            <div className="input-box">
                                <label>Trainer Id</label>
                                <input type="number" name="trainerId" value={trainer?.trainerId} disabled />
                            </div>
                            <div className="input-box">
                                <label>Full Name:</label>
                                <input type="text" name="fullName" defaultValue={trainer?.fullName} required />
                            </div>
                        </div>

                        <div className="column">
                            <div className="input-box">
                                <label>Email Address:</label>
                                <input type="email" name="email" defaultValue={trainer?.email} required />
                            </div>
                            <div className="input-box">
                                <label>Designation</label>
                                <input type="text" name="designation" defaultValue={trainer?.designation} required />
                            </div>
                        </div>


                        <div className="column">
                            <div className="input-box">
                                <label>Contact Number:</label>
                                <input type="number" name="contactNumber" defaultValue={trainer?.contactNumber} required />
                            </div>
                            <div className="input-box">
                                <label>Joining Date:</label>
                                <input type="date" name="joiningDate" defaultValue={trainer?.joiningDate} required />
                            </div>

                        </div>
                        <div className="input-box address">
                            <label>Address</label>
                            <input type="text" name="address" defaultValue={trainer?.address} required />

                            <div className="column">

                                <div className="input-box">
                                    <label>Total Yrs Experince:</label>
                                    <input type="number" name="totalYrsExp" defaultValue={trainer?.totalYrsExp} required />
                                </div>
                                <div className="input-box">
                                    <label>Expertises:</label>
                                    <input type="text" name="expertises" defaultValue={trainer?.expertises} required />
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

export default TrainerProfile;
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useQuery } from 'react-query';
import axios from 'axios';
import { toast } from 'react-hot-toast';
import { useUser } from '../../../Context/UserProvider';
import { useNavigate } from 'react-router-dom';
import img from '../../../assets/18942381.png'


const AdminProfile = () => {
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const navigate = useNavigate();
    const { register, handleSubmit } = useForm({
    });
    const { data: admin, refetch, isLoading } = useQuery({
        queryKey: ['getAdmin'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/admins/${userDetails?.userId}`;

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
            adminId: e.target.adminId.value,
            fullName: e.target.fullName.value,
            email: e.target.email.value,
            contactNumber: e.target.contactNumber.value,

        }
        console.log(updatedData)
        fetch(`http://localhost:8082/api/admins/${userDetails?.userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(updatedData)
        })
            .then(res => {
                console.log(res);
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                console.log(data);
                if (data.status == 200) {
                    refetch();
                    toast.success(`succesfully profile Updated`)
                }
                else {
                    toast.error(data.msg);
                }
            })

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
                            'Content-Type': 'application/json',
                            authorization: `Bearer ${localStorage.getItem('accessToken')}`
                        },
                        body: updateData
                    })
                        .then(res => {
                            if (res.status === 401 || res.status === 403) {
                                toast.error(`Access denied`);
                                localStorage.removeItem('accessToken');
                                localStorage.removeItem('myAppState');
                                navigate('/login');
                            }
                            return res.json();
                        })
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
            <div className='bg-white rounded-lg shadow-lg p-6 mb-4'>
                <h1 className='text-3xl py-4 text-center'>My Profile</h1>
                <div className='hero-content flex-col lg:flex-row-reverse justify-between items-start'>

                    <div>
                        <div className="avatar online">
                            <div className="w-24 rounded-full">
                                {
                                   admin?.profilePicture ? <img src={`http://localhost:8082/api/download/${admin?.profilePicture}`} alt="Admin Profile Picture" /> :
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
                                <label>Admin Id</label>
                                <input type="number" name="adminId" value={admin?.userId} disabled />
                            </div>
                            <div className="input-box">
                                <label>Full Name:</label>
                                <input type="text" name="fullName" placeholder="Full Name:" defaultValue={admin?.fullName} required />
                            </div>
                        </div>

                        <div className="column">
                            <div className="input-box">
                                <label>Email Address:</label>
                                <input type="email" name="email" placeholder="Enter email address" defaultValue={admin?.email} required />
                            </div>
                        </div>
                        <div className="column">
                            <div className="input-box">
                                <label>Contact Number:</label>
                                <input type="text" name="contactNumber" defaultValue={admin?.contactNumber} required />
                            </div>
                        </div>
                        <button type='submit' className='btn btn-primary btn-md text-white font bold'>Update</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AdminProfile;
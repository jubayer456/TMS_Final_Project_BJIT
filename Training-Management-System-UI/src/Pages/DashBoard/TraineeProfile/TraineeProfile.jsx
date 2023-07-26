import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-hot-toast';
import { useQuery } from 'react-query';
import '../../Shared/Register.css'
import { useUser } from '../../../Context/UserProvider';
import { useNavigate } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import img from '../../../assets/18942381.png'

const TraineeProfile = () => {
    const navigate = useNavigate();
    const { register, handleSubmit } = useForm({
    });
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const { data: trainee, refetch, isLoading } = useQuery({
        queryKey: ['getTrainee'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/trainees/${userDetails?.userId}`;
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
            traineeId: e.target.traineeId.value,
            email: e.target.email.value,
            fullName: e.target.fullName.value,
            contactNumber: e.target.contactNumber.value,
            address: e.target.address.value,
            dob: e.target.dob.value,
            degreeName: e.target.degreeName.value,
            educationalInstitute: e.target.educationalInstitute.value,
            passingYear: e.target.passingYear.value,
            cgpa: e.target.cgpa.value
        }
        axios.put(`http://localhost:8082/api/trainee`, updatedData, {
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        }).then((response) => {
            refetch();
            toast.success("Successfully Updated");
        })
            .catch((error) => toast.error(error.response));


    }
    if (isLoading) {
        <Loading></Loading>
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
                                toast.error(`${res.statusText} Access`);
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
                                    trainee?.profilePicture ? <img src={`http://localhost:8082/api/download/${trainee?.profilePicture}`} alt="Admin Profile Picture" /> :
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
                                <label>Trainee Id</label>
                                <input type="number" name="traineeId" defaultValue={trainee?.traineeId} />
                            </div>
                            <div className="input-box">
                                <label>Full Name:</label>
                                <input type="text" name="fullName" defaultValue={trainee?.fullName} required />
                            </div>
                        </div>

                        <div className="column">
                            <div className="input-box">
                                <label>Email Address:</label>
                                <input type="email" name="email" defaultValue={trainee?.email} required />
                            </div>
                        </div>


                        <div className="column">
                            <div className="input-box">
                                <label>Contact Number:</label>
                                <input type="number" name="contactNumber" defaultValue={trainee?.contactNumber} required />
                            </div>
                            <div className="input-box">
                                <label>Birth Date</label>
                                <input type="date" name="dob" defaultValue={trainee?.dob} required />
                            </div>
                        </div>

                        <div className="input-box address">
                            <label>Address</label>
                            <input type="text" name="address" defaultValue={trainee?.address} required />

                            <div className="column">
                                <div className="input-box">
                                    <label>Educational Institute:</label>
                                    <input type="text" name="educationalInstitute" defaultValue={trainee?.educationalInstitute} required />
                                </div>
                                <div className="input-box">
                                    <label>Degree Name:</label>
                                    <input type="text" name="degreeName" defaultValue={trainee?.degreeName} required />
                                </div>
                            </div>
                            <div className="column">
                                <div className="input-box">
                                    <label>Passing Year:</label>
                                    <input type="text" name="passingYear" defaultValue={trainee?.passingYear} required />
                                </div>
                                <div className="input-box">
                                    <label>CGPA:</label>
                                    <input type="number" name="cgpa" defaultValue={trainee?.cgpa} required />
                                </div>
                            </div>
                        </div>
                        <button type='submit'>Updated</button>
                    </form>
                </div>
            </div>
        </div>

    );
};

export default TraineeProfile;
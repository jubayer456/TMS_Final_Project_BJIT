import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import toast from 'react-hot-toast';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import '../../Shared/Register.css'

const AssignmentCreatedModalTest = ({ setCreatedModal }) => {
    const navigate = useNavigate();
    const { scheduleId } = useParams();
    const { register, handleSubmit } = useForm({
        defaultValues: {
            scheduleId: scheduleId,
        },
    });
    const onSubmit = data => {
        const file = data.assignmentFile[0];
        const formData = new FormData();
        formData.append('file', file);
        axios.post('http://localhost:8082/api/upload', formData)
            .then((response) => {
                if (response.status == 200) {
                    const assignmentData = {
                        scheduleId: parseInt(scheduleId),
                        assignmentName: data.assignmentName,
                        assignmentFile: response.data.name,
                        deadLine: data.deadLine
                    }
                    console.log(assignmentData);
                    fetch(`http://localhost:8082/api/schedules/${scheduleId}/add-assignment`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            authorization: `Bearer ${localStorage.getItem('accessToken')}`
                        },
                        body: JSON.stringify(assignmentData)
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
                            console.log(data);
                            if (data.status == 200) {
                                // setCreatedModal(false);
                                toast.success(`succesfully Assignment Created`)
                            }
                            else {
                                // setCreatedModal(false);
                                toast.error(data.msg);
                            }
                        })
                }
                else {
                    // setCreatedModal(false);
                    toast.error(response.data.msg);
                }
            })
            .catch((error) => {
                toast.error("Server Error for Uploading Image");
            });
    };

    return (
        <div>
            <input type="checkbox" id="assignment-created-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl'>Assignment Creation Form</header>
                    <button onClick={() => setCreatedModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit(onSubmit)} className="form">
                        <div className="input-box">
                            <label>Schedule Id:</label>
                            <input type="number" {...register("scheduleId")} disabled />
                        </div>
                        <div className="input-box">
                            <label>Assignment Name:</label>
                            <input type="text" {...register("assignmentName", { required: true })} />
                        </div>
                        <div className="input-box">
                            <label>AssignmentFile:</label>
                            <input type="file" {...register("assignmentFile", { required: true })} />
                        </div>
                        <div className="input-box">
                            <label>DeadLine Date:</label>
                            <input type="date" {...register("deadLine", { required: true })} />
                        </div>

                        <button htmlFor="assignment-created-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AssignmentCreatedModalTest;
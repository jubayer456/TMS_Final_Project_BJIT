import React from 'react';
// import Trainee from '../Register/Trainee';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';

const AssignmentSubModal = ({ assignSubModal, setAssignSubModal,traineeId }) => {
    const { assignmentId, scheduleId, assignmentName, assignmentFile, deadLine,batchId } = assignSubModal;
    const navigate=useNavigate();
    const { register, handleSubmit } = useForm({
        defaultValues: {
            assignmentId: assignmentId,
            traineeId: traineeId
        },
    });

    const onSubmit = data => {
        const currentDate = new Date();
        const year = currentDate.getFullYear();
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const day = String(currentDate.getDate()).padStart(2, '0');
        const formattedDate = `${year}-${month}-${day}`;

        const file = data.submissionFile[0];
        const formData = new FormData();
        formData.append('file', file);
        axios.post('http://localhost:8082/api/upload', formData)
            .then((response) => {
                if (response.status == 200) {
                    console.log(response.data);
                    const assignmentSubData = {
                        assignmentId: data.assignmentId,
                        traineeId: data.traineeId,
                        batchId: batchId,
                        submissionFile: response.data.name,
                        submissionDate: formattedDate
                    }
                    console.log(assignmentSubData);
                    fetch(`http://localhost:8082/api/schedules/add-assignmentSub`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            authorization: `Bearer ${localStorage.getItem('accessToken')}`
                        },
                        body: JSON.stringify(assignmentSubData)
                    })
                        .then(res => {
                            console.log(res);
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
                                setAssignSubModal(false);
                                toast.success(`succesfully Assignment Submitted`)
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
                toast.error("Server Error for Uploading Image");
            });
    };
    return (
        <div>
        <input type="checkbox" id="assignmentSub-modal" className="modal-toggle" />
        <div className="modal">
            <div className="modal-box">
                < header className='text-center text-2xl'>Assignment Submission Form</header>
                <button onClick={() => setAssignSubModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                <form onSubmit={handleSubmit(onSubmit)} className="form">
                        <div className="input-box">
                            <label>Assignment Id:</label>
                            <input type="number" {...register("assignmentId")} disabled />
                        </div>
                        <div className="input-box">
                            <label>Trainee Id:</label>
                            <input type="number" {...register("traineeId")} disabled/>
                        </div>
                        <div className="input-box">
                            <label>Assignment submission File:</label>
                            <input type="file" {...register("submissionFile", { required: true })} />
                        </div>
                        <button htmlFor="assignmentSub-modal" type='submit'>Submit</button>
                    </form>
            </div>
        </div>
    </div>
    );
};

export default AssignmentSubModal;
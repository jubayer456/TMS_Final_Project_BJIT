import React from 'react';
// import Trainee from '../Register/Trainee';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { toast } from 'react-hot-toast';

const AssignmentSubModal = ({ assignSubModal, setAssignSubModal }) => {
    const { assignmentId, scheduleId, assignmentName, assignmentFile, deadLine,batchId } = assignSubModal;
    
    const { register, handleSubmit } = useForm({
        defaultValues: {
            assignmentId: assignmentId,
            traineeId: traineeId
        },
    });
    const getCurrentDate = () => {
        const currentDate = new Date();
        const year = currentDate.getFullYear().toString().slice(-2); // Get the last two digits of the year
        const month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Add leading zero if needed
        const day = currentDate.getDate().toString().padStart(2, '0'); // Add leading zero if needed
        return `${year}-${month}-${day}`;
      };

    const onSubmit = data => {
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
                        submissionDate: getCurrentDate()
                    }
                    console.log(assignmentSubData);
                    fetch(`http://localhost:8082/api/schedule/add-assignmentSub`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                            // authorization: `Bearer ${localStorage.getItem('accessToken')}`
                        },
                        body: JSON.stringify(assignmentSubData)
                    })
                        .then(res => {
                            console.log(res);
                            // if (res.status === 401 || res.status === 403) {
                            //     toast.error(`${res.statusText} Access`);
                            //     localStorage.removeItem('accessToken');
                            //     navigate('/login');
                            // }
                            return res.json();
                        })
                        .then(data => {
                            console.log(data);
                            if (data.status == 200) {
                                setAssignSubModal(false);
                                toast.success(`succesfully Assignment Submitted`)
                            }
                            else {
                                setAssignSubModal(false);
                                toast.error(data.msg);
                            }
                        })
                }
                else {
                    setAssignSubModal(false);
                    toast.error(response.data.msg);
                }
            })
            .catch((error) => {
                setAssignSubModal(false)
                console.log(error);
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
                            <input type="text" {...register("traineeId")} disabled/>
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
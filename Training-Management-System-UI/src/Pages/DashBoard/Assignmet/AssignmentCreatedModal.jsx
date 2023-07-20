import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import toast from 'react-hot-toast';
import '../../Shared/Register.css'

const AssignmentCreatedModal = ({ setCreatedModal }) => {
    const navigate = useNavigate();
    const { scheduleId } = useParams();
    const handleSubmit = (e) => {

        e.preventDefault();
        const assignmentData = {
            scheduleId: e.target.scheduleId.value,
            assignmentName: e.target.assignmentName.value,
            assignmentFile: e.target.assignmentFile.value,
            deadLine: e.target.deadLine.value
        }
        console.log(assignmentData);
        fetch(`http://localhost:8082/api/schedule/${scheduleId}/add-assignment`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                // authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(assignmentData)
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
                    setCreatedModal(false);
                    toast.success(`succesfully Assignment Created`)
                }
                else {
                    setCreatedModal(false);
                    toast.error(data.msg);
                }
            })
    }
    return (
        <div>
            <input type="checkbox" id="assignment-created-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl'>Assignment Creation Form</header>
                    <button onClick={() => setCreatedModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit} className="form">
                        <div className="input-box">
                            <label>Schedule Id:</label>
                            <input type="number" name="scheduleId" value={scheduleId} disabled />
                        </div>
                        <div className="input-box">
                            <label>Assignment Name:</label>
                            <input type="text" name="assignmentName" placeholder="Enter Assignment Name" required />
                        </div>
                        <div className="input-box">
                            <label>AssignmentFile:</label>
                            <input type="file" name="assignmentFile" placeholder="Upload Assignment file" required />
                        </div>
                        <div className="input-box">
                            <label>DeadLine Date:</label>
                            <input type="date" name="deadLine" placeholder="Enter DeadLine Date" required />
                        </div>

                        <button htmlFor="assignment-created-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AssignmentCreatedModal;
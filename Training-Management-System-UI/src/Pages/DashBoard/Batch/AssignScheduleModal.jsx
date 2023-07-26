import React from 'react';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import { useParams } from 'react-router-dom';
import '../../Shared/Register.css'
const AssignScheduleModal = ({ setScheduleModal }) => {

    const navigate = useNavigate();
    const { batchId } = useParams();
    const handleSubmit = (e) => {
        e.preventDefault();
        const scheduleData = {
            batchId: e.target.batchId.value,
            startingDate: e.target.startingDate.value,
            endingDate: e.target.endingDate.value,
            courseId: e.target.courseId.value
        }
        console.log(scheduleData);
        fetch('http://localhost:8082/api/batches/add-schedule', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(scheduleData)
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
                if (data.status == 200) {
                    // setScheduleModal(false);
                    toast.success(data.msg);
                }
                else {
                    // setScheduleModal(false);
                    toast.error(data.msg);
                }
            })
    }
    return (
        <div>
            <input type="checkbox" id="schedule-created-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">

                    < header className='text-center text-2xl'>Schedule Creation Form</header>
                    <button onClick={() => setScheduleModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit} className="form">
                        <div className="input-box">
                            <label>Batch Id:</label>
                            <input type="number" name="batchId" value={batchId} disabled />
                        </div>
                        <div className="input-box">
                            <label>Course Id:</label>
                            <input type="number" name="courseId" placeholder="Enter Course Id" required />
                        </div>
                        <div className="input-box">
                            <label>Starting Date:</label>
                            <input type="date" name="startingDate" placeholder="Enter Strating Date" required />
                        </div>
                        <div className="input-box">
                            <label>Ending Date:</label>
                            <input type="date" name="endingDate" placeholder="Enter Ending Date" required />
                        </div>

                        <button htmlFor="schedule-created-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AssignScheduleModal;
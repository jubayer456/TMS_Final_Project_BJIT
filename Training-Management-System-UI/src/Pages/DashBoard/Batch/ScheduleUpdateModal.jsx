import React from 'react';
import { toast } from 'react-hot-toast';

const ScheduleUpdateModal = ({scheduleUpdateModal,setScheduleUpdateModal}) => {

    const handleSubmit=(e)=>{
        e.preventDefault();
        const scheduleData = {
            batchId: e.target.batchId.value,
            startingDate: e.target.startingDate.value,
            endingDate: e.target.endingDate.value,
            courseId: e.target.courseId.value
        }
        fetch(`http://localhost:8082/api/batches/schedules/${scheduleUpdateModal.scheduleId}`, {
            method: 'PUT',
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
                    toast.success(`succesfully Schedule Updated`)
                }
                else {
                    // setScheduleModal(false);
                    toast.error(data.msg);
                }
            })
    }
    return (
        <div>
        <input type="checkbox" id="schedule-update-modal" className="modal-toggle" />
        <div className="modal">
            <div className="modal-box">

                < header className='text-center text-2xl'>Schedule Update Form</header>
                <button onClick={() => setScheduleUpdateModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                <form onSubmit={handleSubmit} className="form">
                    <div className="input-box">
                        <label>Batch Id:</label>
                        <input type="number" name="batchId" value={scheduleUpdateModal.batchId} disabled />
                    </div>
                    <div className="input-box">
                        <label>Course Id:</label>
                        <input type="number" name="courseId" defaultValue={scheduleUpdateModal.courseId} disabled />
                    </div>
                    <div className="input-box">
                        <label>Starting Date:</label>
                        <input type="date" name="startingDate" defaultValue={scheduleUpdateModal.startingDate} required />
                    </div>
                    <div className="input-box">
                        <label>Ending Date:</label>
                        <input type="date" name="endingDate" defaultValue={scheduleUpdateModal.endingDate}  required />
                    </div>

                    <button htmlFor="schedule-update-modal" type='submit'>Submit</button>
                </form>
            </div>
        </div>
    </div>
    );
};

export default ScheduleUpdateModal;
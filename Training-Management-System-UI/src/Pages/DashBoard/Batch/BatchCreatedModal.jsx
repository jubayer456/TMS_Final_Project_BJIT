import React from 'react';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import '../../Shared/Register.css'
const BatchCreatedModal = ({ setCreatedModal }) => {
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const batchData = {
            batchId:e.target.batchId.value,
            batchName: e.target.batchName.value,
            startingDate: e.target.startingDate.value,
            endingDate: e.target.endingDate.value,
            totalNumOfTrainee: e.target.totalNumOfTrainee.value
        }
        console.log(batchData);
        fetch('http://localhost:8082/api/batch-save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(batchData)
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
                    setCreatedModal(false);
                    toast.success(`succesfully course Created`)
                }
                else {
                    setCreatedModal(false);
                    toast.error(data.msg);
                }
            })
    }
    return (
        <div>
            <input type="checkbox" id="batch-created-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl'>Batch Creation Form</header>
                    <button onClick={() => setCreatedModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit} className="form">
                    <div className="input-box">
                            <label>Batch ID:</label>
                            <input type="number" name="batchId" placeholder="Enter Batch Id" required />
                        </div>
                        <div className="input-box">
                            <label>Batch Name:</label>
                            <input type="text" name="batchName" placeholder="Enter Batch Name" required />
                        </div>
                        <div className="input-box">
                            <label>Starting Date:</label>
                            <input type="date" name="startingDate" placeholder="Strating Date" required />
                        </div>
                        <div className="input-box">
                            <label>Ending Date:</label>
                            <input type="date" name="endingDate" placeholder="Ending Date" required />
                        </div>
                        <div className="input-box">
                            <label>Batch Capacity:</label>
                            <input type="number" name="totalNumOfTrainee" placeholder="Batch Capacity" required />
                        </div>
                        <button htmlFor="batch-created-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default BatchCreatedModal;
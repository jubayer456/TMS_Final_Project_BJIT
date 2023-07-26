import React from 'react';
import {  useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import { useParams } from 'react-router-dom';
import '../../Shared/Register.css'
const AssignTraineeModal = ({setTraineeModal}) => {

    const navigate = useNavigate();
    const { batchId } = useParams();
    const handleSubmit = (e) => {
        e.preventDefault();
        const batchTraineeData = {
            batchId: e.target.batchName.value,
            traineeId: e.target.traineeId.value,            
        }
        console.log(batchTraineeData);
         fetch('http://localhost:8082/api/batches/add-trainee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(batchTraineeData)
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
                    // setTraineeModal(false);
                    toast.success(`succesfully Trainee Inserted`)
                }
                else {
                    // setTraineeModal(false);
                    toast.error(data.msg);
                }
            }) 
    }
    return (
         <div>
            <input type="checkbox" id="trainee-assign-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                < header className='text-center text-2xl'>Trainee Assign Form</header>

                    <button onClick={()=>setTraineeModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit} className="form">
                        <div className="input-box">
                            <label>Batch Id:</label>
                            <input type="number" name="batchName" value={batchId} disabled/>
                        </div>
                        <div className="input-box">
                            <label>Trainee Id:</label>
                            <input type="number" name="traineeId"/>
                        </div>
                        <button htmlFor="trainee-assign-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AssignTraineeModal;
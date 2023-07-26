import React from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const Batch = ({batch,index}) => {
    const{batchId,batchName,startingDate,endingDate,totalNumOfTrainee}=batch;
    
    const navigate = useNavigate();
    const   AssignTrainee=(batchId)=>{
        navigate(`/dashboard/batch/${batchId}/trainees`);
    }
    const   AssignSchedule=(batchId)=>{
        navigate(`/dashboard/batch/${batchId}/schedules`);
    }
    const   handleClassRoom=(classRoomId)=>{
        navigate(`/dashboard/classroom/${classRoomId}`);
    }
    return (
        <div className="card bg-base-100 shadow-xl p-4">
        <div className="card-body">
            <h5 className='text-xl font-semibold'>Name: { batchName} </h5>
            <p className='text-base leading-6'>Starting Date: {startingDate }</p>
            <p className='text-base leading-6'>Ending Date: {endingDate }</p>
            <p className='text-base leading-6'>Capacity: {totalNumOfTrainee}</p>
            <div className="">
            <label onClick={() => AssignTrainee(batch.batchId)} className="btn btn-sm bg-blue-500 text-white m-1">Assign Trainee</label>
            <label onClick={() => AssignSchedule(batch.batchId)} className="btn btn-sm bg-green-500 text-white m-1">Assign Schedule</label>
            <label onClick={() => handleClassRoom(batch.batchId)} className="btn btn-sm bg-yellow-500 text-white m-1">ClassRoom</label>
            </div>
        </div>
    </div>
    );
};

export default Batch;
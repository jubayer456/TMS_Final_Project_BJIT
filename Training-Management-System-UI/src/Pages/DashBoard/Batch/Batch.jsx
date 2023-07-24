import React from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const Batch = ({batch,index}) => {
    const{batchId,batchName,startingDate,endingDate,totalNumOfTrainee}=batch;
    
    const navigate = useNavigate();
    const   AssignTrainee=(batchId)=>{
        navigate(`/dashboard/batch/${batchId}/getAllTrainee`);
    }
    const   AssignSchedule=(batchId)=>{
        navigate(`/dashboard/batch/${batchId}/getAllSchedule`);
    }
    const   handleClassRoom=(classRoomId)=>{
        navigate(`/dashboard/classroom/${classRoomId}`);
    }
    return (
        <div className="card w-64 bg-base-100 shadow-xl">
        <div className="card-body">
            <h5 className='text-xl'>Name: { batchName} </h5>
            {/* <h5 className='text-sm'>Batch Id: { batchId} </h5> */}

            <p className='text-sm leading-3'>Starting Date: {startingDate }</p>
            <p className='text-sm leading-3'>Ending Date: {endingDate }</p>
            <p className='text-sm leading-3'>Capacity: {totalNumOfTrainee}</p>
            <p className='text-sm leading-3'>Number Of Trainee: { }</p>
            <div>
            <label onClick={() => AssignTrainee(batch.batchId)} className="btn btn-sm btn-success text-base-100 mt-2 mx-1">Assign Trainee</label>
            <label onClick={() => AssignSchedule(batch.batchId)} className="btn btn-sm btn-success text-base-100 mt-2 mx-1">Assign Schedule</label>
            <label onClick={() => handleClassRoom(batch.batchId)} className="btn btn-sm btn-success text-base-100 mt-2 mx-1">ClassRoom</label>
            </div>
        </div>
    </div>
    );
};

export default Batch;
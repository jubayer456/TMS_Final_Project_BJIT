import React from 'react';
import { useNavigate } from 'react-router-dom';

const Class = ({trainerClass}) => {
    const {batchId,batchName,startingDate,endingDate,totalNumOfTrainee,classRoomName}=trainerClass;
    const navigate = useNavigate();
    const handleClassoom=classId=>{
        navigate(`/dashboard/classroom/${classId}`)
    }
    return (
      <div className="card bg-base-100 shadow-xl p-4">
      <div className="card-body">
          <h5 className='text-xl font-semibold'> { batchName} </h5>
          <p className='text-base leading-6'>Strating Date: {startingDate}</p>
          <p className='text-base leading-6'>Ending  Date: {endingDate}</p>
          <p className='text-base leading-6'>Number oF trainees: {totalNumOfTrainee}</p>
          <div>
            <button onClick={()=>handleClassoom(batchId)} className="btn btn-sm bg-blue-500 text-white">Class Room</button>
          </div>
        </div>
      </div>
    );
};

export default Class;
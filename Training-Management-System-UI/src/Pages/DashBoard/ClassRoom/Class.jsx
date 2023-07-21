import React from 'react';
import { useNavigate } from 'react-router-dom';

const Class = ({trainerClass}) => {
    const {batchId,batchName,startingDate,endingDate,totalNumOfTrainee,classRoomName}=trainerClass;
    const navigate = useNavigate();
    const handleClassoom=classId=>{
        navigate(`/dashboard/classroom/${classId}`)
    }
    return (
        <div className="card w-96 bg-base-100 shadow-xl">
        <div className="card-body">
          <h2 className="card-title">{batchName}</h2>
          <p>Strating Date: {startingDate}</p>
          <p>Ending  Date: {endingDate}</p>
          <p>Number oF trainees: {totalNumOfTrainee}</p>
          <div className="card-actions justify-end">
            <button onClick={()=>handleClassoom(batchId)} className="btn btn-primary">Class Room</button>
          </div>
        </div>
      </div>
    );
};

export default Class;
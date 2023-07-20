import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';


const Schedule = ({schedule,index,setDeletingScheduling,batchName}) => {

    const {scheduleId,batchId,courseId,courseName,startingDate,endingDate,trainerName,profilePicture,trainerId}=schedule;

    const navigate = useNavigate();
    const handelUpdate=id=>{
        navigate(`/dashboard/batch/${batchId}/getAllSchedule`);
    }
    return (
        <tr>
        <td>
          <div className="flex items-center space-x-3">
            <div className="avatar">
              <div className="mask mask-squircle w-12 h-12">
                <img src={`http://localhost:8082/api/download/${profilePicture}`}  alt="Avatar Tailwind CSS Component" />
              </div>
            </div>
            <div>
              <div className="font-bold">{trainerName}</div>
              <div className="text-sm opacity-50">{trainerId}</div>
            </div>
          </div>
        </td>
        <td>{scheduleId}</td>
        <td>{startingDate}</td>
        <td>{endingDate}</td>
        <td>
          {courseName}
          <br/>
          <span className="badge badge-ghost badge-sm">{courseId}</span>
        </td>
        <th>
          <div className=''>
              <label onClick={() => handelUpdate(schedule.scheduleId)} className="btn btn-sm btn-success text-base-100 mx-2">Update</label>
              <label onClick={() => setDeletingScheduling(schedule)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100">Delete</label>
          </div>
        </th>
      </tr>
    );
};

export default Schedule;
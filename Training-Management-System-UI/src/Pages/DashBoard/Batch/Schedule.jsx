import React from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import img from '../../../assets/18942381.png'

const Schedule = ({ schedule, index, setDeletingScheduling, batchName, setScheduleUpdateModal }) => {

  const { scheduleId, batchId, courseId, courseName, startingDate, endingDate, trainerName, profilePicture, trainerId } = schedule;

  const navigate = useNavigate();
  return (
    <tr className='hover:bg-slate-100 font-medium'>
      <td>
        <div className="flex items-center space-x-3">
          <div className="avatar">
            <div className="mask mask-squircle w-12 h-12">
              {
                profilePicture ? <img src={`http://localhost:8082/api/download/${profilePicture}`} alt="Trainee Profile Picture" /> :
                  <img src={img} alt="Trainee default Profile Picture" />
              }
            </div>
          </div>
          <div>
            <div className="font-bold font-bold text-blue-500">{trainerName}</div>
            <div className="text-sm opacity-50">{trainerId}</div>
          </div>
        </div>
      </td>
      <td>{scheduleId}</td>
      <td>{startingDate}</td>
      <td>{endingDate}</td>
      <td>
        {courseName}
        <br />
        <span className="badge badge-ghost badge-sm">{courseId}</span>
      </td>
      <th>
        <div className=''>
          <label onClick={() => setScheduleUpdateModal(schedule)} htmlFor="schedule-update-modal" className="btn btn-sm btn-success text-base-100 mx-2"><FaEdit /></label>
          <label onClick={() => setDeletingScheduling(schedule)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100"><FaTrash /></label>
        </div>
      </th>
    </tr>
  );
};

export default Schedule;
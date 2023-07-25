import React from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import img from '../../../assets/18942381.png'

const Course = ({ course, setDeletingCourse, index, setCourseUpdateModal }) => {
  const { courseId, trainerId, trainerName, name, profilePicture } = course;
  const navigate = useNavigate();
  return (
    <tr className='hover:bg-slate-100 font-medium'>
      <td>
        <div className="flex items-center space-x-3">
          <div className="avatar">
            <div className="mask mask-squircle w-12 h-12">
              {
                profilePicture ? <img src={`http://localhost:8082/api/download/${profilePicture}`} alt="Trainer Profile Picture" /> :
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
      <td>{courseId}</td>
      <td>{name}</td>
      <th>
        <div className=''>
          <label htmlFor="course-update-modal" onClick={() => setCourseUpdateModal(course)} className="btn btn-primary btn-sm mx-1"><FaEdit /></label>
          <label onClick={() => setDeletingCourse(course)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100 mx-1"><FaTrash /></label>
        </div>
      </th>
    </tr>
  );
};

export default Course;
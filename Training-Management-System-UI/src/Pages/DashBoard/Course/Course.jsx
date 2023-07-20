import React from 'react';
import { useNavigate } from 'react-router-dom';

const Course = ({course,setDeletingCourse,index,setCourseUpdateModal}) => {
    const { courseId,trainerId,trainerName, name, profilePicture } = course;
    const navigate = useNavigate();
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
          <td>{courseId}</td>
          <td>{name}</td>
          <th>
            <div className=''>
                <label htmlFor="course-update-modal" onClick={() => setCourseUpdateModal(course)} className="btn btn-primary btn-sm mx-1">update</label>
                <label onClick={() => setDeletingCourse(course)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100 mx-1">Delete</label>
            </div>
          </th>
        </tr>
      );
};

export default Course;
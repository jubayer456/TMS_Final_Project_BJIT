import React from 'react';
import { FaEdit } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';

const Assignment = ({ assignment, setAssignUpdatedModal }) => {
    const { assignmentId, scheduleId, assignmentName, assignmentFile, deadLine } = assignment;
    const navigate = useNavigate();
    const handleDetails=(scheduleId,assignmentId)=>{
        navigate(`/dashboard/schedules/${scheduleId}/${assignmentId}`);
    }
    return (
        <tr className='hover:bg-slate-100 '>
            <td className='font-bold text-blue-500'>{assignmentId}</td>
            <td>{assignmentName}</td>
            <td className='underline italic hover-text-blue-500'>{assignmentFile}</td>
            <td className='text-sm text-gray-700'><span className='text-xs font-bold rounded-lg bg-yellow-200 bg-opacity-500 p-2'>{deadLine}</span></td>
            <th>
                <div className=''>
                    <label htmlFor="assignment-updated-modal" onClick={() => setAssignUpdatedModal(assignment)} className="btn btn-primary btn-sm mx-1 text-white hover-bg-blue-500"><FaEdit /></label>
                    <label onClick={()=>handleDetails(scheduleId,assignmentId)} className="btn btn-sm btn-error text-base-100 mx-1 text-white">Details</label>
                </div>
            </th>
        </tr>
    );
};

export default Assignment;
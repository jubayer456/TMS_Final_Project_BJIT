import React from 'react';
import { useNavigate } from 'react-router-dom';

const Assignment = ({ assignment, setAssignUpdatedModal }) => {
    const { assignmentId, scheduleId, assignmentName, assignmentFile, deadLine } = assignment;
    const navigate = useNavigate();
    const handleDetails=(scheduleId,assignmentId)=>{
        navigate(`/dashboard/schedules/${scheduleId}/${assignmentId}`);
    }
    return (
        <tr>
            <td>{assignmentId}</td>
            <td>{assignmentName}</td>
            <td>{assignmentFile}</td>
            <td>{deadLine}</td>
            <th>
                <div className=''>
                    <label htmlFor="assignment-updated-modal" onClick={() => setAssignUpdatedModal(assignment)} className="btn btn-primary btn-sm mx-1">update</label>
                    <label onClick={()=>handleDetails(scheduleId,assignmentId)} className="btn btn-sm btn-error text-base-100 mx-1">Details</label>
                </div>
            </th>
        </tr>
    );
};

export default Assignment;
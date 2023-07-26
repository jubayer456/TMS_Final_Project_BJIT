import axios from 'axios';
import React from 'react';
import { toast } from 'react-hot-toast';
import {  useNavigate } from 'react-router-dom';

const AssignmentSub = ({ assignment, setAssignSubModal }) => {
    const {assignmentId, scheduleId, assignmentName, assignmentFile, deadLine } = assignment;

    const handleDownload=assignmentFile=>{
        console.log(assignmentFile);
        fetch(`http://localhost:8082/api/download/${assignmentFile}`)
       .then((response) => response.blob())
       .then((blob) => {
        // Create a URL object from the file blob
        const url = window.URL.createObjectURL(blob);
        
        // Create a temporary link element
        const link = document.createElement('a');
        link.href = url;
        link.download =assignmentFile; // Specify the desired file name here
        link.click();
        
        // Cleanup by revoking the object URL
        window.URL.revokeObjectURL(url);
      })
      .catch((error) => {
        console.error('Error downloading the file:', error);
      });
    }
    return (
        <tr>
             <td>{assignmentId}</td>
             <td>{assignmentName}</td>
             <td className='underline italic hover:text-blue-500 cursor-pointer'><label onClick={() => handleDownload(assignmentFile)} >{assignmentFile}</label></td> 
            <td>{deadLine}</td>
            <th>
                <div className=''>
                    <label htmlFor="assignmentSub-modal" onClick={() => setAssignSubModal(assignment)} className="btn btn-primary btn-sm mx-1 text-white">Upload Assignment</label>
                </div>
            </th>
        </tr>
    );
};

export default AssignmentSub;
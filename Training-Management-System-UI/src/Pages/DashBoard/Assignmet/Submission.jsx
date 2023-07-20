import React from 'react';

const Submission = ({submission,index,setAsignEvoModel,setAsignEvoUpdateModel}) => {
    const {profilePicture,fullName,traineeId,assignmentName,assignmentId,asgSubId,submissionFile,submissionDate,evolution}=submission;
    
    const handleDownload=submissionFile=>{
      fetch(`http://localhost:8082/api/download/${submissionFile}`)
     .then((response) => response.blob())
     .then((blob) => {
      // Create a URL object from the file blob
      const url = window.URL.createObjectURL(blob);
      
      // Create a temporary link element
      const link = document.createElement('a');
      link.href = url;
      link.download =submissionFile; // Specify the desired file name here
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
      <td>
        <div className="flex items-center space-x-3">
          <div className="avatar">
            <div className="mask mask-squircle w-12 h-12">
              <img src={`http://localhost:8082/api/download/${profilePicture}`}  alt="Avatar Tailwind CSS Component" />
            </div>
          </div>
          <div>
            <div className="font-bold">{fullName}</div>
            <div className="text-sm opacity-50">{traineeId}</div>
          </div>
        </div>
      </td>
      <td><div>
            <div className="font-bold">{assignmentName}</div>
            <div className="text-sm opacity-50">{assignmentId}</div>
          </div>
      </td>
      <td>{asgSubId}</td>
      <td className='underline italic hover:text-blue-500 cursor-pointer'><label onClick={() => handleDownload(submissionFile)} >{submissionFile}</label></td> 
      <td>{submissionDate}</td>
      <td>
        {evolution != null && <label className='text-sm  font-bold p-2 bg-success rounded'>{evolution}</label>}
        {evolution == null && <label className='text-sm  font-bold p-2 bg-rose-500 rounded'>not seen</label>}
      </td>

      <td><>
      {evolution == null && <label htmlFor="submissionEvo-modal" onClick={() => setAsignEvoModel(submission)} className=" btn btn-sm btn-error text-base-100">give marks</label>}
        {evolution != null && <label htmlFor="submissionEvo-update-modal" onClick={() => setAsignEvoUpdateModel(submission)} className="btn btn-sm btn-error text-base-100">Update</label>}
      </></td>
    </tr>
    ); 
};

export default Submission;
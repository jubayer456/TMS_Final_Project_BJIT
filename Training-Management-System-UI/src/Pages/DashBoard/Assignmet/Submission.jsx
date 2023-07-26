import React from 'react';
import { FaEdit } from 'react-icons/fa';
import img from '../../../assets/18942381.png'

const Submission = ({ submission, index, setAsignEvoModel, setAsignEvoUpdateModel }) => {
  const { profilePicture, fullName, traineeId, assignmentName, assignmentId, asgSubId, submissionFile, submissionDate, evolution } = submission;

  const handleDownload = (fileName) => {
    // Fetch the file using the provided file name
    fetch(`http://localhost:8082/api/download/${fileName}`)
      .then((response) => response.blob())
      .then((blob) => {
        // Create a URL object from the file blob
        const url = window.URL.createObjectURL(blob);

        // Create a temporary link element
        const link = document.createElement('a');
        link.href = url;
        link.download = fileName; // Specify the desired file name here
        link.click();

        // Cleanup by revoking the object URL
        window.URL.revokeObjectURL(url);
      })
      .catch((error) => {
        console.error('Error downloading the file:', error);
      });
  };

  return (
    <tr className='hover:bg-slate-100 font-medium'>
      <td>
        <div className="flex items-center space-x-3">
          <div className="avatar">
            <div className="mask mask-squircle w-12 h-12">
              {
                profilePicture ? <img src={`http://localhost:8082/api/download/${profilePicture}`} alt="Admin Profile Picture" /> :
                  <img src={img} alt="Trainee default Profile Picture" />
              }
            </div>
          </div>
          <div>
            <div className="font-bold text-blue-500">{fullName}</div>
            <div className="text-sm opacity-50">{traineeId}</div>
          </div>
        </div>
      </td>
      <td>
        <div>
          <div className="font-bold text-blue-500">{assignmentName}</div>
          <div className="text-sm opacity-50">{assignmentId}</div>
        </div>
      </td>
      <td>{asgSubId}</td>
      <td className='underline italic hover:text-blue-500 cursor-pointer'>
        <label onClick={() => handleDownload(submissionFile)}>{submissionFile}</label>
      </td>
      <td>
        <span className='text-xs font-bold rounded-lg bg-green-200 bg-opacity-500 p-2'>{submissionDate}</span>
      </td>
      <td>
        {evolution != null && <label className='text-sm font-bold p-2 bg-yellow-200 rounded'>{evolution}</label>}
        {evolution == null && <label className='text-sm font-bold p-2 bg-rose-500 rounded'>not seen</label>}
      </td>
      <td>
        <>
          {evolution == null && <label htmlFor="submissionEvo-modal" onClick={() => setAsignEvoModel(submission)} className="btn btn-sm btn-error text-base-100">give marks</label>}
          {evolution != null && <label htmlFor="submissionEvo-update-modal" onClick={() => setAsignEvoUpdateModel(submission)} className="btn btn-sm btn-primary text-base-100"><FaEdit /></label>}
        </>
      </td>
    </tr>
  );
};

export default Submission;

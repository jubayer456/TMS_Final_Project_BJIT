import React from 'react';

const Trainee = ({ trainee, index, setDeletingTrainee }) => {
  const { traineeId, email, fullName, profilePicture, contactNumber, batchId } = trainee;

  return (
    <tr>
      <td>
        <div className="flex items-center space-x-3">
          <div className="avatar">
            <div className="mask mask-squircle w-12 h-12">
              <img src={profilePicture} alt="Avatar Tailwind CSS Component" />
            </div>
          </div>
          <div>
            <div className="font-bold">{fullName}</div>
            <div className="text-sm opacity-50">{traineeId}</div>
          </div>
        </div>
      </td>
      <td>{email}</td>
      <td>{contactNumber}</td>
      <td><>
        {batchId == null && <button className='btn btn-sm btn-error text-base-100'>No batch</button>}
        {batchId !== null && <button className='btn btn-sm btn-success px-7 text-base-100'>{batchId}</button>}
      </></td>
      <th>
        <label onClick={() => setDeletingTrainee(trainee)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100">Delete</label>
      </th>
    </tr>
  );
};

export default Trainee;
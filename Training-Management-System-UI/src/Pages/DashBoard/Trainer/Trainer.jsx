import React from 'react';

const Trainer = ({ trainer, index, setDeletingTrainer }) => {
    const { trainerId, email, fullName, profilePicture, contactNumber, designation, expertises } = trainer;

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
                        <div className="text-sm opacity-50">{trainerId}</div>
                    </div>
                </div>
            </td>
            <td>{email}</td>
            <td>{contactNumber}</td>
            <td>{designation}</td>
            <td>{expertises}</td>
            <th>
                <label onClick={() => setDeletingTrainer(trainer)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100">Delete</label>
            </th>
        </tr>
    );
};

export default Trainer;
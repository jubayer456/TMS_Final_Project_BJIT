import React from 'react';
import { FaTrash } from 'react-icons/fa';
import img from '../../../assets/18942381.png'

const Trainer = ({ trainer, index, setDeletingTrainer }) => {
    const { trainerId, email, fullName, profilePicture, contactNumber, designation, expertises } = trainer;

    return (
        <tr className='hover:bg-slate-100 font-medium'>
            <td>
                <div className="flex items-center space-x-3">
                    <div className="avatar">
                        <div className="mask mask-squircle w-12 h-12">
                            {/* <img src={profilePicture} alt="Avatar Tailwind CSS Component" /> */}
                            {
                                profilePicture ? <img src={`http://localhost:8082/api/download/${profilePicture}`} alt="Trainee Profile Picture" /> :
                                    <img src={img} alt="Trainee default Profile Picture" />
                            }
                        </div>
                    </div>
                    <div>
                        <div className="font-bold font-bold text-blue-500">{fullName}</div>
                        <div className="text-sm opacity-50 ">{trainerId}</div>
                    </div>
                </div>
            </td>
            <td>{email}</td>
            <td>{contactNumber}</td>
            <td className='text-sm text-gray-700'><span className='text-xs font-bold rounded-lg bg-green-200 bg-opacity-500 p-2'>{designation}</span></td>
            <td>{expertises}</td>
            <th>
                <label onClick={() => setDeletingTrainer(trainer)} htmlFor="confirmation-modal" className="btn btn-sm btn-error text-base-100"> <FaTrash></FaTrash></label>
            </th>
        </tr>
    );
};

export default Trainer;
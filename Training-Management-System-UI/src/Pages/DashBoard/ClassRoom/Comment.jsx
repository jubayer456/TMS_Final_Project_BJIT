import React from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
import img from '../../../assets/18942381.png'
import { useUser } from '../../../Context/UserProvider';

const Comment = ({ comment, onDelete, onUpdate, trainee }) => {
  const { postId, traineeId, profilePicture, traineeName, commentDate, msg } = comment;
  const { state, dispatch } = useUser();
  const { userDetails } = state;
  return (
    <div className="bg-gray-100 rounded-lg p-2 my-3">
      <div className="flex items-center justify-between mb-4">
        {
          profilePicture ? <img src={`http://localhost:8082/api/download/${profilePicture}`} alt="Trainee Profile Picture"
            className="w-16 h-16 rounded-full mr-4" /> :
            <img src={img} alt="Trainee default Profile Picture"
              className="w-16 h-16 rounded-full mr-4" />
        }
        <div className="w-3/4 ">
          <p className="text-gray-600 mb-2">{msg}</p>
          <strong className='mr-2'>{traineeName}</strong> <span className="text-gray-400 text-sm">{commentDate}</span>
        </div>

        <div>
          {
            userDetails?.role != 'ADMIN' && <>
              <button
                className="text-blue-500 mr-2"
                onClick={() => onUpdate(comment)}
                title="Edit"
              >
                <FaEdit />
              </button>
              <button
                className="text-red-500"
                onClick={() => onDelete(comment)}
                title="Delete"
              >
                <FaTrash />
              </button>
            </>}


        </div>

      </div>
    </div>
  );
};

export default Comment;
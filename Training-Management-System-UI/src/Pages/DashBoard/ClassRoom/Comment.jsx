import React from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
const Comment = ({ comment, onDelete, onUpdate, trainee }) => {
  const { postId, traineeId, profilePicture, traineeName, commentDate, msg } = comment;
  return (
    <div className="bg-gray-100 rounded-lg p-2 my-3">
      <div className="flex ">
        <img
          src={`http://localhost:8082/api/download/${profilePicture}`}
          alt="Post"
          className="w-16 h-16 object-cover rounded-full mr-8"
        />
        <div className="w-3/4">
          <p className="text-gray-600 mb-2">{msg}</p>
          <strong className='mr-2'>{traineeName}</strong> <span className="text-gray-400 text-sm">{commentDate}</span>
        </div>
        {
          trainee &&
          <div>
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
          </div>
        }
      </div>
    </div>
  );
};

export default Comment;
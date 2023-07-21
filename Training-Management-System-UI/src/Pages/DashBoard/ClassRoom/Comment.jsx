import React from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
const Comment = ({ comment, onDelete, onUpdate }) => {
  const { text, image, date } = comment;

  return (
    <div className="bg-gray-100 rounded-lg p-2 my-1">
      <div className="flex items-center">
        {image && (
          <img
            src={URL.createObjectURL(image)}
            alt="Comment"
            className="w-10 h-10 object-cover rounded-full mr-2"
          />
        )}
        <p className="text-gray-600">{text}</p>
      </div>
      <div className="flex items-center justify-between mt-2">
        <p className="text-gray-400 text-sm">{date}</p>
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
      </div>
    </div>
  );
};

export default Comment;
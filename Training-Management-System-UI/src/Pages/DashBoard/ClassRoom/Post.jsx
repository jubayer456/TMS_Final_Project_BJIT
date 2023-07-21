import React, { useState } from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
import Comment from './Comment';
import CommentForm from './CommentForm';

const Post = ({ post }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedPost, setEditedPost] = useState(post);
  const [comments, setComments] = useState([]);
  const [showCommentForm, setShowCommentForm] = useState(false);
  const [showComments, setShowComments] = useState(true);

  const addComment = (comment) => {
    setComments([...comments, comment]);
  };

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    // Implement save post functionality here (e.g., update the post in the backend).
    // For this example, we'll just update the state with the edited post.
    setIsEditing(false);
    setEditedPost(post);
  };

  const handleDelete = () => {
    // Implement delete post functionality here (e.g., delete the post in the backend).
    // For this example, we'll just remove the post from the UI.
    // You might need to add a confirmation dialog before deleting.
    // Optionally, you can add a callback to inform the parent component about the delete action.
    // For this example, we'll not include the callback.
    // You can add the callback to the parent component to handle the deletion from the parent's state or backend.
    // For now, we'll just log a message to indicate that the post is deleted.
    console.log('Post deleted:', post.id);
  };

  const handleDeleteComment = (commentToDelete) => {
    const updatedComments = comments.filter(
      (comment) => comment !== commentToDelete
    );
    setComments(updatedComments);
  };

  const handleUpdateComment = (commentToUpdate) => {
    // Implement update comment functionality here (e.g., update the comment in the backend).
    // For this example, we'll just update the comment in the state.
    const updatedComments = comments.map((comment) =>
      comment === commentToUpdate ? { ...comment, isEditing: true } : comment
    );
    setComments(updatedComments);
  };

  const handleSaveComment = (commentToUpdate, updatedText) => {
    // Implement save comment functionality here (e.g., update the comment in the backend).
    // For this example, we'll just update the comment in the state.
    const updatedComments = comments.map((comment) =>
      comment === commentToUpdate
        ? { ...comment, text: updatedText, isEditing: false }
        : comment
    );
    setComments(updatedComments);
  };

  return (
    <div className="bg-white rounded-lg shadow-lg p-6 mb-4">
      <div className="flex items-center justify-between mb-4">
        <img
          src={editedPost.imageUrl}
          alt="Post"
          className="w-16 h-16 object-cover rounded-full mr-4"
        />
        <div className="w-3/4">
          {isEditing ? (
            <div className="mb-4">
              <textarea
                className="w-full p-2 border rounded-lg focus:outline-none focus:ring focus:border-blue-300"
                value={editedPost.content}
                onChange={(e) =>
                  setEditedPost({ ...editedPost, content: e.target.value })
                }
              />
            </div>
          ) : (
            <>
              <p className="text-gray-600 mb-2">{editedPost.content}</p>
              <p className="text-gray-400 text-sm">{editedPost.date}</p>
            </>
          )}
        </div>
        <div className="flex items-center">
          {isEditing ? (
            <>
              <button
                className="text-blue-500 mr-2"
                onClick={handleSave}
                title="Save"
              >
                Save
              </button>
              <button
                className="text-red-500"
                onClick={() => setIsEditing(false)}
                title="Cancel"
              >
                Cancel
              </button>
            </>
          ) : (
            <>
              <button
                className="text-blue-500 mr-2"
                onClick={handleEdit}
                title="Edit"
              >
                <FaEdit />
              </button>
              <button
                className="text-red-500"
                onClick={handleDelete}
                title="Delete"
              >
                <FaTrash />
              </button>
            </>
          )}
        </div>
      </div>
      {showCommentForm && <CommentForm addComment={addComment} />}
      <div className="w-full mb-2">
        <button
                 className="bg-blue-500 text-slate-50 rounded mx-2 px-2"
                onClick={() => setShowCommentForm(!showCommentForm)}
                title="Toggle Comment Form"
              >
                {showCommentForm ? 'cancel' : 'Comment'}
              </button>
        {showComments ? (
                <button
                className="bg-blue-500 text-slate-50 rounded mx-2 px-2"
                  onClick={() => setShowComments(false)}
                  title="Hide Comments"
                >
                  Hide Comments
                </button>
              ) : (
                <button
                className="bg-blue-500 text-slate-50 rounded mx-2 px-2"
                  onClick={() => setShowComments(true)}
                  title="Show Comments"
                >
                  Show Comments
                </button>
              )}
        {showComments &&
          comments.map((comment, index) =>
            comment.isEditing ? (
              <div key={index} className="mb-2">
                <textarea
                  className="w-full p-2 border rounded-lg focus:outline-none focus:ring focus:border-blue-300"
                  value={comment.text}
                  onChange={(e) =>
                    handleSaveComment(comment, e.target.value)
                  }
                />
                <button
                    className="bg-blue-500 text-slate-50 rounded mx-2 px-2"
                  onClick={() => handleSaveComment(comment, comment.text)}
                >
                  Save Comment
                </button>
              </div>
            ) : (
              <Comment
                key={index}
                comment={comment}
                onDelete={() => handleDeleteComment(comment)}
                onUpdate={() => handleUpdateComment(comment)}
              />
            )
          )}
      </div>
    </div>
  );
};

export default Post;
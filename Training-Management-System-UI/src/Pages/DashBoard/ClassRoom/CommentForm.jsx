import React, { useState } from 'react';

const CommentForm = ({ addComment }) => {
  const [commentText, setCommentText] = useState('');
  const [commentImage, setCommentImage] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!commentText.trim() && !commentImage) return; // Prevent adding empty comments

    const newComment = {
      text: commentText,
      image: commentImage,
      date: new Date().toLocaleString(),
    };

    addComment(newComment);

    // Reset form fields after submitting
    setCommentText('');
    setCommentImage(null);
  };

  return (
    <form onSubmit={handleSubmit} className="mt-4">
      <textarea
        rows="3"
        className="w-full p-2 border rounded-lg focus:outline-none focus:ring focus:border-blue-300"
        value={commentText}
        onChange={(e) => setCommentText(e.target.value)}
        placeholder="Add a comment..."
      />
      {/* <input
        type="file"
        className="mt-2"
        onChange={(e) => setCommentImage(e.target.files[0])}
      /> */}
      <br />
      <button type="submit"   className="bg-blue-500 text-slate-50 rounded mx-2 px-2 mb-4">
        Add Comment
      </button>
    </form>
  );
};

export default CommentForm;
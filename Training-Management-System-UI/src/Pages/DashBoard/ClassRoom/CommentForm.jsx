import React, { useState } from 'react';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';

const CommentForm = ({ addComment, refetch, setShowCommentForm, trainee, post }) => {
  const { traineeId, profilePicture, fullName } = trainee;
  const [commentText, setCommentText] = useState('');
  const navigate=useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;

    const commentData = {
      postId:post.postId,
      traineeId:traineeId,
      profilePicture:profilePicture,
      traineeName:fullName,
      commentDate:formattedDate,
      msg: commentText
    }
    console.log(commentData)
    fetch('http://localhost:8082/api/classrooms/add-comment', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
          authorization: `Bearer ${localStorage.getItem('accessToken')}`
      },
      body: JSON.stringify(commentData)
    })
      .then(res => {
          console.log(res);
          if (res.status === 401 || res.status === 403) {
            toast.error(`Access denied please login again`);
              localStorage.removeItem('accessToken');
              localStorage.removeItem('myAppState');
              navigate('/login');
          }
          return res.json();
      })
      .then(data => {
          if (data.status == 200) {
              setShowCommentForm(false);
              refetch();
              toast.success(data.msg);
          }
          else {
              toast.error(data.msg);
          }
      })
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
      <br />
      <button type="submit" className="bg-blue-500 text-slate-50 rounded mx-2 px-2 mb-4">
        Add Comment
      </button>
    </form>
  );
};

export default CommentForm;
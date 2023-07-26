import React, { useState } from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
import Comment from './Comment';
import CommentForm from './CommentForm';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import img from '../../../assets/18942381.png'
import { useUser } from '../../../Context/UserProvider';

const Post = ({ post, trainer, trainee, refetch }) => {
  const { postId, classRoomId, profilePicture, postDate, msg, postFile, comments, trainerName,trainerId } = post;

  const [isEditing, setIsEditing] = useState(false);
  const [editedPost, setEditedPost] = useState(post);
  const [showCommentForm, setShowCommentForm] = useState(false);
  const [showComments, setShowComments] = useState(false);
  const { state, dispatch } = useUser();
  const { userDetails } = state;
  const navigate = useNavigate();

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = post => {
    setIsEditing(false);
    console.log(editedPost);
    const updateData = {
      postId: post.postId,
      classRoomId: post.classRoomId,
      trainerId: trainer.trainerId,
      msg: editedPost.msg,
    };
    console.log(updateData);
    fetch(`http://localhost:8082/api/classrooms/posts/${post.postId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        authorization: `Bearer ${localStorage.getItem('accessToken')}`
      },
      body: JSON.stringify(updateData)
    })
      .then(res => {
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
          refetch();
          toast.success(`succesfully post Created`)
        }
        else {
          toast.error(data.msg);
        }
      })
  };

  const handleDelete = post => {
    fetch(`http://localhost:8082/api/classrooms/posts/${post.postId}/${trainerId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        authorization: `Bearer ${localStorage.getItem('accessToken')}`
      }
    })
      .then(res => {
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
          refetch();
          toast.success(data.msg);
        }
        else {
          toast.error(data.msg);
        }
      })
  };

  const handleDeleteComment = comment => {
    fetch(`http://localhost:8082/api/classrooms/${postId}/${userDetails?.userId}/comments?commentId=${comment.commentId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        authorization: `Bearer ${localStorage.getItem('accessToken')}`
      }
    })
      .then(res => {
        if (res.status === 401 || res.status === 403) {
          toast.error(`Access denied please login again`);
          localStorage.removeItem('accessToken');
          localStorage.removeItem('myAppState');
          navigate('/login');
        }
        return res.json();
      })
      .then(data => {
        console.log(data);
        if (data.status == 200) {
          refetch();
          toast.success(data.msg);
        }
        else {
          toast.error(data.msg);
        }
      })

  };

  const handleUpdateComment = (commentToUpdate) => {
  };

  const handleSaveComment = (commentToUpdate, updatedText) => {
  };

  const handleDownload = postFile => {
    fetch(`http://localhost:8082/api/download/${postFile}`)
      .then((response) => response.blob())
      .then((blob) => {
        // Create a URL object from the file blob
        const url = window.URL.createObjectURL(blob);

        // Create a temporary link element
        const link = document.createElement('a');
        link.href = url;
        link.download = postFile; // Specify the desired file name here
        link.click();

        // Cleanup by revoking the object URL
        window.URL.revokeObjectURL(url);
      })
      .catch((error) => {
        console.error('Error downloading the file:', error);
      });
  }

  return (
    <div className="bg-white rounded-lg shadow-lg p-6 mb-4">
      <div className="flex items-center justify-between mb-4">

        {
          profilePicture ? <img src={`http://localhost:8082/api/download/${profilePicture}`} alt="Trainer Profile Picture"
            className="w-16 h-16 rounded-full mr-4" /> :
            <img src={img} alt="Trainee default Profile Picture"
              className="w-16 h-16 rounded-full mr-4" />
        }
        <div className="w-3/4">
          {isEditing ? (
            <div className="mb-4">
              <textarea
                className="w-full p-2 border rounded-lg focus:outline-none focus:ring focus:border-blue-300"
                defaultValue={msg}
                onChange={(e) =>
                  setEditedPost({ msg, msg: e.target.value })
                }
              />
            </div>
          ) : (
            <>
              <p className="text-gray-600 mb-2">{msg}</p>
              {
                postFile && <p className='underline italic hover:text-blue-500 cursor-pointer' onClick={() => handleDownload(postFile)}>{postFile}</p>
              }
              <strong className='mr-2'>{trainerName}</strong><span className="text-gray-400 text-sm">{postDate}</span>
            </>
          )}
        </div>
        <div className="flex items-center">
          {isEditing ? (
            <>
              <button
                className="text-blue-500 mr-2"
                onClick={() => handleSave(post)}
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
          ) : (trainer &&
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
                onClick={() => handleDelete(post)}
                title="Delete"
              >
                <FaTrash />
              </button>
            </>
          )}
        </div>
      </div>
      {showCommentForm && <CommentForm refetch={refetch} setShowCommentForm={setShowCommentForm} trainee={trainee} post={post} />}
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
            Show Comments ({comments.length})
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
                trainee={trainee}
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
import React, { useState } from 'react';
import './classRoom.css'
const Classroom = () => {
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState('');
    const [attachments, setAttachments] = useState([]);
    const [isTrainee, setIsTrainee] = useState(true); // Set this based on user type
  
    const handleAddComment = () => {
      if (newComment.trim() !== '') {
        setComments([...comments, newComment]);
        setNewComment('');
      }
    };
  
    const handleAttachmentChange = (event) => {
      const files = event.target.files;
      setAttachments([...attachments, ...files]);
    };
  
    const handlePost = () => {
      // Handle posting the content by the trainer
      // You can implement backend integration here
      console.log('Post submitted:', {
        title: 'Classroom Post Title',
        content: 'Classroom Post Content',
        attachments,
      });
    };
  
    return (
      <div className="container">
        <div className="classroom-post">
          <h2>Classroom Post Title</h2>
          <div className="content">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
              ultrices arcu eu odio volutpat, eu mattis arcu fermentum.
            </p>
          </div>
  
          <div className="attachments">
            <h3>Attachments:</h3>
            <ul>
              {attachments.map((file, index) => (
                <li key={index}>{file.name}</li>
              ))}
            </ul>
            <input type="file" onChange={handleAttachmentChange} />
          </div>
  
          {isTrainee ? (
            <div className="comments">
              <h3>Comments:</h3>
              <ul>
                {comments.map((comment, index) => (
                  <li key={index}>{comment}</li>
                ))}
              </ul>
              <textarea
                rows="4"
                value={newComment}
                onChange={(e) => setNewComment(e.target.value)}
                placeholder="Type your comment..."
              />
              <button onClick={handleAddComment}>Add Comment</button>
            </div>
          ) : (
            <div className="post-area">
              <button onClick={handlePost}>Post</button>
            </div>
          )}
        </div>
      </div>
    );
  };

export default Classroom;
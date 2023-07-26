import React from 'react';
import '../../Shared/Register.css'
import {  useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';       


const CreatedCourseModal = ({setCourseModal}) => {
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const courseData = {
            trainerId: e.target.trainerId.value,
            name: e.target.name.value
        }
        console.log(courseData);
         fetch('http://localhost:8082/api/course-save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(courseData)
        })
            .then(res => {
                console.log(res);
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                console.log(data);
                if (data.status == 200) {
                    // setCourseModal(false);
                    toast.success(`succesfully course Created`)
                }
                else {
                    toast.error(data.msg);
                }
            }) 
    }
    return (
        <div>
        <input type="checkbox" id="course-create-modal" className="modal-toggle" />
        <div className="modal">
            <div className="modal-box">
            < header className='text-center text-2xl text-blue-500'>Course Creation Form</header>

            <button onClick={()=>setCourseModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
            <form onSubmit={handleSubmit} className="form">
                        <div className="input-box">
                            <label>Trainer Id</label>
                            <input type="number" name="trainerId" />
                        </div>
                        <div className="input-box">
                            <label>Course Name:</label>
                            <input type="text" name="name" placeholder="Course Name:" required />
                        </div>
                        <button htmlFor="course-create-modal" type='submit'>Submit</button>
                    </form>
            </div>
        </div>
    </div>
    );
};

export default CreatedCourseModal;
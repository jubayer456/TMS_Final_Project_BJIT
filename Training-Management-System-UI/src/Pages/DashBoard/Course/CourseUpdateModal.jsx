import React, { useState } from 'react';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import '../../Shared/Register.css'

const CourseUpdateModal = ({ setCourseUpdateModal, courseUpdateModal }) => {
    const { courseId, trainerId, name } = courseUpdateModal
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const courseData = {
            trainerId: e.target.trainerId.value,
            name: e.target.name.value
        }
        console.log(courseData);
        fetch(`http://localhost:8082/api/courses/${courseId}`, {
            method: 'PUT',
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
                    // navigate('/dashboard/course');
                    setCourseUpdateModal(null);
                    toast.success(`succesfully course Updated`)
                }
                else {
                    setCourseUpdateModal(null);
                    toast.error(data.msg);
                }
            })

    }
    return (
        <div>
            <input type="checkbox" id="course-update-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl text-blue-500'>Course Update Form</header>
                    <button onClick={() => setCourseUpdateModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit} className="form">
                        <div className="input-box">
                            <label>Course Id</label>
                            <input type="number" name="courseId" disabled value={courseId} />
                        </div>
                        <div className="input-box">
                            <label>Trainer Id</label>
                            <input type="number" name="trainerId" defaultValue={trainerId} />
                        </div>
                        <div className="input-box">
                            <label>Course Name:</label>
                            <input type="text" name="name" placeholder="Course Name:" required defaultValue={name} />
                        </div>
                        <button htmlFor="course-update-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default CourseUpdateModal;
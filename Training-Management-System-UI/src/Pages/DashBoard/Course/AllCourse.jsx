import { useState } from "react";
import Loading from "../../Shared/Loading";
import Course from "./Course";
import { useQuery } from 'react-query';
import ConfirmationModal from "../../Shared/ConfirmationModal";
import { toast } from 'react-hot-toast';
import CourseUpdateModal from "./CourseUpdateModal";
const AllCourse = () => {
    const [deletingCourse, setDeletingCourse] = useState(null);
    const [courseUpdateModal, setCourseUpdateModal] = useState(null);

    const closeModal = () => {
        setDeletingCourse(null);
    }

    const { data: courses = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllCourse'],
        queryFn: async () => {
            let url = `http://localhost:8082/api/courses`;
            const headers = {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
            };
            const res = await fetch(url, { headers });
            if (res.status === 401 || res.status === 403) {
                toast.error(`Access denied`);
                localStorage.removeItem('accessToken');
                localStorage.removeItem('myAppState');
                navigate('/login');
            }
            const data = await res.json();
            return data;
        }
    });
    if (isLoading) {
        return <Loading></Loading>
    }
    const handleDeleteCourse = course => {
        fetch(`http://localhost:8082/api/courses/${course.courseId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then(res => {
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
                    refetch();
                    toast.success(`${deletingCourse.name} succesfully Deleted`)
                }
                else {
                    toast.error(data.msg);
                }
            })
    }
    return (
        <div>
        <h1 className='text-3xl pb-5 text-center'>All Courses</h1>
        <div>
            <div className="overflow-x-auto">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>CourseId</th>
                            <th>Course Name</th>
                            <th>More</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        courses.map((course, index) => <Course
                            key={course.courseId}
                            index={index + 1}
                            course={course}
                            setCourseUpdateModal={setCourseUpdateModal}
                            setDeletingCourse={setDeletingCourse}
                        ></Course>)
                    }
                    </tbody>
                </table>
            </div>
            {
                deletingCourse && <ConfirmationModal
                    title={`Are you sure you want to delete?`}
                    message={`If you delete ${deletingCourse.name}. It cannot be undone.`}
                    successAction={handleDeleteCourse}
                    successButtonName="Delete"
                    modalData={deletingCourse}
                    closeModal={closeModal}>
                </ConfirmationModal>
                ||
                courseUpdateModal && <CourseUpdateModal
                setCourseUpdateModal={setCourseUpdateModal}
                courseUpdateModal={courseUpdateModal}
                ></CourseUpdateModal>
            }
        </div>
    </div>
    );
};

export default AllCourse;
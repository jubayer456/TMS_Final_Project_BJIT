import { useState } from "react";
import AllCourse from "./AllCourse";
import { Link } from 'react-router-dom';
import CreatedCourseModal from "./CreatedCourseModal";

const Courses = () => {
    const [courseModal, setCourseModal] = useState(false);
    return (
        <div className="m-2 p-4">
            <div>
                <div className=" ">
                    <label htmlFor="course-create-modal" onClick={() => setCourseModal(true)} className="btn btn-primary btn-sm">Create Course</label>
                </div>
                <div className="divider"></div>
                <><AllCourse />
                {
                    courseModal && <CreatedCourseModal
                    setCourseModal={setCourseModal}
                    ></CreatedCourseModal>
                }
                </>
            </div>
        </div>
    );
};

export default Courses; 
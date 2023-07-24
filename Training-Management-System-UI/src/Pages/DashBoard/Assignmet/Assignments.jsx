import React, { useState } from 'react';
import AllAssignment from './AllAssignment';
import AssignmentCreatedModalTest from './AssignmentCreatedModalTest';


const Assignments = () => {
    const [createdModal, setCreatedModal] = useState(false);
    return (
        <div className="p-4 m-2">
          <div className="bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen">
                <div className=" ">
                    <label htmlFor="assignment-created-modal" onClick={() => setCreatedModal(true)} className="btn btn-primary btn-sm text-white">Create Assignment</label>
                </div>
                <div className="divider"></div>
                <><AllAssignment />
                    {
                        createdModal && < AssignmentCreatedModalTest
                            setCreatedModal={setCreatedModal}
                        ></AssignmentCreatedModalTest>
                    }
                </>
            </div>
        </div>
    );
};

export default Assignments;
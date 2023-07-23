import React, { useState } from 'react';
import AllAssignment from './AllAssignment';
import AssignmentCreatedModalTest from './AssignmentCreatedModalTest';


const Assignments = () => {
    const [createdModal, setCreatedModal] = useState(false);
    return (
        <div className="m-2 p-4">
            <div>
                <div className=" ">
                    <label htmlFor="assignment-created-modal" onClick={() => setCreatedModal(true)} className="btn btn-primary btn-sm">Create Assignment</label>
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
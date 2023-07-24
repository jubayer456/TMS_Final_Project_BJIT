import React, { useState } from 'react';
import AllBatchTrainee from './AllBatchTrainee';
import AssignTraineeModal from './AssignTraineeModal';

const BatchTrainee = () => {
    const [TraineeModal, setTraineeModal] = useState(false);

    return (
        <div className='m-2 p-4'>
            <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
                <div className=" ">
                    <label htmlFor="trainee-assign-modal" onClick={() => setTraineeModal(true)} className="btn btn-primary btn-sm text-white">Assign Trainee</label>
                </div>
                <div className="divider m-2"></div>
                <><AllBatchTrainee />
                    {
                        TraineeModal && <AssignTraineeModal
                            setTraineeModal={setTraineeModal}
                        ></AssignTraineeModal>
                    }
                </>
            </div>
        </div>
    );
};

export default BatchTrainee;
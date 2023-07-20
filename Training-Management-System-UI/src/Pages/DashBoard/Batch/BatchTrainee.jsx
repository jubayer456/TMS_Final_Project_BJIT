import React, { useState } from 'react';
import AllBatchTrainee from './AllBatchTrainee';
import AssignTraineeModal from './AssignTraineeModal';

const BatchTrainee = () => {
    const[TraineeModal,setTraineeModal]=useState(false);

    return (
         <div className='m-2 p-4'>
                <div className=" ">
                    <label htmlFor="trainee-assign-modal" onClick={()=>setTraineeModal(true)} className="btn btn-primary btn-sm">Assign Trainee</label>
                </div>
                <div className="divider"></div>
                <><AllBatchTrainee/>
                {
                    TraineeModal && <AssignTraineeModal
                    setTraineeModal={setTraineeModal}
                    ></AssignTraineeModal>
                }
                </>
            </div>
    );
};

export default BatchTrainee;
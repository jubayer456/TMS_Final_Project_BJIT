import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import AllBatch from './AllBatch';
import BatchCreatedModal from './BatchCreatedModal';

const Batchs = () => {
    const [createdModal, setCreatedModal] = useState(false);

    return (
        <div className="m-2 p-4">
            <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
                <div className=" ">
                    <label htmlFor="batch-created-modal" onClick={() => setCreatedModal(true)} className="btn btn-primary btn-sm text-white">Create Batch</label>
                </div>
                <div className="divider m-2"></div>
                <><AllBatch />
                    {
                        createdModal && <BatchCreatedModal
                            setCreatedModal={setCreatedModal}
                        ></BatchCreatedModal>
                    }
                </>
            </div>
        </div>
    );
};

export default Batchs;
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import AllBatchSchedule from './AllBatchSchedule';
import AssignScheduleModal from './AssignScheduleModal';
const BatchSchedule = () => {
    const[scheduleModal,setScheduleModal]=useState(false);
    
    return (
        <div className='m-2 p-4'>
        <div >
            <label htmlFor="schedule-created-modal" onClick={()=>setScheduleModal(true)} className="btn btn-primary btn-sm">Create Schedule</label>
            
        </div>
        <div className="divider"></div>
        <><AllBatchSchedule/>
        {
            scheduleModal && <AssignScheduleModal
            setScheduleModal={setScheduleModal}
            ></AssignScheduleModal>
        }
            
        </>
    </div>
    );
};

export default BatchSchedule;
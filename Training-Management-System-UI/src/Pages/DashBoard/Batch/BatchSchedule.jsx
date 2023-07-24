import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import AllBatchSchedule from './AllBatchSchedule';
import AssignScheduleModal from './AssignScheduleModal';
import ScheduleUpdateModal from './ScheduleUpdateModal';
const BatchSchedule = () => {
    const[scheduleModal,setScheduleModal]=useState(false);
    
    return (
        <div className='m-2 p-4'>
        <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
        <div >
            <label htmlFor="schedule-created-modal" onClick={()=>setScheduleModal(true)} className="btn btn-primary btn-sm text-white">Create Schedule</label>
            
        </div>
        <div className="divider m-2"></div>
        <><AllBatchSchedule/>
        {
            scheduleModal && <AssignScheduleModal
            setScheduleModal={setScheduleModal}
            ></AssignScheduleModal>
        }
            
        </>
    </div>
       </div>
    );
};

export default BatchSchedule;
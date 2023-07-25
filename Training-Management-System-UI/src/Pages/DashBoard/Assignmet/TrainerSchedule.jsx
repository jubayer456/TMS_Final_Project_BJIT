import { useNavigate } from 'react-router-dom';

const TrainerSchedule = ({ trainerSchedule, index }) => {
    const { courseId, scheduleId, startingDate, endingDate, trainerId } = trainerSchedule

    const navigate = useNavigate();
    const AssignmentHandler = scheduleId => {
        navigate(`/dashboard/schedules/${scheduleId}/getAllAssignment`)
    }
    return (
        <div className="card bg-base-100 shadow-xl p-4">
        <div className="card-body">
                <h5 className='text-xl font-semibold'>Course Id: { courseId} </h5>
                <h3 className='text-base leading-6'>Schedule Id: {scheduleId}</h3>
                <h3 className='text-base leading-6'>Starting Date: {startingDate}</h3>
                <h3 className='text-base leading-6'>Ending Date: {endingDate}</h3>
                <h3 className='text-base leading-6'>Trainer Id: {trainerId}</h3>

                <label onClick={() => AssignmentHandler(scheduleId)} className="btn btn-sm bg-blue-500 text-white hover:bg-blue-700">Assignment Details</label>
            </div>
        </div>
    );
};

export default TrainerSchedule;
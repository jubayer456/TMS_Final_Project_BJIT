import { useNavigate } from 'react-router-dom';

const TrainerSchedule = ({ trainerSchedule, index }) => {
    const { courseId, scheduleId, startingDate, endingDate, trainerId } = trainerSchedule

    const navigate = useNavigate();
    const AssignmentHandler = scheduleId => {
        navigate(`/dashboard/schedules/${scheduleId}/getAllAssignment`)
    }
    return (
        <div className="card w-96 bg-base-100 shadow-xl">
            <div className="card-body">
                <h3 className='text-2xl'>Course Id: {courseId}</h3>
                <h3 className='text-lg'>Schedule Id: {scheduleId}</h3>
                <h3 className='text-lg'>Starting Date: {startingDate}</h3>
                <h3 className='text-lg'>Ending Date: {endingDate}</h3>
                <h3 className='text-lg'>Trainer Id: {trainerId}</h3>
                <label onClick={() => AssignmentHandler(scheduleId)} className="btn btn-sm btn-success text-base-100 mt-4 mx-1">Assignment Details</label>
            </div>
        </div>
    );
};

export default TrainerSchedule;
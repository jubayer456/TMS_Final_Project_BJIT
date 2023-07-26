import { useQuery } from 'react-query';
import Loading from "../../Shared/Loading";
import ConfirmationModal from "../../Shared/ConfirmationModal";
import { toast } from 'react-hot-toast';
import Schedule from './Schedule';
import { useNavigate, useParams } from 'react-router-dom';
import { useState } from 'react';
import ScheduleUpdateModal from './ScheduleUpdateModal';

const AllBatchSchedule = () => {
    const { batchId } = useParams();
    const [deletingSchedule, setDeletingScheduling] = useState(null);
    const[scheduleUpdateModal,setScheduleUpdateModal]=useState(false);
    const navigate=useNavigate();
    const closeModal = () => {
        setDeletingScheduling(null);
    }

    const { data: schedules = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllSchedule'],
        queryFn: async () => {
            const url =`http://localhost:8082/api/batches/${batchId}/schedules`;

            const headers = {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
            };
            const res = await fetch(url, { headers });
            if (res.status === 401 || res.status === 403) {
                toast.error(`Access denied please login again`);
                localStorage.removeItem('accessToken');
                localStorage.removeItem('myAppState');
                navigate('/login');
            }
            const data = await res.json();
            return data;
        }
    });
    if (isLoading) {
        return <Loading></Loading>
    }
    const handleDeleteSchedule = schedule => {
        fetch(`http://localhost:8082/api/batches/schedules/${schedule.scheduleId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then(res => {
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied please login again`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                console.log(data);
                if (data.status == 200) {
                    refetch();
                    toast.success(`${deletingSchedule.scheduleId} succesfully Deleted`)
                }
                else {
                    toast.error(data.msg);
                }
            })
    }

    return (
        <div className='m-2'>
        <h1 className='text-3xl pb-3 text-center mb-3'>All Schedules</h1>
        <div>
            <div className="overflow-x-auto">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Trainer Details</th>
                            <th>Schedule Id</th>
                            <th>Starting Date</th>
                            <th>Ending Date</th>
                            <th>Course Details</th>
                            <th>More</th>
                        </tr>
                    </thead>
                    <tbody>
                    {   
                        schedules?.map((schedule, index) => <Schedule
                            key={schedule.scheduleId}
                            index={index + 1}
                            schedule={schedule}
                            setDeletingScheduling={setDeletingScheduling}
                            setScheduleUpdateModal={setScheduleUpdateModal}
                        ></Schedule>)
                    }
                    </tbody>
                </table>
            </div>
            {
                deletingSchedule && <ConfirmationModal
                    title={`Are you sure you want to delete?`}
                    message={`If you delete ${deletingSchedule.scheduleId}. It cannot be undone.`}
                    successAction={handleDeleteSchedule}
                    successButtonName="Delete"
                    modalData={deletingSchedule}
                    closeModal={closeModal}>
                </ConfirmationModal>||

                scheduleUpdateModal && <ScheduleUpdateModal
                setScheduleUpdateModal={setScheduleUpdateModal}
                scheduleUpdateModal={scheduleUpdateModal}
                ></ScheduleUpdateModal>
            }
        </div>
    </div>
    );
};

export default AllBatchSchedule;
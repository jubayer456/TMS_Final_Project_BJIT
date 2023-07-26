import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import Submission from './Submission';
import SubmissionEvoModal from './SubmissionEvoModal';
import { useQuery } from 'react-query';
// import AssignmentSubModal from '../AssignmentSub/AssignmentSubModal';
import SubmissionEvoUpdateModal from './SubmissionEvoUpdateModal';
// import Test from '../AssignmentSub/Test';

const ShowAllAssignmentSubmission = () => {
    const [asignEvoModel, setAsignEvoModel] = useState(null);
    const [asignEvoUpdateModel, setAsignEvoUpdateModel] = useState(null);

    const { scheduleId } = useParams();
    const { assignmentId } = useParams();
    const { data: submissions = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllAssignmentSubmission'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/schedules/${scheduleId}/${assignmentId}`;

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
    return (
        <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
            <h1 className='text-3xl mb-5 text-center'>All submission List </h1>
            <div>
                <div className="overflow-x-auto">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Asign Details</th>
                                <th>subId</th>
                                <th>SubFile</th>
                                <th>Date</th>
                                <th>Evolution</th>
                                <th>More</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                submissions.map((submission, index) => <Submission
                                    key={submission.asgSubId}
                                    index={index + 1}
                                    refetch={refetch}
                                    submission={submission}
                                    setAsignEvoModel={setAsignEvoModel}
                                    setAsignEvoUpdateModel={setAsignEvoUpdateModel}
                                ></Submission>)
                            }
                        </tbody>
                    </table>
                </div>
                {
                    asignEvoModel && <SubmissionEvoModal
                        asignEvoModel={asignEvoModel}
                        setAsignEvoModel={setAsignEvoModel}
                        refetch={refetch}
                    ></SubmissionEvoModal> 
                    ||
                    asignEvoUpdateModel && <SubmissionEvoUpdateModal
                        asignEvoUpdateModel={asignEvoUpdateModel}
                        setAsignEvoUpdateModel={setAsignEvoUpdateModel}
                        refetch={refetch}
                    ></SubmissionEvoUpdateModal>
                }
            </div>
        </div>

    );
};

export default ShowAllAssignmentSubmission;
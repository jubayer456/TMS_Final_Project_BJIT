import React, { useState } from 'react';
import Loading from '../../Shared/Loading';
import { useQuery } from 'react-query';
import { useParams } from 'react-router-dom';
import AssignmentUpdateModal from './AssignmentUpdateModal';
import Assignment from './Assignment';

const AllAssignment = () => {
    const [assignUpdateModal, setAssignUpdatedModal] = useState(null);

    const { scheduleId } = useParams();
    const { data: assignments = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllAssignment'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/schedules/${scheduleId}/assignments`;

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
        <div >
            <h1 className='text-3xl pb-3 text-center'>All Assignment</h1>
            <div>
                <div className="overflow-x-auto">
                    <table className="table ">
                        <thead>
                            <tr>
                                <th>AssignId</th>
                                <th>Name</th>
                                <th>File</th>
                                <th>DeadLine</th>
                                <th>More</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                assignments.map((assignment, index) => <Assignment
                                    key={assignment.assignmentId}
                                    index={index + 1}
                                    assignment={assignment}
                                    setAssignUpdatedModal={setAssignUpdatedModal}
                                ></Assignment>)
                            }
                        </tbody>
                    </table>
                </div>
                {
                    assignUpdateModal && <AssignmentUpdateModal
                        setAssignUpdatedModal={setAssignUpdatedModal}
                        assignUpdateModal={assignUpdateModal}
                    ></AssignmentUpdateModal>
                }
            </div>
        </div>
    );
};

export default AllAssignment;
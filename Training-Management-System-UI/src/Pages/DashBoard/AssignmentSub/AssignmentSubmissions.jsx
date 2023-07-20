import React, { useEffect, useState } from 'react';
import { useQuery } from 'react-query';
import { json, useParams } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import AssignmentSub from './AssignmentSub';
import AssignmentSubModal from './AssignmentSubModal';

const AssignmentSubmissions = () => {
    const [assignSubModal, setAssignSubModal] = useState(null);
    const [user, setUser] = useState({});

    useEffect(()=>{
        fetch(`http://localhost:8082/api/trainee/${userId}`)
        .then(res=>res.json())
        .then(data=>setUser(data))
    },[user]);
    const { data: assignmentSub = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllAssignmentSub'],
        queryFn: async () => {
            const res = await fetch(`http://localhost:8082/api/schedule/${user?.batchId}/allAssignmentSub`);
            const data = await res.json();
            return data
        }
    });
    if (isLoading) {
        return <Loading></Loading>
    }

    return (
        <div className='m-2 p-4'>
            <h1 className='text-3xl pb-3 text-center'>Assignment Submission Page</h1>
            <div>
                <div className="overflow-x-auto">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Assignment Id</th>
                                <th>Assignment Name</th>
                                <th>Assignment File</th>
                                <th>DeadLine</th>
                                <th>Upload Assignment</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                assignmentSub.map((assignment, index) => <AssignmentSub
                                    key={assignment.assignmentId}
                                    index={index + 1}
                                    assignment={assignment}
                                    setAssignSubModal={setAssignSubModal}
                                ></AssignmentSub>)
                            }
                        </tbody>
                    </table>
                </div>
                {
                    assignSubModal && <AssignmentSubModal
                        setAssignSubModal={setAssignSubModal}
                        assignSubModal={assignSubModal}
                    ></AssignmentSubModal>
                }
            </div>
        </div>
    );
};

export default AssignmentSubmissions;
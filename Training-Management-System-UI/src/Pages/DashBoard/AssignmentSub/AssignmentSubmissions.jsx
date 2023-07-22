import React, { useEffect, useState } from 'react';
import { useQuery } from 'react-query';
import Loading from '../../Shared/Loading';
import AssignmentSub from './AssignmentSub';
import AssignmentSubModal from './AssignmentSubModal';
import { useUser } from '../../../Context/UserProvider';

const AssignmentSubmissions = () => {
    const [assignSubModal, setAssignSubModal] = useState(null);
    const [user, setUser] = useState({});
    const { state, dispatch } = useUser();
    const {userDetails}=state;
    console.log(userDetails);
    console.log(user);

    useEffect(() => {
        if (userDetails?.userId) {
          fetch(`http://localhost:8082/api/trainee/${userDetails.userId}`)
            .then((res) => res.json())
            .then((data) => setUser(data));
        }
      }, [userDetails?.userId]);
    
      // Conditionally call useQuery only when the user object is available
      const { data: assignmentSub = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllAssignmentSub'],
        queryFn: async () => {
          if (user?.batchId) {
            const res = await fetch(`http://localhost:8082/api/schedule/${user.batchId}/allAssignmentSub`);
            const data = await res.json();
            console.log(data);
            return data;
          }
          return []; // Return an empty array if the user object doesn't have a batchId yet
        },
        enabled: !!user.batchId, // Enable the query only if user.batchId is available
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
                        traineeId={userDetails?.userId}
                    ></AssignmentSubModal>
                }
            </div>
        </div>
    );
};

export default AssignmentSubmissions;
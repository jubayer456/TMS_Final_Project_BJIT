import React, { useEffect, useState } from 'react';
import { useQuery } from 'react-query';
import Loading from '../../Shared/Loading';
import AssignmentSub from './AssignmentSub';
import AssignmentSubModal from './AssignmentSubModal';
import { useUser } from '../../../Context/UserProvider';
import { useNavigate } from 'react-router-dom';

const AssignmentSubmissions = () => {
    const [assignSubModal, setAssignSubModal] = useState(null);
    const navigate=useNavigate();
    const [user, setUser] = useState('');
    const { state, dispatch } = useUser();
    const {userDetails}=state;
    // console.log(user);
    useEffect(() => {
        if (userDetails?.userId) {
          fetch(`http://localhost:8082/api/trainees/${userDetails?.userId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then((res) =>{ 
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied please login again`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
               return res.json();})
            .then((data) => {
                console.log(data);
                setUser(data)});
        }
      }, [userDetails?.userId]);
    
      // Conditionally call useQuery only when the user object is available
      const { data: assignmentSub = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllAssignmentSub'],
        queryFn: async () => {
          if (user?.batchId) {
            const url = `http://localhost:8082/api/schedules/${user?.batchId}/assignmentSubmissions`;

            const headers = {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
            };
            const res = await fetch(url, { headers });
            if (res.status === 401 || res.status === 403) {
                toast.error(`Access denied`);
                localStorage.removeItem('accessToken');
                localStorage.removeItem('myAppState');
                navigate('/login');
            }
            const data = await res.json();
            return data;
          }
      }});
    if (isLoading) {
        return <Loading></Loading>
    }

    return (
        <div className='p-4 m-2'>
                <div className='bg-white rounded-lg shadow-lg p-6 mb-4  min-h-screen'>
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
        </div>
       
    );
};

export default AssignmentSubmissions;
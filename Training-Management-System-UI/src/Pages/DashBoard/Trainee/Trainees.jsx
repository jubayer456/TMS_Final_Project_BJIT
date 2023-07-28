import React, { useState } from 'react';
import CreatedTraineeModal from './CreatedTraineeModal';
import Trainee from './Trainee';
import Loading from '../../Shared/Loading';
import { useQuery } from 'react-query';
import ConfirmationModal from '../../Shared/ConfirmationModal';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';

const Trainees = () => {
    const [traineeModal, setTraineeModal] = useState(false);
    const [deletingTrainee, setDeletingTrainee] = useState(null);
    const navigate=useNavigate();

    const closeModal = () => {
        setDeletingTrainee(null);
    }

    const { data: trainee = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllTrainee'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/trainees`;

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

    const handleDeleteTrainee = trainee => {
        fetch(`http://localhost:8082/api/trainees/${trainee.traineeId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then(res => {
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                if (data.status == 200) {
                    refetch();
                    toast.success(`${trainee.fullName} succesfully Deleted`)
                }
                else {
                    toast.error(`${trainee.fullName} did not Deleted`)
                }
            })
    }
    return (
        <div className="m-2 p-4">
            <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
                <div className=" ">
                    <label htmlFor="trainee-create-modal" onClick={() => setTraineeModal(true)} className="btn btn-primary btn-sm text-white">Create Trainee</label>
                </div>
                <div className="divider m-1"></div>
                <div>
                    <h1 className='text-3xl mb-5 text-center'>All Trainees</h1>
                    <div className="overflow-x-auto">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>email</th>
                                    <th>contact Num</th>
                                    <th>Batch Id</th>
                                    <th>More</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    trainee.map((trainee, index) => <Trainee
                                        key={trainee.traineeId}
                                        index={index + 1}
                                        trainee={trainee}
                                        setDeletingTrainee={setDeletingTrainee}
                                    ></Trainee>)
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
                {
                    deletingTrainee && <ConfirmationModal
                        title={`Are you sure you want to delete?`}
                        message={`If you delete ${deletingTrainee.name}. It cannot be undone.`}
                        successAction={handleDeleteTrainee}
                        successButtonName="Delete"
                        modalData={deletingTrainee}
                        closeModal={closeModal}>
                    </ConfirmationModal>
                    ||
                    traineeModal && <CreatedTraineeModal
                        setTraineeModal={setTraineeModal}
                    ></CreatedTraineeModal>
                }

            </div>
        </div>
    );
};

export default Trainees;
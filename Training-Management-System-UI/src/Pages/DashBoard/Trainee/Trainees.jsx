import React, { useState } from 'react';
import CreatedTraineeModal from './CreatedTraineeModal';
import Trainee from './Trainee';
import Loading from '../../Shared/Loading';
import { useQuery } from 'react-query';
import ConfirmationModal from '../../Shared/ConfirmationModal';

const Trainees = () => {
    const [traineeModal, setTraineeModal] = useState(false);
    const [deletingTrainee, setDeletingTrainee] = useState(null);

    const closeModal = () => {
        setDeletingTrainee(null);
    }

    const { data: trainee = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllTrainee'],
        queryFn: async () => {
            const res = await fetch(`http://localhost:8082/api/trainee/getAll`);
            const data = await res.json();
            return data
        }
    });
    if (isLoading) {
        return <Loading></Loading>
    }
    const handleDeleteTrainee = trainee => {
        fetch(`http://localhost:8082/api/trainee/${trainee.traineeId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
                // authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then(res => {
                // if (res.status === 401 || res.status === 403) {
                //     toast.error(`${res.statusText} Access`);
                //     localStorage.removeItem('accessToken');
                //     navigate('/login');
                // }
                return res.json();
            })
            .then(data => {
                console.log(data);
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
            <div>
                <div className=" ">
                    <label htmlFor="trainee-create-modal" onClick={() => setTraineeModal(true)} className="btn btn-primary btn-sm">Create Trainee</label>
                </div>
                <div className="divider"></div>
                <div>
                <h1 className='text-3xl py-5 text-center'>All Trainees</h1>
                <div className="overflow-x-auto">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>email</th>
                                <th>contact Num</th>
                                <th>Batch Name</th>
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
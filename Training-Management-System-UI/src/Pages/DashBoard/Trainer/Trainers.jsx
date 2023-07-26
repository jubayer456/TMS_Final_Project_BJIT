import React, { useState } from 'react';
import CreateTraierModal from './CreateTraierModal';
import ConfirmationModal from '../../Shared/ConfirmationModal';
import Loading from '../../Shared/Loading';
import { useQuery } from 'react-query';
import Trainer from './Trainer'
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';

const Trainers = () => {
    const navigate=useNavigate();
    const [trainerModal, setTrainerModal] = useState(false);
    const [deletingTrainer, setDeletingTrainer] = useState(null);

    const closeModal = () => {
        setDeletingTrainer(null);
    }

    const { data: trainers = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllTrainer'],
        queryFn: async () => {
            let url = `http://localhost:8082/api/trainers`;
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
    });
    if (isLoading) {
        return <Loading></Loading>
    }

    const handleDeleteTrainee = trainer => {
        fetch(`http://localhost:8082/api/trainers/${trainer.trainerId}`, {
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
                    toast.success(`${deletingTrainer.fullName} succesfully Deleted`)
                }
                else {
                    toast.error(data.msg)
                }
            })
    }
    return (
        <div className="m-2 p-4">
             <div className='bg-white rounded-lg shadow-lg p-6 mb-4 min-h-screen'>
                <div className=" ">
                    <label htmlFor="trainer-create-modal" onClick={() => setTrainerModal(true)} className="btn btn-primary btn-sm text-white">Create Trainer</label>
                </div>
                <div className="divider m-2"></div>
                <h1 className='text-3xl mb-5 text-center'>All Trainers</h1>
                <div>
                    <div className="overflow-x-auto">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>email</th>
                                    <th>contact Num</th>
                                    <th>Designation</th>
                                    <th>Expertise</th>
                                    <th>More</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                trainers.map((trainer, index) => <Trainer
                                    key={trainer.trainerId}
                                    index={index + 1}
                                    trainer={trainer}
                                    setDeletingTrainer={setDeletingTrainer}
                                ></Trainer>)
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
                {
                    deletingTrainer && <ConfirmationModal
                        title={`Are you sure you want to delete?`}
                        message={`If you delete ${deletingTrainer.name}. It cannot be undone.`}
                        successAction={handleDeleteTrainee}
                        successButtonName="Delete"
                        modalData={deletingTrainer}
                        closeModal={closeModal}>
                    </ConfirmationModal>
                }
                {
                    trainerModal && <CreateTraierModal
                        refetch={refetch}
                        setTrainerModal={setTrainerModal}
                    ></CreateTraierModal>
                }
                {/* </> */}
            </div>
        </div>
    );
};

export default Trainers;
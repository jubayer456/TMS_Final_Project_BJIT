import React, { useState } from 'react';
import { useQuery } from 'react-query';
import ConfirmationModal from '../../Shared/ConfirmationModal';
import { toast } from 'react-hot-toast';
import { useParams } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import Trainee from '../Trainee/Trainee';

const AllBatchTrainee = () => {
    const [removeTrainee, setRemoveTrainee] = useState(null);
    const { batchId } = useParams();
    const closeModal = () => {
        setRemoveTrainee(null);
    }

    const { data: trainees = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllBatchTrainee'],

        queryFn: async () => {
            const url = `http://localhost:8082/api/batches/${batchId}/trainees`;

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
    const handleRemoveTrainee = trainee => {
        fetch(`http://localhost:8082/api/batches/trainees/${trainee.traineeId}`, {
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
                    toast.success(`${removeTrainee.fullName} succesfully Removed`)
                }
                else {
                    toast.error(`${removeTrainee.fullName} did not Removed`)
                }
            })
    }
    return (
        <div className='m-2'>
        <h1 className='text-3xl pb-5 text-center'>All Batch Trainees</h1>
        <div>
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
                        trainees.map((trainee, index) => <Trainee
                            key={trainee.traineeId}
                            index={index + 1}
                            trainee={trainee}
                            setDeletingTrainee={setRemoveTrainee}
                        ></Trainee>)
                    }
                    </tbody>
                </table>
            </div>
            {
                removeTrainee && <ConfirmationModal
                    title={`Are you sure you want to Remove?`}
                    message={`If you Remove ${removeTrainee.fullName} from the batch of ${removeTrainee.batchId}. It cannot be undone.`}
                    successAction={handleRemoveTrainee}
                    successButtonName="Remove"
                    modalData={removeTrainee}
                    closeModal={closeModal}>
                </ConfirmationModal>
            }
        </div>
    </div>
    );
};

export default AllBatchTrainee;
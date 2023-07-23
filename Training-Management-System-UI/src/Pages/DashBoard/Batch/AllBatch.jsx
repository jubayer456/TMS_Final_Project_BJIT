import React from 'react';
import {  useNavigate } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import Batch from './Batch';
import { useQuery } from 'react-query';

const AllBatch = () => {
    const navigate=useNavigate();
    const { data: batchs = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllBatchs'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/batch/getAll`;

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
        <div className=''>
            <h1 className='text-2xl text-center mb-5'>Batch Details!!!</h1>
            <div className='grid md:grid-cols-2 grid-col-1 gap-10 justify-items-center'>
                {
                    batchs.map((batch, index) => <Batch
                    key={batch.batchId}
                    index={index + 1}
                    batch={batch}
                ></Batch>)
                }
            </div>
        </div>
    );
};

export default AllBatch;
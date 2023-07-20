import React from 'react';
import { Link } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import Batch from './Batch';
import { useQuery } from 'react-query';

const AllBatch = () => {
    const { data: batchs = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllBatchs'],
        queryFn: async () => {
            const res = await fetch(`http://localhost:8082/api/batch/getAll`);
            const data = await res.json();
            return data
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
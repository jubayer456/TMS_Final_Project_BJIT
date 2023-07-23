import React, { useState } from 'react';
import { useQuery } from 'react-query';
import { Link, useNavigate } from 'react-router-dom';
import Loading from '../../Shared/Loading';
import TrainerSchedule from './TrainerSchedule';
import { useUser } from '../../../Context/UserProvider';

const TrainerSchedules = () => {
    const { state, dispatch } = useUser();
    const {userDetails}=state;
    const navigate=useNavigate();
    const { data: trainerSchedules = [], refetch, isLoading } = useQuery({
        queryKey: ['getTrainerAllSchedules'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/schedule/${userDetails?.userId}`;

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
        <div className='m-2 p-4'>
            <div className='grid md:grid-cols-2 grid-col-1 gap-10 justify-items-center'>
                {
                    trainerSchedules.map((schedule, index) => < TrainerSchedule
                        key={schedule.scheduleId}
                        index={index + 1}
                        trainerSchedule={schedule}
                    ></TrainerSchedule>)
                }
            </div>
        </div>
    );
};

export default TrainerSchedules;
import React from 'react';
import { useUser } from '../../../Context/UserProvider';
import { useQuery } from 'react-query';
import Class from './Class';

const TrainerClassRoom = () => {

    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const { data: classes = [], refetch, isLoading } = useQuery({
        queryKey: ['getAllTrainerClass'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/classroom/${userDetails?.userId}/getAllClassRoom`;

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
    return (
        <div>
            <h1 className='text-2xl text-center mb-5'>Your Classes!!!</h1>
            <div className='grid md:grid-cols-2 grid-col-1 gap-10 justify-items-center'>
                {
                    classes?.map((trainerClass, index) => <Class
                        key={trainerClass.classId}
                        trainerClass={trainerClass}
                    ></Class>)
                }
            </div>
        </div>
    );
};

export default TrainerClassRoom;
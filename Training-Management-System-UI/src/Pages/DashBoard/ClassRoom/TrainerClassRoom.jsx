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
            const res = await fetch(`http://localhost:8082/api/classroom/${userDetails?.userId}/getAllClassRoom`);
            const data = await res.json();
            console.log(data);
            return data
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
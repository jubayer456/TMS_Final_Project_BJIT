import React from 'react';
import { useUser } from '../../../Context/UserProvider';
import { useForm } from 'react-hook-form';
import { toast } from 'react-hot-toast';
import { useParams } from 'react-router-dom';

const NoticeCreateModal = ({setNoticeCreatdModal,refetch}) => {
    const {classRoomId}=useParams();
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const { register, handleSubmit } = useForm({
        defaultValues: {
            trainerId: userDetails?.userId,
        },
    });
    const onSubmit = data => {
        const noticeData={
            trainerId :data.trainerId,
            title :data.title,
            description :data.description,
            classRoomId :classRoomId
        }
        fetch(`http://localhost:8082/api/classrooms/add-notice`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(noticeData)
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
                    setNoticeCreatdModal(false)
                    toast.success(`succesfully Notice Created`)
                }
                else {
                    setNoticeCreatdModal(false)
                    toast.error("Can't create notice");
                }
            })
    }
    return (
        <div>
        <input type="checkbox" id="notice-Created-modal" className="modal-toggle" />
        <div className="modal">
            <div className="modal-box">
                < header className='text-center text-2xl'>Create Notice Form</header>
                <button onClick={() => setNoticeCreatdModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                <form onSubmit={handleSubmit(onSubmit)} className="form">
                        <div className="input-box">
                            <label>Trainer Id</label>
                            <input type="number" {...register("trainerId")} disabled />
                        </div>
                        <div className="input-box">
                            <label>Title</label>
                            <input type="text" {...register("title")} />
                        </div>
                        <div className="input-box">
                            <label>Description</label>
                            <input type="text" {...register("description", { required: true })} />
                        </div>
                        <button htmlFor="notice-Created-modal" type='submit'>Submit</button>
                    </form>
            </div>
        </div>
    </div>
    );
};

export default NoticeCreateModal;
import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-hot-toast';
import { useParams } from 'react-router-dom';

const PostCreatedModal = ({ setPostModal, trainer,refetch }) => {
    const { classRoomId } = useParams();
    const { register, handleSubmit } = useForm({
    });
    const onSubmit = (data) => {
        const currentDate = new Date();
        const year = currentDate.getFullYear();
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const day = String(currentDate.getDate()).padStart(2, '0');

        const formattedDate = `${year}-${month}-${day}`;

        const file = data.postFile[0];
        const formData = new FormData();
        formData.append('file', file);
        axios.post('http://localhost:8082/api/upload', formData)
            .then((response) => {
                if (response.status == 200) {
                    const attachmentData = {
                        classRoomId: classRoomId,
                        trainerId: trainer.trainerId,
                        trainerName: trainer.fullName,
                        profilePicture: trainer.profilePicture,
                        msg: data.msg,
                        postFile: response.data.name,
                        postDate: formattedDate
                    }
                    console.log(attachmentData);
                    fetch('http://localhost:8082/api/classrooms/add-post', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            authorization: `Bearer ${localStorage.getItem('accessToken')}`
                        },
                        body: JSON.stringify(attachmentData)
                    })
                        .then(res => {
                            if (res.status === 401 || res.status === 403) {
                                toast.error(`Access denied please Login Again`);
                                localStorage.removeItem('accessToken');
                                localStorage.removeItem('myAppState');
                                navigate('/login');
                            }
                            return res.json();
                        })
                        .then(data => {
                            if (data.status == 200) {
                                refetch();
                                toast.success(`succesfully post Created`)
                            }
                            else {
                                toast.error(data.msg);
                            }
                        })
                }
                else {
                    setPostModal(false);
                    toast.error(response.data.msg);
                }
            })
            .catch((error) => {
                setPostModal(false)
                toast.error("Server Error for Uploading Image");
            });
    }
    return (
        <div>
            <input type="checkbox" id="post-create-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl text-blue mb-4'>Post Creation Form</header>
                    <button onClick={() => setPostModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit(onSubmit)} className="form">
                        <textarea
                            placeholder="Enter your Post here"
                            className="w-full h-20 p-2 border rounded-md"
                            {...register("msg", { required: true })} />
                        <div className="input-box">
                            <label>Attachment File</label>
                            <input type="file" {...register("postFile", { required: true })} />
                        </div>
                        <button htmlFor="post-create-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default PostCreatedModal;
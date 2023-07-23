import React, { useEffect, useState } from 'react';
import { useUser } from '../../../Context/UserProvider';
import ClassRoomNav from './ClassRoomNav';
import Post from './Post';
import NoticeCreateModal from './NoticeCreateModal';
import { useQuery } from 'react-query';
import Loading from '../../Shared/Loading';
import { useParams } from 'react-router-dom';
import { toast } from 'react-hot-toast';


const Classrooms = () => {
    const [noticeCreatdModal, setNoticeCreatdModal] = useState(false);
    const [textValue, setTextValue] = useState('');
    const { classRoomId } = useParams();
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const { data: classRoom, refetch, isLoading } = useQuery({
        queryKey: ['getClassRoom'],
        queryFn: async () => {
            const url = `http://localhost:8082/api/classroom/${classRoomId}`;

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
    const [trainee, setTrainee] = useState(false);
    const [trainer, setTrainer] = useState(false);

    useEffect(() => {
        if (userDetails?.userId && userDetails?.role == 'trainee') {
            fetch(`http://localhost:8082/api/trainee/${userDetails.userId}`,{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    authorization: `Bearer ${localStorage.getItem('accessToken')}`
                }
            })
                .then((res) => res.json())
                .then((data) => setTrainee(data));
        }
        else if (userDetails?.userId && userDetails?.role == 'trainer') {
            fetch(`http://localhost:8082/api/trainer/${userDetails.userId}`,{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    authorization: `Bearer ${localStorage.getItem('accessToken')}`
                }
            })
                .then((res) => res.json())
                .then((data) => setTrainer(data));
        }
    }, [userDetails?.userId]);

    if (isLoading) {
        return <Loading></Loading>
    }

    // Handler for the button click event
    const handleButtonClick = () => {
        const currentDate = new Date();
        const year = currentDate.getFullYear();
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const day = String(currentDate.getDate()).padStart(2, '0');

        const formattedDate = `${year}-${month}-${day}`;
        const postData = {
            classRoomId: classRoomId,
            trainerId: trainer.trainerId,
            trainerName: trainer.trainerName,
            profilePicture: trainer.profilePicture,
            msg: textValue,
            postDate: formattedDate
        };
        fetch('http://localhost:8082/api/classroom/add-post', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(postData)
        })
            .then(res => {
                if (res.status === 401 || res.status === 403) {
                    toast.error(`${res.statusText} Access`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                if (data.status == 200) {
                    setTextValue('');
                    refetch();
                    toast.success(`succesfully post Created`)
                }
                else {
                    toast.error(data.msg);
                }
            })

    };
    return (
        <div>
            <ClassRoomNav isLoading={isLoading} setNoticeCreatdModal={setNoticeCreatdModal} classRoom={classRoom}></ClassRoomNav>
            <div className='m-8'>
                {
                    trainer && <div className='bg-white rounded-lg shadow-lg p-6 mb-4'>
                        <div className="flex items-center">
                            {/* Replace 'avatar_image_url' with the URL of your avatar image */}
                            <img
                                src={`http://localhost:8082/api/download/${trainer.profilePicture}`}
                                alt="Avatar"
                                className="w-16 h-16 rounded-full mr-4"
                            />

                            {/* Container for the textarea */}
                            <div className="flex-1">
                                {/* Text area to input text */}
                                <textarea
                                    value={textValue}
                                    onChange={(e) => setTextValue(e.target.value)}
                                    placeholder="Enter your Post here"
                                    className="w-full h-20 p-2 border rounded-md"
                                />
                            </div>

                            {/* Button to trigger an action */}
                            <button
                                onClick={handleButtonClick}
                                className="px-4 ms-4 py-2 text-white bg-blue-500 rounded-md"
                            >
                                Submit
                            </button>
                        </div>
                    </div>
                }
                <div className="">
                    <h1 className="text-2xl font-bold mb-4">Post Comment App</h1>
                    {
                        classRoom?.posts.map((post, index) => <Post
                            key={post.postId} post={post}
                            trainer={trainer}
                            trainee={trainee}
                            refetch={refetch}
                        ></Post>)
                    }
                </div >
            </div >
            {
                noticeCreatdModal && <NoticeCreateModal
                    setNoticeCreatdModal={setNoticeCreatdModal}
                ></NoticeCreateModal>
            }
        </div >
    );
};

export default Classrooms;
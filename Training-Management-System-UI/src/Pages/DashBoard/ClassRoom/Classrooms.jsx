import React, { useState } from 'react';
import { useUser } from '../../../Context/UserProvider';
import ClassRoomNav from './ClassRoomNav';
import Post from './Post';
import NoticeCreateModal from './NoticeCreateModal';
import { useQuery } from 'react-query';
import Loading from '../../Shared/Loading';
import { useParams } from 'react-router-dom';


const Classrooms = () => {
    const [noticeCreatdModal, setNoticeCreatdModal] = useState(false);
    const { classRoomId } = useParams();
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    const { data: classRoom, refetch, isLoading } = useQuery({
        queryKey: ['getClassRoom'],
        queryFn: async () => {
            const res = await fetch(`http://localhost:8082/api/classroom/${classRoomId}`);
            const data = await res.json();
            console.log(data);
            return data
        }
    });
    if (isLoading) {
        return <Loading></Loading>
    }
    const [textValue, setTextValue] = useState('');

    // Handler for the button click event
    const handleButtonClick = () => {
        // Perform any action you'd like to do with the text (e.g., display it, send it to the server, etc.)
        console.log('Text entered:', textValue);
    };
    const dummyPosts = [
        {
            id: 1,
            title: 'Post 1',
            content: "Thilphanumeric text to  work activities).",
            imageUrl: 'https://placekitten.com/400/300', // Replace with your image URL
            date: new Date().toLocaleString(), // Current local date and time
        },
        {
            id: 2,
            title: 'Post 2',
            content: 'This is the content of Post 2.',
            imageUrl: 'https://placekitten.com/400/300', // Replace with your image URL
            date: new Date().toLocaleString(), // Current local date and time
        },
    ];
    return (
        <div>
            <ClassRoomNav isLoading={isLoading} setNoticeCreatdModal={setNoticeCreatdModal} classRoom={classRoom}></ClassRoomNav>
            <div className='m-8'>
                <div className='bg-white rounded-lg shadow-lg p-6 mb-4'>
                    <div className="flex items-center">
                        {/* Replace 'avatar_image_url' with the URL of your avatar image */}
                        <img
                            src="https://placekitten.com/400/300"
                            alt="Avatar"
                            className="w-16 h-16 rounded-full mr-4"
                        />

                        {/* Container for the textarea */}
                        <div className="flex-1">
                            {/* Text area to input text */}
                            <textarea
                                value={textValue}
                                onChange={(e) => setTextValue(e.target.value)}
                                placeholder="Enter your text here"
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
                <div className="">
                    <h1 className="text-2xl font-bold mb-4">Post Comment App</h1>
                    {
                        dummyPosts.map((post, index) => <Post
                            key={post.id} post={post}
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
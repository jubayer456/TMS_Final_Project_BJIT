import React from 'react';
import Loading from '../../Shared/Loading';
import { useQuery } from 'react-query';
import Notice from './Notice';
import { useUser } from '../../../Context/UserProvider';

const ClassRoomNav = ({ setNoticeCreatdModal, classRoom, isLoading, setPostModal, trainer }) => {

    const { state, dispatch } = useUser();
    const { userDetails } = state;
    if (isLoading) {
        return <Loading></Loading>
    }
    return (
        <div className="navbar bg-slate-300">
            <div className="flex-1">
                <a className="btn btn-ghost normal-case text-xl">{classRoom?.classRoomName}</a>
            </div>
            <div className="flex-none">
                <div className="dropdown dropdown-end">
                    <label tabIndex={0} className="btn btn-ghost btn-circle">
                        <div className="indicator">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
                            <span className="badge badge-sm indicator-item">{classRoom.classRoomNotice?.length}</span>
                        </div>
                    </label>
                    <div tabIndex={0} className="mt-2 z-[10] card card-compact dropdown-content w-96 bg-base-100 shadow">
                        <div className="card-body">
                            {
                                classRoom?.classRoomNotice?.map((notice, index) => <Notice
                                    key={notice.noticeId}
                                    notice={notice}
                                ></Notice>)
                            }
                        </div>
                    </div>
                </div>
                {
                    trainer && <div className=" ">
                        <label htmlFor="post-create-modal" onClick={() => setPostModal(true)} className="btn bg-primary btn-sm text-white mr-3">Attachment</label>
                    </div>
                }
                {
                    (userDetails?.role === "TRAINER" || userDetails?.role === "ADMIN") &&
                    <label htmlFor="notice-Created-modal" onClick={() => setNoticeCreatdModal(true)} className="btn btn-primary btn-sm text-white mr-3">Notice</label>

                }
            </div>
        </div>
    );
};

export default ClassRoomNav;
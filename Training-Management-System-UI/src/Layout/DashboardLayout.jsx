import React from 'react';
import Navbar from '../Pages/DashBoard/Navbar/Navbar';
import { Link, Outlet } from 'react-router-dom';
import { useUser } from '../Context/UserProvider';
import { useQuery } from 'react-query';
import { toast } from 'react-hot-toast';

const DashboardLayout = () => {
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    // console.log(userDetails);

    const { data: trainee, refetch, isLoading } = useQuery({
        queryKey: ['getTraineeBatch'],
        queryFn: async () => {
            if(userDetails?.role=='TRAINEE'){
                const url = `http://localhost:8082/api/trainees/${userDetails?.userId}`;
            const headers = {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
            };
            const res = await fetch(url, { headers });
            if (res.status === 401 || res.status === 403) {
                toast.error(`Access denied`);
            }
            const data = await res.json();
            return data;
            }
        }
    });

    return (
        <div>
            <Navbar></Navbar>
            <div className="drawer lg:drawer-open">
                <input id="drawer-content" type="checkbox" className="drawer-toggle" />
                <div className="drawer-content">
                    <Outlet></Outlet>
                </div>
                <div className="drawer-side">
                    <label htmlFor="drawer-content" className="drawer-overlay"></label>
                    <ul className="menu p-4 w-60 h-full bg-sky-600 text-base-content hover-bg-red-400 text-white font-medium" >
                        {/* Sidebar content here */}
                        {
                           userDetails?.role == "ADMIN" && <>
                            <li><Link to="/dashboard/trainers">Create Trainer</Link></li>
                            <li><Link to="/dashboard/trainees">Create Trainee</Link></li>
                            <li><Link to="/dashboard/admin">My Profile</Link></li>
                            <li><Link to="/dashboard/course">course</Link></li>
                            <li><Link to="/dashboard/batch">Batch</Link></li>
                        </> ||

                        userDetails?.role == "TRAINEE" && <>
                            <li><Link to="/dashboard/trainee">My Profile</Link></li>
                            <li><Link to="/dashboard/assignmetSub">Assignment Sub</Link></li>
                           {
                                trainee?.batchId &&
                                <li><Link to={`/dashboard/classroom/${trainee?.batchId}`}>ClassRoom</Link></li>
                           }
                        </>||
                        userDetails?.role == "TRAINER" && <>
                            <li><Link to="/dashboard/trainer">My Profile</Link></li>
                            <li><Link to="/dashboard/schedules">Assignment Create</Link></li>
                            <li><Link to="/dashboard/classrooms">ClassRoom</Link></li>
                        </>
                        }
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default DashboardLayout;
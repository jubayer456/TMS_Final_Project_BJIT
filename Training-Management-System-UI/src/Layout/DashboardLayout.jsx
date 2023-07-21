import React from 'react';
import Navbar from '../Pages/DashBoard/Navbar/Navbar';
import { Link, Outlet } from 'react-router-dom';
import { useUser } from '../Context/UserProvider';
import { useQuery } from 'react-query';

const DashboardLayout = () => {
    const { state, dispatch } = useUser();
    const { userDetails } = state;
    console.log(userDetails);

    const { data: trainee, refetch, isLoading } = useQuery({
        queryKey: ['getTraineeBatch'],
        queryFn: async () => {
            const res = await fetch(`http://localhost:8082/api/trainee/${userDetails?.userId}`);
            const data = await res.json();
            console.log(data);
            return data
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
                    <ul className="menu p-4 w-60 h-full bg-base-200 text-base-content hover-bg-base-400">
                        {/* Sidebar content here */}
                        {
                           userDetails?.role == "admin" && <>
                            <li><Link to="/dashboard/registerTrainer">Create Trainer</Link></li>
                            <li><Link to="/dashboard/registerTrainee">Create Trainee</Link></li>
                            <li><Link to="/dashboard/admin">Admin</Link></li>
                            <li><Link to="/dashboard/course">course</Link></li>
                            <li><Link to="/dashboard/batch">Batch</Link></li>
                        </> ||

                        userDetails?.role == "trainee" && <>
                            <li><Link to="/dashboard/trainee">Trainee</Link></li>
                            <li><Link to="/dashboard/assignmetSub">Assignment Sub</Link></li>
                           {
                                trainee?.batchId &&
                                <li><Link to={`/dashboard/classroom/${trainee?.batchId}`}>ClassRoom</Link></li>
                           }
                        </>||
                        userDetails?.role == "trainer" && <>
                            <li><Link to="/dashboard/trainer">Trainer</Link></li>
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
import React, { useEffect, useState } from 'react';
import { useUser } from '../../../Context/UserProvider';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-hot-toast';
import bjit from '../../../assets/BJIT.png'
import img from '../../../assets/18942381.png'

const Navbar = () => {
    const { state, dispatch } = useUser();
    const [user, setUser] = useState({});
    const { userDetails } = state;
    const navigate = useNavigate();
    useEffect(() => {
        fetch(`http://localhost:8082/api/auth/${userDetails?.userId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
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
            .then(data => setUser(data))
    }, []);

    const handleLogout = () => {
        dispatch({ type: 'LOGOUT' });
        localStorage.removeItem('accessToken');
        localStorage.removeItem('myAppState');
        navigate('/login');
    };
    return (
        <div className="navbar bg-sky-500 text-white font-medium">
            <div className="flex-1">
                <label htmlFor="drawer-content" tabIndex={1} className="btn btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h8m-8 6h16" /></svg>
                </label>
                <div className="w-10 rounded-full">
                    <img src={bjit} />
                </div>
                <a className=" ms-3 normal-case text-xl text-bold ">Trainning Management System</a>
            </div>
            <div className="flex-none gap-2">
                <p className='text-bold'>{user?.fullName}</p>
                <div className="dropdown dropdown-end">
                    <label tabIndex={0} className="btn btn-ghost btn-circle avatar">
                        <div className="w-10 rounded-full">
                            {
                                user?.profilePicture ? <img src={`http://localhost:8082/api/download/${user?.profilePicture}`} alt="Trainer Profile Picture" /> :
                                    <img src={img} alt="Trainee default Profile Picture" />
                            }
                        </div>
                    </label>
                    <ul tabIndex={0} className="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-sky-500 rounded-box w-52">
                        <li>
                            <Link to={`/dashboard/${userDetails?.role}`} className="justify-between">
                                Profile
                            </Link>
                        </li>
                        <li><a onClick={() => handleLogout()}>Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default Navbar;
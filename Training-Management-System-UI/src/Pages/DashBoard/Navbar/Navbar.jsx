import React, { useEffect, useState } from 'react';
import { useUser } from '../../../Context/UserProvider';
import { Link, useNavigate } from 'react-router-dom';

const Navbar = () => {
    const { state, dispatch } = useUser();
    const [user,setUser]=useState({});
    const {userDetails}=state;
    useEffect(()=>{
        fetch(`http://localhost:8082/api/auth/${userDetails?.userId}`)
        .then(res=>res.json())
        .then(data=>setUser(data))
    },[]);
    const navigate=useNavigate();
    const handleLogout = () => {
        dispatch({ type: 'LOGOUT' });
        localStorage.removeItem('accessToken');
        localStorage.removeItem('myAppState');
            navigate('/login');
      };
    return (
        <div className="navbar bg-base-100">
            <div className="flex-1">
                <label htmlFor="drawer-content" tabIndex={1} className="btn btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h8m-8 6h16" /></svg>
                </label>
                <a className="btn btn-ghost normal-case text-xl text-bold">{user?.role}</a>
            </div>
            <div className="flex-none gap-2">
                <p className='text-bold'>{user?.fullName}</p>
                <div className="dropdown dropdown-end">
                    <label tabIndex={0} className="btn btn-ghost btn-circle avatar">
                        <div className="w-10 rounded-full">
                            <img src={`http://localhost:8082/api/download/${user?.profilePicture}`} />
                        </div>
                    </label>
                    <ul tabIndex={0} className="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-base-100 rounded-box w-52">
                        <li>
                            <Link to={`/dashboard/${userDetails?.role}`} className="justify-between">
                                Profile
                                <span className="badge">New</span>
                            </Link>
                        </li>
                        <li><a onClick={()=>handleLogout()}>Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default Navbar;
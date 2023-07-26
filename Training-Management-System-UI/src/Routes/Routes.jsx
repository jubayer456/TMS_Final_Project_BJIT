import React from 'react';
import DashboardLayout from '../Layout/DashboardLayout';
import Batch from '../Pages/DashBoard/Batch/Batchs';
import Login from '../Pages/Login/Login'
import AdminProfile from '../Pages/DashBoard/AdminProfile/AdminProfile'
import TrainerProfile from '../Pages/DashBoard/TrainerProfile/TrainerProfile'
import TraineeProfile from '../Pages/DashBoard/TraineeProfile/TraineeProfile'
import { createBrowserRouter } from 'react-router-dom';
import Courses from '../Pages/DashBoard/Course/Courses';
import NotFound from '../Pages/Shared/NotFound';
import BatchTrainee from '../Pages/DashBoard/Batch/BatchTrainee';
import BatchSchedule from '../Pages/DashBoard/Batch/BatchSchedule';
import AssignTrainee from '../Pages/DashBoard/Batch/AssignTraineeModal';
import AssignSchedule from '../Pages/DashBoard/Batch/AssignScheduleModal';
import Assignments from '../Pages/DashBoard/Assignmet/Assignments';
import TrainerSchedules from '../Pages/DashBoard/Assignmet/TrainerSchedules';
import AssignmentSubmissions from '../Pages/DashBoard/AssignmentSub/AssignmentSubmissions';
import ShowAllAssignmentSubmission from '../Pages/DashBoard/Assignmet/ShowAllAssignmentSubmission';
import Trainers from '../Pages/DashBoard/Trainer/Trainers';
import Trainees from '../Pages/DashBoard/Trainee/Trainees';
import Classroom from '../Pages/DashBoard/ClassRoom/Classrooms';
import TrainerClassRoom from '../Pages/DashBoard/ClassRoom/TrainerClassRoom';
import RequireAuth from '../Pages/Shared/RequireAuth';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Login />,
    },
    {
        path: "/login",
        element: <Login />,
    },
    {
        path: "*",
        element: <NotFound />,
    },
    {
        path: '/dashboard',
        element: <RequireAuth><DashboardLayout/></RequireAuth>,
        children: [
            {
                path: '/dashboard',
                element: <DashboardLayout />
            },
            {
                path: '/dashboard/trainers',
                element: <Trainers />
            },
            {
                path: '/dashboard/classroom/:classRoomId',
                element: <Classroom />
            },
            {
                path: '/dashboard/classrooms',
                element: <TrainerClassRoom />
            },
            {
                path: '/dashboard/trainees',
                element: <Trainees />
            },
            {
                path: '/dashboard/admin',
                element: <AdminProfile />
            },
            {
                path: '/dashboard/trainer',
                element: <TrainerProfile />
            },
            {
                path: '/dashboard/trainee',
                element:<TraineeProfile />
            },
            {
                path: '/dashboard/course',
                element: <Courses />
            },
            {
                path: '/dashboard/batch',
                element: <Batch />
            },
            {
                path: '/dashboard/batch/:batchId/trainees',
                element: <BatchTrainee />
            },
            {
                path: '/dashboard/batch/:batchId/schedules',
                element: <BatchSchedule />
            },
            {
                path: '/dashboard/batch/add-trainee',
                element: <AssignTrainee />
            },
            {
                path: '/dashboard/batch/add-scehedule',
                element: <AssignSchedule />
            },
            {
                path: '/dashboard/schedules',
                element: <TrainerSchedules />
            },
            {
                path: '/dashboard/schedules/:scheduleId/getAllAssignment',
                element: <Assignments />
            }, 
            {
                path: '/dashboard/schedules/:scheduleId/:assignmentId',
                element: <ShowAllAssignmentSubmission />
            },
            {
                path: '/dashboard/assignmetSub',
                element: <AssignmentSubmissions />
            },
        ]
    },
])

export default router;
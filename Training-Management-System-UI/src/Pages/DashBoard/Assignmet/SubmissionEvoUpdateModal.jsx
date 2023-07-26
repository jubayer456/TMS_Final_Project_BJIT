import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import '../../Shared/Register.css'

const SubmissionEvoUpdateModal = ({asignEvoUpdateModel,setAsignEvoUpdateModel,refetch}) => {
    const {asgSubId,evolution,assignmentId}=asignEvoUpdateModel;
    const navigate = useNavigate();
    const { register, handleSubmit } = useForm({
        defaultValues: {
            asgSubId: asgSubId,
            evolution: evolution
        },
    });
    const onSubmit = data => {
        console.log(data);
        const evoData = {
            asgSubId: data.asgSubId,
            evolution: data.evolution
        }
        console.log(evoData);
        fetch(`http://localhost:8082/api/schedule/${assignmentId}/${asgSubId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(evoData)
        })
            .then(res => {
                console.log(res);
                if (res.status === 401 || res.status === 403) {
                    toast.error(`Access denied Login again Please`);
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('myAppState');
                    navigate('/login');
                }
                return res.json();
            })
            .then(data => {
                console.log(data);
                if (data.status == 200) {
                    refetch;
                    setAsignEvoUpdateModel(null);
                    toast.success(`succesfully Marks Submitted`)
                }
                else {
                    setAsignEvoUpdateModel(null);
                    toast.error(data.msg);
                }
            })

    }
    return (
        <div>
        <input type="checkbox" id="submissionEvo-update-modal" className="modal-toggle" />
       <div className="modal">
           <div className="modal-box">
               < header className='text-center text-2xl'>Assignment Creation Form</header>
               <button onClick={() => setAsignEvoUpdateModel(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
               <form onSubmit={handleSubmit(onSubmit)} className="form">
                        <div className="input-box">
                            <label>Submission Id:</label>
                            <input type="number" {...register("asgSubId")} disabled />
                        </div>
                        <div className="input-box">
                            <label>Evalution Mark:</label>
                            <input type="number" {...register("evolution", { required: true })} />
                        </div>
                        <button htmlFor="submissionEvo-update-modal" type='submit'>Submit</button>
                </form>
           </div>
       </div>
    </div>
    );
};

export default SubmissionEvoUpdateModal;
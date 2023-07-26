import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import '../../Shared/Register.css'
const SubmissionEvoModal = ({setAsignEvoModel,asignEvoModel,refetch}) => {
    const {asgSubId,assignmentId}=asignEvoModel
    const navigate = useNavigate();
    const { register, handleSubmit } = useForm({
        defaultValues: {
            asgSubId : asgSubId,
        },
    });
    const onSubmit = data => {
        console.log(data);
        const evoData = {
            asgSubId: data.asgSubId,
            evolution: data.evolution
        }
        console.log(evoData);
        fetch(`http://localhost:8082/api/schedules/${assignmentId}/${asgSubId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(evoData)
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
            .then(data => {
                if (data.status == 200) {
                    refetch();
                    setAsignEvoModel(null);
                    toast.success(`succesfully Marks Submitted`)
                }
                else {
                    setAsignEvoModel(null);
                    toast.error(data.msg);
                }
            })

    }
    return (
        <div>
            <input type="checkbox" id="submissionEvo-modal" className="modal-toggle" />
            <div className="modal">
                <div className="modal-box">
                    < header className='text-center text-2xl'>EvalutionForm</header>
                    <button onClick={() => setAsignEvoModel(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    <form onSubmit={handleSubmit(onSubmit)} className="form">
                        <div className="input-box">
                            <label>Assignment Submission Id:</label>
                            <input type="number" {...register("asgSubId")} disabled />
                        </div>
                        <div className="input-box">
                            <label>Evalution Mark:</label>
                            <input type="number" {...register("evolution", { required: true })} />
                        </div>
                        <button htmlFor="submissionEvo-modal" type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default SubmissionEvoModal;
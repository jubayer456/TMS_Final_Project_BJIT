// import React from 'react';
// import { useForm } from 'react-hook-form';

// const Test = ({test,setTest}) => {
//     const {asgSubId}=test
//     const { register, handleSubmit } = useForm({
//         defaultValues: {
//             asgSubId:asgSubId,
//         },
//     });
//     const onSubmit = data => {
//         console.log(data);
//     }
//     return (
//         <div>
//         <input type="checkbox" id="assignmentEvo-modal" className="modal-toggle" />
//         <div className="modal">
//             <div className="modal-box">
//                 < header className='text-center text-2xl'>Assignment Submission Form</header>
//                 <button onClick={() => setAssignSubModal(false)} className="btn btn-md btn-circle btn-ghost absolute right-2 top-2">✕</button>
//                 <form onSubmit={handleSubmit(onSubmit)} className="form">
//                         <div className="input-box">
//                             <label>Assignment Id:</label>
//                             <input type="number" {...register("assignmentId")} disabled />
//                         </div>
//                         <div className="input-box">
//                             <label>Trainee Id:</label>
//                             <input type="text" {...register("traineeId")} disabled/>
//                         </div>
//                         <div className="input-box">
//                             <label>Assignment submission File:</label>
//                             <input type="file" {...register("submissionFile", { required: true })} />
//                         </div>
//                         <button htmlFor="assignmentEvo-modal" type='submit'>Submit</button>
//                     </form>
//             </div>
//         </div>
//     </div>
//     );
// };

// export default Test;
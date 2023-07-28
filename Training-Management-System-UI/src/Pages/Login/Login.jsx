import { useForm } from "react-hook-form";
import { toast } from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { useUser } from "../../Context/UserProvider";

const Login = () => {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const navigate = useNavigate();
    const { state, dispatch } = useUser();
    const setUserDetails = (user) => {
        dispatch({
          type: 'SET_USER_DETAILS',
          payload: user,
        });
      };

    const onSubmit = data => {
        fetch(`http://localhost:8082/api/auth/login`, {
        method: 'POST',
            headers: {
                'content-type': 'application/json',  
            },
            body: JSON.stringify(data)
        })
            .then(res =>res.json())
            .then(data => {
                const accesssToken = data.token;
                localStorage.setItem('accessToken', accesssToken);
                setUserDetails(data.user);
                const role = data.user.role.toLowerCase();
                navigate(`/dashboard/${role}`)
               
            })
            .catch(e=> {
                toast.error("Failed to login,,, please enter a valid Password And email", e);
          });
    };
    return (
        <div>
            <div className="card w-96  bg-base-100 shadow-xl mx-auto my-8">
                <div className="card-body">
                    <h2 className="font-bold text-3xl text-center">Login!!!</h2>
                    <form onSubmit={handleSubmit(onSubmit)}>

                        <div className="form-control w-full max-w-xs">
                            <label className="label">
                                <span className="label-text">Email:</span>
                            </label>
                            <input
                                type='email'
                                placeholder="Your Email"
                                className="input input-bordered w-full max-w-xs"
                                {...register("email", {
                                    required: {
                                        value: true,
                                        message: "Email is required"
                                    },
                                    pattern: {
                                        value: /[a-z0-9]+@[a-z]+[a-z]{2,3}/,
                                        message: "Please provide a valid email"
                                    }
                                })} />
                            <label className="label">
                                {errors.email?.type === 'required' && <span className="label-text-alt text-red-500">{errors.email.message}</span>}
                                {errors.email?.type === 'pattern' && <span className="label-text-alt text-red-500">{errors.email.message}</span>}

                            </label>
                        </div>
                        <div className="form-control w-full max-w-xs">
                            <label className="label">
                                <span className="label-text">Password:</span>
                            </label>
                            <input
                                type='password'
                                placeholder="Your Password"
                                className="input input-bordered w-full max-w-xs"
                                {...register("password", {
                                    required: {
                                        value: true,
                                        message: "Password is required"
                                    },
                                    minLength: {
                                        value: 6,
                                        message: "Please provide mimimum 6 length password"
                                    }
                                })} />
                            <label className="label">
                                {errors.password?.type === 'required' && <span className="label-text-alt text-red-500">{errors.password.message}</span>}
                                {errors.password?.type === 'minLength' && <span className="label-text-alt text-red-500">{errors.password.message}</span>}
                            </label>
                            <label className="label">
                                <span className="label-text">Forgot password?</span>
                            </label>
                        </div>
                        <input type="submit" className="btn btn-primary w-full" value='Login' />
                    </form>

                </div>

            </div>

        </div>
    );
};

export default Login;
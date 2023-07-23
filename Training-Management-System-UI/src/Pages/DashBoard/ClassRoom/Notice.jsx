import React from 'react';

const Notice = ({notice}) => {
    const {title,description,trainerName}=notice;
    return (
        <div class="collapse collapse-plus bg-base-200">
            <input type="radio" name="my-accordion-3" checked="checked" />
            <div class="collapse-title text-md font-medium">
                <span className='mr-4'>{trainerName}</span><span>{title}</span>
            </div>
            <div class="collapse-content">
                <p>{description}</p>
            </div>
        </div>
    );
};

export default Notice;
import React from 'react';

const Notice = ({notice}) => {
    return (
        <div class="collapse collapse-plus bg-base-200">
            <input type="radio" name="my-accordion-3" checked="checked" />
            <div class="collapse-title text-md font-medium">
                Click to open this one and close others
            </div>
            <div class="collapse-content">
                <p>hello</p>
            </div>
        </div>
    );
};

export default Notice;
package com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchScheduleResDto {
    private Long scheduleId;
    private Long batchId;

    private Long courseId;
    private String courseName;
    private String startingDate;
    private String endingDate;
    private String trainerName;
    private String profilePicture;
    private Long trainerId;
}

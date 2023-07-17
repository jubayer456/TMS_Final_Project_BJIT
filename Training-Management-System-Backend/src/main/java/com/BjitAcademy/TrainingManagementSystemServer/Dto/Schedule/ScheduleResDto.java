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
public class ScheduleResDto {
    private Long scheduleId;
    private String startingDate;
    private String endingDate;
    private Long courseId;
    private Long batchId;
    private Long trainerId;
}

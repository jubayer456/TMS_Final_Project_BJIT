package com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AsignSubResDto {
    private Long asgSubId;
    private Long assignmentId;
    private Long traineeId;
    private String submissionDate;
    private String submissionFile;
    private String profilePicture;
    private String assignmentName;
    private String fullName;
    private Long batchId;
    private Long evolution;
}

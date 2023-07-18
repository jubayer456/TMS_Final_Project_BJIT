package com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AsignSubReqDto {
    private Long assignmentId;
    private Long traineeId;
    private Long batchId;
    private String submissionDate;
    private String submissionFile;
    private String scheduleId;
}

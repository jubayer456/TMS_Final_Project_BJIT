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
public class AssignmentResDto {
    private Long assignmentId;
    private Long scheduleId;
    private String assignmentName;
    private String assignmentFile;
    private String deadLine;
    private Long batchId;
}

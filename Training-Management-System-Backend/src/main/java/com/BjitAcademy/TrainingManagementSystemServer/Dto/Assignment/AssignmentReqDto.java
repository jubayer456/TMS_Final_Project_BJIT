package com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssignmentReqDto {
    private Long scheduleId;
    private String assignmentName;
    private String assignmentFile;
    private String deadLine;
}

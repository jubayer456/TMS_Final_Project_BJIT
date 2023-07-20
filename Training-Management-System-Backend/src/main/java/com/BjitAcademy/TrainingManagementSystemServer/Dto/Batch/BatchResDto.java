package com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchResDto {
    private Long batchId;
    private String batchName;
    private String startingDate;
    private String endingDate;
    private Integer totalNumOfTrainee;
    private String classRoomName;
}

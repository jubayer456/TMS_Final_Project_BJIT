package com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchReqDto {
    private Long batchId;
    private String batchName;
    private String startingDate;
    private String endingDate;
    private Integer totalNumOfTrainee;
}

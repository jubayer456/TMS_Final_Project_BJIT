package com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchTraineeReqDto {
    private Long batchId;
    private Long traineeId;
}

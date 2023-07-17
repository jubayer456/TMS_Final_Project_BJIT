package com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchTraineeResDto {
    private Long traineeId;
    private String fullName;
    private String profilePicture;
    private String email;
}

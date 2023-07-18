package com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentEvoReqDto {
    private Long asgSubId;
    private Long evolution;
}

package com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponseDto {
    private Integer status;
    private String msg;
}

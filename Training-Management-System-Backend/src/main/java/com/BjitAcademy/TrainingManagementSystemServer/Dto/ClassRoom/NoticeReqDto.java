package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeReqDto {
    private Long trainerId;
    private String trainerName;
    private String title;
    private String description;
    private Long classRoomId;
}

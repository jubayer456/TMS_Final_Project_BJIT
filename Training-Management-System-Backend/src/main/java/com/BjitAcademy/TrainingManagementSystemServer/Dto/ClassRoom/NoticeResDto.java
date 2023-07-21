package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeResDto {
    private Long noticeId;
    private Long trainerId;
    private Long classRoomId;
    private String trainerName;
    private String title;
    private String description;
}

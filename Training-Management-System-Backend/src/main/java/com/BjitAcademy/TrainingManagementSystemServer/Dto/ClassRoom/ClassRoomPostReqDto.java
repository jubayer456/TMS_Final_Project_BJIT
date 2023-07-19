package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomPostReqDto {
    private Long classRoomId;
    private Long trainerId;
    private String msg;
    private String postFile;
}

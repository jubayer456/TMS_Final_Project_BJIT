package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCommentReqDto {
    private Long postId;
    private Long traineeId;
    private String profilePicture;
    private String traineeName;
    private String commentDate;
    private String msg;
}

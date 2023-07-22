package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCommentResDto {
    private Long postId;
    private Long commentId;
    private Long traineeId;
    private String traineeName;
    private String profilePicture;
    private String commentDate;
    private String msg;
}

package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomPostResDto {
    private Long postId;
    private Long classRoomId;
    private Long trainerId;
    private String msg;
    private String postFile;
    private Set<PostCommentResDto> comments;
}

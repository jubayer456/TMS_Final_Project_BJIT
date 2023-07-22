package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoom;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoomNotice;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostComment;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostEntity;

import java.util.List;

public class ClassRoomMappingModel {
    public static PostEntity postDtoToEntity(ClassRoomPostReqDto classRoomPostReqDto){
        return PostEntity.builder()
                .classRoomId(classRoomPostReqDto.getClassRoomId())
                .trainerId(classRoomPostReqDto.getTrainerId())
                .msg(classRoomPostReqDto.getMsg())
                .postFile(classRoomPostReqDto.getPostFile())
                .postDate(classRoomPostReqDto.getPostDate())
                .trainerName(classRoomPostReqDto.getTrainerName())
                .profilePicture(classRoomPostReqDto.getProfilePicture())
                .build();
    }
    public static ClassRoomPostResDto postEntityToDto(PostEntity postEntity, List<PostCommentResDto> comment){
        return ClassRoomPostResDto.builder()
                .classRoomId(postEntity.getClassRoomId())
                .profilePicture(postEntity.getProfilePicture())
                .postDate(postEntity.getPostDate())
                .trainerName(postEntity.getTrainerName())
                .postId(postEntity.getPostId())
                .trainerId(postEntity.getTrainerId())
                .msg(postEntity.getMsg())
                .postFile(postEntity.getPostFile())
                .comments(comment)
                .build();
    }
    public static PostComment commentDtoEntity(PostCommentReqDto postCommentReqDto){
        return PostComment.builder()
                .postId(postCommentReqDto.getPostId())
                .traineeId(postCommentReqDto.getTraineeId())
                .msg(postCommentReqDto.getMsg())
                .commentDate(postCommentReqDto.getCommentDate())
                .profilePicture(postCommentReqDto.getProfilePicture())
                .traineeName(postCommentReqDto.getTraineeName())
                .build();
    }
    public static PostCommentResDto commentEntityToDto(PostComment postComment){
        return PostCommentResDto.builder()
                .commentId(postComment.getCommentId())
                .postId(postComment.getPostId())
                .traineeId(postComment.getTraineeId())
                .msg(postComment.getMsg())
                .commentDate(postComment.getCommentDate())
                .profilePicture(postComment.getProfilePicture())
                .traineeName(postComment.getTraineeName())
                .build();
    }
    public static ClassRoomNotice noticeDtoToEntity(NoticeReqDto notice){
        return ClassRoomNotice.builder()
                .trainerName(notice.getTrainerName())
                .trainerId(notice.getTrainerId())
                .description(notice.getDescription())
                .title(notice.getTitle())
                .classRoomId(notice.getClassRoomId())
                .build();
    }
    public static NoticeResDto noticeEntityToDto(ClassRoomNotice notice){
        return NoticeResDto.builder()
                .noticeId(notice.getNoticeId())
                .trainerName(notice.getTrainerName())
                .trainerId(notice.getTrainerId())
                .description(notice.getDescription())
                .title(notice.getTitle())
                .classRoomId(notice.getClassRoomId())
                .build();
    }
    public static ClassRoomResponseDto classRoomEntityToDto(ClassRoom ClassRoom,List<ClassRoomPostResDto> posts,List<NoticeResDto> notice){
        return ClassRoomResponseDto.builder()
                .classRoomId(ClassRoom.getClassRoomId())
                .classRoomName(ClassRoom.getClassRoomName())
                .posts(posts)
                .classRoomNotice(notice)
                .build();
    }
}

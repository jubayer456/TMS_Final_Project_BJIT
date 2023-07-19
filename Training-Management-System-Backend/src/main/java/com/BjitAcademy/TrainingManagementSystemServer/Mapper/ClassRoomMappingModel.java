package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoomNotice;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostComment;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostEntity;

public class ClassRoomMappingModel {
    public static PostEntity postDtoToEntity(ClassRoomPostReqDto classRoomPostReqDto){
        return PostEntity.builder()
                .classRoomId(classRoomPostReqDto.getClassRoomId())
                .trainerId(classRoomPostReqDto.getTrainerId())
                .msg(classRoomPostReqDto.getMsg())
                .postFile(classRoomPostReqDto.getPostFile())
                .build();
    }
    public static ClassRoomPostResDto postEntityToDto(PostEntity postEntity){
        return ClassRoomPostResDto.builder()
                .classRoomId(postEntity.getClassRoomId())
                .postId(postEntity.getPostId())
                .trainerId(postEntity.getTrainerId())
                .msg(postEntity.getMsg())
                .postFile(postEntity.getPostFile())
                .build();
    }
    public static PostComment commentDtoEntity(PostCommentReqDto postCommentReqDto){
        return PostComment.builder()
                .postId(postCommentReqDto.getPostId())
                .traineeId(postCommentReqDto.getTraineeId())
                .msg(postCommentReqDto.getMsg())
                .build();
    }
    public static PostCommentResDto commentEntityToDto(PostComment postComment){
        return PostCommentResDto.builder()
                .commentId(postComment.getCommentId())
                .postId(postComment.getPostId())
                .traineeId(postComment.getTraineeId())
                .msg(postComment.getMsg())
                .build();
    }
    public static ClassRoomNotice noticeDtoToEntity(NoticeReqDto notice){
        return ClassRoomNotice.builder()
                .scheduleId(notice.getScheduleId())
                .trainerId(notice.getTrainerId())
                .msg(notice.getMsg())
                .build();
    }
    public static NoticeResDto noticeEntityToDto(ClassRoomNotice notice){
        return NoticeResDto.builder()
                .noticeId(notice.getNoticeId())
                .scheduleId(notice.getScheduleId())
                .trainerId(notice.getTrainerId())
                .msg(notice.getMsg())
                .build();
    }
}

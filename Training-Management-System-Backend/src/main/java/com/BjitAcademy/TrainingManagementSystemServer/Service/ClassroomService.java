package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ClassroomService {
    ResponseEntity<Object> addPost(ClassRoomPostReqDto post);
    ResponseEntity<Object> addNotice(NoticeReqDto notice);
    ResponseEntity<List<NoticeResDto>> getAllNotice(Long classId);
    ResponseEntity<Object> addComment(PostCommentReqDto comment);
    ResponseEntity<Object> updateComment(Long commentId, PostCommentReqDto comment);
    ResponseEntity<Object> updatePost(Long postId, ClassRoomPostReqDto post);
    ResponseEntity<Object> removePost(Long postId);
    ResponseEntity<Object> removeComment(Long postId,Long commentId);
    ResponseEntity<Set<ClassRoomPostResDto>> getAllPost(Long classId);
}

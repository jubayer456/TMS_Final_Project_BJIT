package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ClassroomService {
    ResponseEntity<Object> addPost(ClassRoomPostReqDto post);
    ResponseEntity<Object> addNotice(NoticeReqDto notice);
    ResponseEntity<List<NoticeResDto>> getAllNotice(Long classId);
    ResponseEntity<String> addComment(PostCommentReqDto comment);

}

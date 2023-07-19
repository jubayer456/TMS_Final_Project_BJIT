package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ClassroomService {
    ResponseEntity<String> addPost(ClassRoomPostReqDto post);
    ResponseEntity<String> addNotice(NoticeReqDto notice);
    ResponseEntity<List<NoticeResDto>> getAllNotice(Long classId);

}

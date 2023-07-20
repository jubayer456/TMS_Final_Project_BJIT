package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    @PostMapping("/api/classroom/add-post")
    public ResponseEntity<Object> addPost(@RequestBody ClassRoomPostReqDto post){
        return classroomService.addPost(post);
    }
    @PostMapping("/api/classroom/add-notice")
    public ResponseEntity<Object> addNotice(@RequestBody NoticeReqDto notice){
        return classroomService.addNotice(notice);
    }
    @GetMapping("/api/classroom/{classId}/getAllNotice")
    public ResponseEntity<List<NoticeResDto>> getAllNotice(@PathVariable Long classId){
        return classroomService.getAllNotice(classId);
    }
    @PostMapping("/api/classroom/add-comment")
    public ResponseEntity<String> addComment(@RequestBody PostCommentReqDto comment){
        return classroomService.addComment(comment);
    }
    @PutMapping("/api/classroom/update-comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId,@RequestBody PostCommentReqDto comment){
        return classroomService.updateComment(commentId,comment);
    }
}

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
    public ResponseEntity<Object> addComment(@RequestBody PostCommentReqDto comment){
        return classroomService.addComment(comment);
    }
    @PutMapping("/api/classroom/update-comment/{commentId}")
    public ResponseEntity<Object> updateComment(@PathVariable Long commentId,@RequestBody PostCommentReqDto comment){
        return classroomService.updateComment(commentId,comment);
    }
    @PutMapping("/api/classroom/update-post/{postId}")
    public ResponseEntity<Object> updatePost(@PathVariable Long postId,@RequestBody ClassRoomPostReqDto post){
        return classroomService.updatePost(postId,post);
    }

    @DeleteMapping("/api/classroom/remove-post/{postId}")
    public ResponseEntity<Object> removePost(@PathVariable Long postId){
        return classroomService.removePost(postId);
    }
    @DeleteMapping("/api/classroom/remove-comment/{postId}/{commentId}")
    public ResponseEntity<Object> removeComment(@PathVariable Long postId,@PathVariable Long commentId){
        return classroomService.removeComment(postId,commentId);
    }
    @GetMapping("/api/classroom/{classId}/getAllPost")
    public ResponseEntity<Set<ClassRoomPostResDto>> getAllPost(@PathVariable Long classId){
        return classroomService.getAllPost(classId);
    }
}

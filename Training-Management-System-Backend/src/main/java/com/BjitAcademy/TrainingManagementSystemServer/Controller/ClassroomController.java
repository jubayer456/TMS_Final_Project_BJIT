package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    @PostMapping("/api/classrooms/add-post")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Object> addPost(@RequestBody ClassRoomPostReqDto post){
        return classroomService.addPost(post);
    }
    @PostMapping("/api/classrooms/add-notice")
    @PreAuthorize("hasRole('TRAINER') OR hasRole('ADMIN)")
    public ResponseEntity<Object> addNotice(@RequestBody NoticeReqDto notice){
        return classroomService.addNotice(notice);
    }
    @GetMapping("/api/classrooms/{classId}/notices")
    public ResponseEntity<List<NoticeResDto>> getAllNotice(@PathVariable Long classId){
        return classroomService.getAllNotice(classId);
    }
    @PostMapping("/api/classrooms/add-comment")
    @PreAuthorize("hasRole('TRAINER') OR hasRole('TRAINEE)")
    public ResponseEntity<Object> addComment(@RequestBody PostCommentReqDto comment){
        return classroomService.addComment(comment);
    }
    @PutMapping("/api/classrooms/comments/{commentId}")
    @PreAuthorize("hasRole('TRAINER') AND hasRole('TRAINEE)")
    public ResponseEntity<Object> updateComment(@PathVariable Long commentId,@RequestBody PostCommentReqDto comment){
        return classroomService.updateComment(commentId,comment);
    }
    @PutMapping("/api/classrooms/posts/{postId}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Object> updatePost(@PathVariable Long postId,@RequestBody ClassRoomPostReqDto post){
        return classroomService.updatePost(postId,post);
    }

    @DeleteMapping("/api/classrooms/posts/{postId}/{trainerId}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Object> removePost(@PathVariable Long postId,@PathVariable Long trainerId){
        return classroomService.removePost(postId,trainerId);
    }

    @DeleteMapping("/api/classrooms/{postId}/{userId}/comments")
    @PreAuthorize("hasRole('TRAINER') AND hasRole('TRAINEE)")
    public ResponseEntity<Object> removeComment(@PathVariable Long postId,@PathVariable Long userId,@RequestParam("commentId") Long commentId){
        return classroomService.removeComment(postId,commentId,userId);
    }
    @GetMapping("/api/classrooms/{classId}/posts")
    public ResponseEntity<List<ClassRoomPostResDto>> getAllPost(@PathVariable Long classId){
        return classroomService.getAllPost(classId);
    }
    @GetMapping("/api/classrooms/{trainerId}/classRooms")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Set<BatchResDto>> getAllTrainerClass(@PathVariable Long trainerId){
        return classroomService.getAllTrainerClass(trainerId);
    }
    @GetMapping("/api/classrooms/{classroomId}")
    public ResponseEntity<ClassRoomResponseDto> getClassRoomDetails(@PathVariable Long classroomId){
        return classroomService.getClassRoomDetails(classroomId);
    }
}

package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoom;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoomNotice;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostComment;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.ClassRoomNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ClassRoomMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.ClassRoomRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.NoticeRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.PostCommentRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.PostRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImp implements ClassroomService {
    private final ClassRoomRepository classRoomRepository;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final NoticeRepository noticeRepository;

    @Override
    public ResponseEntity<String> addPost(ClassRoomPostReqDto postReq) {
        PostEntity post= ClassRoomMappingModel.postDtoToEntity(postReq);
        ClassRoom classRoom=classRoomRepository.findByClassRoomId(postReq.getClassRoomId());
        if (classRoom==null){
            throw new ClassRoomNotFoundException("classRoom not found for post");
        }
        classRoom.getPosts().add(postRepository.save(post));
        classRoomRepository.save(classRoom);
        return new ResponseEntity<>("Successfully upload post", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> addNotice(NoticeReqDto noticeReqDto) {
        ClassRoomNotice notice=ClassRoomMappingModel.noticeDtoToEntity(noticeReqDto);
        noticeRepository.save(notice);
        return new ResponseEntity<>("Successfully create notice",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<NoticeResDto>> getAllNotice(Long classId) {
        List<ClassRoomNotice> notice=noticeRepository.findAll();
        List<NoticeResDto> noticeRes=notice.stream().map(ClassRoomMappingModel::noticeEntityToDto).toList();
        return new ResponseEntity<>(noticeRes,HttpStatus.OK);
    }
}

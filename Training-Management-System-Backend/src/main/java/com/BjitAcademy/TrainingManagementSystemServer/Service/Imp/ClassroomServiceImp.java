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
    public ResponseEntity<Object> addPost(ClassRoomPostReqDto postReq) {
        //converting post req dto to entity for db
        PostEntity post= ClassRoomMappingModel.postDtoToEntity(postReq);
        //finding classRoom using classRoomId
        ClassRoom classRoom=classRoomRepository.findByClassRoomId(postReq.getClassRoomId());
        if (classRoom==null){
            throw new ClassRoomNotFoundException("classRoom not found for post");
        }
        //firstly get all post from the classroom and add new post to the list
        classRoom.getPosts().add(postRepository.save(post));
        //save the classRoom entity to classRoom
        classRoomRepository.save(classRoom);
        return new ResponseEntity<>("Successfully upload post", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> addNotice(NoticeReqDto noticeReqDto) {
        //converting NOTICE req dto to entity for db
        ClassRoomNotice notice=ClassRoomMappingModel.noticeDtoToEntity(noticeReqDto);
        //save the notice in notice repository
        noticeRepository.save(notice);
        return new ResponseEntity<>("Successfully create notice",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<NoticeResDto>> getAllNotice(Long classId) {
        //get all the notice
        List<ClassRoomNotice> notice=noticeRepository.findAll();
        //convert the entity to response dto using mapper class named ClassRoomMappingModel
        List<NoticeResDto> noticeRes=notice.stream().map(ClassRoomMappingModel::noticeEntityToDto).toList();
        return new ResponseEntity<>(noticeRes,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> addComment(PostCommentReqDto comment) {
        //checking post is exist or not using post Id
        PostEntity existPost=postRepository.findByPostId(comment.getPostId());
        if ((existPost==null)){
            throw new ClassRoomNotFoundException("post Not found for delete");
        }
        //converting comment dto to entity
        PostComment newComment=ClassRoomMappingModel.commentDtoEntity(comment);
       //collecting post ... inside post there is comment list,,then add the comment to the list
        existPost.getPostComments().add(postCommentRepository.save(newComment));
       //save the post in post repository
        postRepository.save(existPost);
        return new ResponseEntity<>("SuccessFully comment",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> updateComment(Long commentId, PostCommentReqDto comment) {
        //checking post is exist or not?
        PostComment existComment=postCommentRepository.findByCommentId(commentId);
        if (existComment==null){
            throw new ClassRoomNotFoundException("comment can not updated");
        }
        //set the msg for update
        existComment.setMsg(comment.getMsg());
        //save it postCommentRepository
        postCommentRepository.save(existComment);
        return new ResponseEntity<>("update post comment ",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> updatePost(Long postId, ClassRoomPostReqDto post) {
        //checking post is exist or not?
        PostEntity existPost=postRepository.findByPostId(postId);
        if ((existPost==null)){
            throw new ClassRoomNotFoundException("post Not found for delete");
        }
        //set the msg for update
        existPost.setMsg(post.getMsg());
        existPost.setPostFile(post.getPostFile());
        //save it postRepository
        postRepository.save(existPost);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> removePost(Long postId) {
        //checking post is exist or not?
        PostEntity post=postRepository.findByPostId(postId);
        if ((post==null)){
            throw new ClassRoomNotFoundException("post Not found for delete");
        }
        //delete the entity from the repository
        postRepository.delete(post);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }

}

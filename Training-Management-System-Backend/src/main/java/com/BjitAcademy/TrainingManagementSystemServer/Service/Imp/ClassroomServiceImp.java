package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.ClassRoomNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TrainerNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.BatchMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ClassRoomMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ScheduleMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private final TrainerRepository trainerRepository;
    private final ScheduleRepository scheduleRepository;
    private final BatchesRepository batchesRepository;

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
    public ResponseEntity<Object> addComment(PostCommentReqDto comment) {
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
    public ResponseEntity<Object> updateComment(Long commentId, PostCommentReqDto comment) {
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
    public ResponseEntity<Object> updatePost(Long postId, ClassRoomPostReqDto post) {
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
    public ResponseEntity<Object> removePost(Long postId) {
        //checking post is exist or not?
        PostEntity post=postRepository.findByPostId(postId);
        if ((post==null)){
            throw new ClassRoomNotFoundException("post Not found for delete");
        }
        //delete the entity from the repository
        postRepository.delete(post);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> removeComment(Long postId,Long commentId) {
        //checking comment is exist or not?
        PostComment existComment=postCommentRepository.findByCommentId(commentId);
        if (existComment==null){
            throw new ClassRoomNotFoundException("comment can not deleted");
        }
        //checking post is exist or not?
        PostEntity postEntity=postRepository.findByPostId(postId);
        if(postEntity==null){
            throw new ClassRoomNotFoundException("comment can not deleted");
        }
        //post has list of comment . then remove the comment from the list
        postEntity.getPostComments().remove(existComment);
        //remove the comment from the comment repository
        postCommentRepository.delete(existComment);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<ClassRoomPostResDto>> getAllPost(Long classId) {
        //get All the post
        List<PostEntity> posts=postRepository.findAll();
        //get all the comment for specifics post and convert it response dto using mapper class named ClassRoomMappingModel
        Set<ClassRoomPostResDto> allComments= posts.stream().map(postEntity -> {
            //convert comment to post comment res dto
            Set<PostCommentResDto> comments=postEntity.getPostComments().stream().map(ClassRoomMappingModel::commentEntityToDto).collect(Collectors.toSet());
            return ClassRoomPostResDto.builder()
                    .postId(postEntity.getPostId())
                    .msg(postEntity.getMsg())
                    .trainerId(postEntity.getTrainerId())
                    .postFile(postEntity.getPostFile())
                    .classRoomId(postEntity.getClassRoomId())
                    .comments(comments).build();
        }).collect(Collectors.toSet());
        return new ResponseEntity<>(allComments,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<BatchResDto>> getAllTrainerClass(Long trainerId) {
        //checking trainer is exist or not?
        TrainerEntity trainer=trainerRepository.findByTrainerId(trainerId);
        if (trainer==null){
            throw new TrainerNotFoundException("Trainer are not found for ClassRoom");
        }
        //find the schedule for specific trainer
        List<ScheduleResDto> scheduleEntities=scheduleRepository.findAllByTrainerId(trainerId).stream().map(ScheduleMappingModel::scheduleEntityToDto).toList();
        //initialize hash set for output
        Set<BatchResDto> classRooms=new HashSet<>();
        //initialize hash set for unique batch
        Set<Long> batches=new HashSet<>();
        for (ScheduleResDto schedule:scheduleEntities){
            //checking batch is unique or not?
            if(batches.add(schedule.getBatchId())){
                // finding batch for response details for UI
                BatchEntity batch=batchesRepository.findByBatchId(schedule.getBatchId());
                classRooms.add(BatchMappingModel.BatchEntityToDto(batch));
            }
        }
        return  new ResponseEntity<>(classRooms,HttpStatus.OK);
    }

}

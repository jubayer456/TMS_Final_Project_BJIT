package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.ClassRoomException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TrainerException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.BatchMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ClassRoomMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ScheduleMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    @Transactional
    public ResponseEntity<Object> addPost(ClassRoomPostReqDto postReq) {
        //finding classRoom using classRoomId
        ClassRoom classRoom=classRoomRepository.findByClassRoomId(postReq.getClassRoomId());
        if (classRoom==null){
            throw new ClassRoomException("classRoom not found for post");
        }
        //converting post req dto to entity for db
        PostEntity post= ClassRoomMappingModel.postDtoToEntity(postReq);
        //firstly get all post from the classroom and add new post to the list
        classRoom.getPosts().add(postRepository.save(post));
        //save the classRoom entity to classRoom
        classRoomRepository.save(classRoom);
       //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Post To the ClassRoom")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<Object> addNotice(NoticeReqDto noticeReqDto) {
        //checking classroom is present or not?
        ClassRoom classRoom=classRoomRepository.findByClassRoomId(noticeReqDto.getClassRoomId());
        if (classRoom==null){
            throw new ClassRoomException("ClassRoom is not found for notice");
        }
        //converting NOTICE req dto to entity for db
        ClassRoomNotice notice=ClassRoomMappingModel.noticeDtoToEntity(noticeReqDto);
        //save the notice in classRoom notice List
        classRoom.getClassRoomNotice().add(noticeRepository.save(notice));
        //save the updated classRoom in classRoom Repository
        classRoomRepository.save(classRoom);
        //give success msg to UI with status code
        SuccessResponseDto success= SuccessResponseDto.builder()
                .msg("Successfully create notice")
                .status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<NoticeResDto>> getAllNotice(Long classId) {
        //get all the notice for classroom
        List<ClassRoomNotice> notice=classRoomRepository.findByClassRoomId(classId).getClassRoomNotice();
        //convert the entity to response dto using mapper class named ClassRoomMappingModel
        List<NoticeResDto> noticeRes=notice.stream().map(ClassRoomMappingModel::noticeEntityToDto).toList();
        return new ResponseEntity<>(noticeRes,HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<Object> addComment(PostCommentReqDto comment) {
        //checking post is exist or not using post Id
        PostEntity existPost=postRepository.findByPostId(comment.getPostId());
        if ((existPost==null)){
            throw new ClassRoomException("post Not found for delete");
        }
        //converting comment dto to entity
        PostComment newComment=ClassRoomMappingModel.commentDtoEntity(comment);
       //collecting post ... inside post there is comment list,,then add the comment to the list
        existPost.getPostComments().add(postCommentRepository.save(newComment));
       //save the post in post repository
        postRepository.save(existPost);
        //give success msg to UI with status code
        SuccessResponseDto success= SuccessResponseDto.builder()
                .msg("Successfully create Comment")
                .status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> updateComment(Long commentId, PostCommentReqDto comment) {
        //checking post is exist or not?
        PostComment existComment=postCommentRepository.findByCommentId(commentId);
        if (existComment==null){
            throw new ClassRoomException("comment can not updated");
        }
        if (!Objects.equals(existComment.getTraineeId(), comment.getTraineeId())){
            throw new TrainerException("Trainee has no access to updated");
        }
        //set the msg for update
        existComment.setMsg(comment.getMsg());
        //save it postCommentRepository
        postCommentRepository.save(existComment);
        //give success msg to UI with status code
        SuccessResponseDto success= SuccessResponseDto.builder()
                .msg("update post comment ")
                .status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> updatePost(Long postId, ClassRoomPostReqDto post) {
        //checking post is exist or not?
        PostEntity existPost=postRepository.findByPostId(postId);
        if ((existPost==null)){
            throw new ClassRoomException("post Not found for delete");
        }
        if (!Objects.equals(existPost.getTrainerId(), post.getTrainerId())){
            throw new TrainerException("Trainer has no access to updated");
        }
        //set the msg for update
        existPost.setMsg(post.getMsg());
        //save it postRepository
        postRepository.save(existPost);
        //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Updated Post")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> removePost(Long postId,Long trainerId) {
        //checking post is exist or not?
        PostEntity post=postRepository.findByPostId(postId);
        if ((post==null)){
            throw new ClassRoomException("post Not found for delete");
        }
        if (!Objects.equals(post.getTrainerId(), trainerId)){
            throw new TrainerException("Trainer has no access to updated other post");
        }
        //delete the entity from the repository
        postRepository.delete(post);
        //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Deleted Post")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<Object> removeComment(Long postId,Long commentId,Long userId) {
        //checking comment is exist or not?
        PostComment existComment=postCommentRepository.findByCommentId(commentId);
        if (existComment==null){
            throw new ClassRoomException("comment can not deleted");
        }
        //checking post is exist or not?
        PostEntity postEntity=postRepository.findByPostId(postId);
        if(postEntity==null){
            throw new ClassRoomException("comment can not deleted");
        }
        if (!Objects.equals(existComment.getTraineeId(), userId)){
            throw new TrainerException("User has no access to remove other comment");
        }
        //post has list of comment . then remove the comment from the list
        postEntity.getPostComments().remove(existComment);
        //remove the comment from the comment repository
        postCommentRepository.delete(existComment);
        //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Deleted Comment")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClassRoomPostResDto>> getAllPost(Long classId) {
        //get All the post
        List<PostEntity> posts=postRepository.findAll();
        //get all the comment for specifics post and convert it response dto using mapper class named ClassRoomMappingModel
        List<ClassRoomPostResDto> allComments= posts.stream().map(postEntity -> {
            //convert comment to post comment res dto
            List<PostCommentResDto> comments=postEntity.getPostComments().stream().map(ClassRoomMappingModel::commentEntityToDto).toList();
            return ClassRoomMappingModel.postEntityToDto(postEntity,comments);
            }).toList();
        return new ResponseEntity<>(allComments,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<BatchResDto>> getAllTrainerClass(Long trainerId) {
        //checking trainer is exist or not?
        TrainerEntity trainer=trainerRepository.findByTrainerId(trainerId);
        if (trainer==null){
            throw new TrainerException("Trainer are not found for ClassRoom");
        }
        //find the schedule for specific trainer
        List<ScheduleResDto> scheduleEntities=scheduleRepository.findAllByTrainerId(trainerId).stream()
                .map(ScheduleMappingModel::scheduleEntityToDto).toList();
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

    @Override
    public ResponseEntity<ClassRoomResponseDto> getClassRoomDetails(Long classroomId) {
        ClassRoom classRoom=classRoomRepository.findByClassRoomId(classroomId);
        if (classRoom==null){
            throw new ClassRoomException("ClassRoom are Not found");
        }
        List<PostEntity> posts=classRoom.getPosts();
        //get all the comment for specifics post and convert it response dto using mapper class named ClassRoomMappingModel
        List<ClassRoomPostResDto> allPosts= posts.stream().map(postEntity -> {
            //convert comment to post comment res dto
            List<PostCommentResDto> comments=postEntity.getPostComments().stream().map(ClassRoomMappingModel::commentEntityToDto).toList();
            return ClassRoomMappingModel.postEntityToDto(postEntity,comments);
        }).toList();

        List<ClassRoomNotice> notice=classRoom.getClassRoomNotice();
        //convert the entity to response dto using mapper class named ClassRoomMappingModel
        List<NoticeResDto> noticeRes=notice.stream().map(ClassRoomMappingModel::noticeEntityToDto).toList();
        //passing the notice and all post dto as a parameter of ClassRoomMapping model which is converting classRoomResponse Model for UI
        ClassRoomResponseDto classRoomResponse=ClassRoomMappingModel.classRoomEntityToDto(classRoom,allPosts,noticeRes);
        return new ResponseEntity<>(classRoomResponse,HttpStatus.OK);
    }
}

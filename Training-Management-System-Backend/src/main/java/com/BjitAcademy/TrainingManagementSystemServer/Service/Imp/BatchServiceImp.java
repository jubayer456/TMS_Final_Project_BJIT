package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchTraineeReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.BatchScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.*;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.BatchMappingModel;

import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ScheduleMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BatchServiceImp implements BatchService {
    private final BatchesRepository batchesRepository;
    private final TraineeRepository traineeRepository;
    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;
    private final ClassRoomRepository classRoomRepository;
    @Override
    public ResponseEntity<Object> createBatch(BatchReqDto batchReqDto) {
        BatchEntity batchByName=batchesRepository.findByBatchName(batchReqDto.getBatchName());
        if (batchByName!=null){
            throw new UserAlreadyExistException("Already created Batch in this name... please insert new batch name");
        }

        //for checking date ,,, using date formatter
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //checking valid starting date and ending date
        if(LocalDate.parse((CharSequence) batchReqDto.getStartingDate(),dateTimeFormatter).isAfter(LocalDate.parse((CharSequence) batchReqDto.getEndingDate(),dateTimeFormatter))){
            throw new ScheduleNotFoundException("please enter a valid date range");
        }
        //mapping batch req dto to entity using batch mapper model
        BatchEntity batch= BatchMappingModel.BatchDtoToEntity(batchReqDto);
        batch.setClassRoom( classRoomRepository.save(new ClassRoom()));

        //saving batch entity to batch repository
        batchesRepository.save(batch);

        //showing backend msg to frontend using success object
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Batch Created")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateBatch(Long batchId, BatchReqDto batchReqDto) {
        BatchEntity batch=batchesRepository.findByBatchId(batchId);
        if (batch==null){
            throw new BatchNotFoundException("Batch is Not Found");
        }
        batch.setBatchName(batchReqDto.getBatchName());
        batch.setStartingDate(batchReqDto.getStartingDate());
        batch.setEndingDate(batchReqDto.getStartingDate());
        batch.setTotalNumOfTrainee(batchReqDto.getTotalNumOfTrainee());
        batchesRepository.save(batch);

        //showing backend msg to frontend using success object
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Batch updated")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BatchResDto>> getAllBatch() {
        List<BatchEntity> batches=batchesRepository.findAll();

        //batch entity to batch response dto ,,, for showing UI
        List<BatchResDto> batchResDtoList=batches.stream().map(BatchMappingModel::BatchEntityToDto).toList();
        return new ResponseEntity<>(batchResDtoList,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> addTraineeToBatch(BatchTraineeReqDto batchTraineeReqDto) {
        BatchEntity batch=batchesRepository.findByBatchId(batchTraineeReqDto.getBatchId());
        if(batch==null){
            throw new BatchNotFoundException("Batch not Found");
        }
        TraineeEntity trainee=traineeRepository.findByTraineeId(batchTraineeReqDto.getTraineeId());
        if(trainee==null){
            throw new TraineeNotFoundException("Trainee not found");
        }
        //checking trainee already assigned another batch?
        if (trainee.getBatchId()!=null && !trainee.getBatchId().equals(batch.getBatchId())){
            throw new TraineeAlreadyExistException("Trainee is already inserted "+ batch.getBatchName()+" Batch");
        }
        //checking trainee already assigned current batch where I want to assign?
        if (trainee.getBatchId() != null ){
            throw new TraineeAlreadyExistException("Trainee is already inserted this batch Batch");
        }
        //set batch id to trainee table
        trainee.setBatchId(batch.getBatchId());
        //add to batch the trainee entity
        batch.getTrainees().add(trainee);
        //save the batch entity to batch repo
        batchesRepository.save(batch);
        //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully add trainee to batch")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeTraineeFromBatch(Long traineeId) {
        TraineeEntity trainee=traineeRepository.findByTraineeId(traineeId);
        if (trainee==null ||trainee.getBatchId()==null){
            throw new TraineeNotFoundException("Trainee Are not found for Delete");
        }
        //find batch using traineeId
        BatchEntity batch=batchesRepository.findByBatchId(trainee.getBatchId());
        //remove  trainee from batch
        batch.getTrainees().remove(trainee);
        //set the trainee batch status null
        trainee.setBatchId(null);
        //save updated trainee entity to trainee Repository
        traineeRepository.save(trainee);
        //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("remove trainee to Batch")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addScheduleToBatch(ScheduleReqDto scheduleReqDto) {
        BatchEntity batch=batchesRepository.findByBatchId(scheduleReqDto.getBatchId());
        if(batch==null){
            throw new BatchNotFoundException("Batch not found for Insert Scheduling");
        }
        CourseEntity course=courseRepository.findByCourseId(scheduleReqDto.getCourseId());
        if(course==null){
            throw new CourseNotFoundException("Course are not found for Scheduling");
        }
        //for checking date ,,, using date formatter
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //checking valid starting date and ending date
        if((LocalDate.parse((CharSequence) batch.getStartingDate(),dateTimeFormatter).isAfter(LocalDate.parse((CharSequence) scheduleReqDto.getEndingDate(),dateTimeFormatter)) )||
                (LocalDate.parse((CharSequence)scheduleReqDto.getEndingDate(),dateTimeFormatter).isAfter(LocalDate.parse((CharSequence)  batch.getEndingDate(),dateTimeFormatter))
                )){
            throw new ScheduleNotFoundException("please enter a valid date range");
        }
        ScheduleEntity existSchedule=scheduleRepository.findByBatchIdAndCourseId(scheduleReqDto.getBatchId(),scheduleReqDto.getCourseId());
        if (existSchedule!=null){
            throw new ScheduleNotFoundException("Course is already exist in Batch,,,, try to insert new course");
        }
        ScheduleEntity newSchedule=scheduleRepository.save(ScheduleMappingModel.scheduleDtoToEntity(scheduleReqDto,course));
        batch.getSchedules().add(newSchedule);
        batchesRepository.save(batch);
        //give success msg to UI with status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully add schedule to batch")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeScheduleFromBatch(Long scheduleId) {
        ScheduleEntity existSchedule=scheduleRepository.findByScheduleId(scheduleId);
        if (existSchedule==null){
            throw new ScheduleNotFoundException("Schedule is not found for Delete");
        }
        BatchEntity batch=batchesRepository.findByBatchId(existSchedule.getBatchId());
        batch.getSchedules().remove(existSchedule);
        scheduleRepository.delete(existSchedule);
        batchesRepository.save(batch);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully remove Schedule")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Set<BatchScheduleResDto>> getAllBatchSchedule(Long batchId) {
        BatchEntity batch=batchesRepository.findByBatchId(batchId);
        if (batch==null){
            throw new BatchNotFoundException("Batch not found for to show all schedules details");
        }
        Set<ScheduleEntity> schedules=batch.getSchedules();

        Set<BatchScheduleResDto> batchSchedules=schedules.stream().map(schedule->{
            CourseEntity course=courseRepository.findByCourseId(schedule.getCourseId());
            return BatchMappingModel.scheduleEntityToBatchRes(schedule,course,batchId);

        }).collect(Collectors.toSet());
        return new ResponseEntity<>(batchSchedules,HttpStatus.OK);
    }
}

package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.*;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.BatchMappingModel;

import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BatchServiceImp implements BatchService {
    private final BatchesRepository batchesRepository;
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
}

package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;

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
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(LocalDate.parse((CharSequence) batchReqDto.getStartingDate(),dateTimeFormatter).isAfter(LocalDate.parse((CharSequence) batchReqDto.getEndingDate(),dateTimeFormatter))){
            throw new ScheduleNotFoundException("please enter a valid date range");
        }
        BatchEntity batch= BatchMappingModel.BatchDtoToEntity(batchReqDto);
        batch.setClassRoom( classRoomRepository.save(new ClassRoom()));
        batchesRepository.save(batch);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully Batch Created")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}

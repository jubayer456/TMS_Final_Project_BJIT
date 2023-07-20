package com.BjitAcademy.TrainingManagementSystemServer.Repository;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ScheduleEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long>{
    ScheduleEntity findByCourseId(Long courseId);

    ScheduleEntity findByScheduleId(Long scheduleId);

    ScheduleEntity findByBatchIdAndCourseId(Long batchId, Long courseId);

    List<ScheduleEntity> findByTrainerId(Long trainerId);

    List<ScheduleEntity> findAllByTrainerId(Long trainerId);
}

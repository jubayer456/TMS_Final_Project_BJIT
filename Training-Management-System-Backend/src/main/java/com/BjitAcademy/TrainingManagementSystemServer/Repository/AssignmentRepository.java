package com.BjitAcademy.TrainingManagementSystemServer.Repository;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity,Long> {
    AssignmentEntity findByAssignmentId(Long assignmentId);
    Set<AssignmentEntity> findAllByBatchId(Long batchId);
}

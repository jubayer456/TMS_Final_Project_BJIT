package com.BjitAcademy.TrainingManagementSystemServer.Repository;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.AssignmentSubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentSubRepository extends JpaRepository<AssignmentSubEntity,Long> {
    AssignmentSubEntity findBySubmissionFile(String fileName);

    AssignmentSubEntity findByTraineeId(Long traineeId);
    AssignmentSubEntity findByTraineeIdAndAssignmentId(Long traineeId,Long assignmentId);

    AssignmentSubEntity findByAsgSubId(Long submissionId);
}

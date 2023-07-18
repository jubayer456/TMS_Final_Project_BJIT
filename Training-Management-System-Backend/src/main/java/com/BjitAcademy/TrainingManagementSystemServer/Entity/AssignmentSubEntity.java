package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "assignmentSub")
public class AssignmentSubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asgSubId;
    private Long assignmentId;
    private Long traineeId;
    private Long batchId;
    private String submissionDate;
    private String submissionFile;
    private Long evolution;
}

package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "assignment")
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assignmentId;
    private Long scheduleId;
    private Long batchId;
    private String assignmentName;
    private String assignmentFile;
    private String deadLine;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<AssignmentSubEntity> assignmentSubEntities;
}

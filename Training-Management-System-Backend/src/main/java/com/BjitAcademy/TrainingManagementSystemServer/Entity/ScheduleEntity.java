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
@Entity
@Table(name = "course_schedule")
@Builder
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;

    private String startingDate;
    private String endingDate;
    private Long courseId;
    private Long batchId;
    private Long trainerId;
    @OneToMany( cascade = CascadeType.ALL)
    private Set<AssignmentEntity> assignments;
}

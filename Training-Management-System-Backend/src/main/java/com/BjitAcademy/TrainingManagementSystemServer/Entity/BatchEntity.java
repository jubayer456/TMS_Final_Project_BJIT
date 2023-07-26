package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "batches")
public class BatchEntity {
    @Id
    private Long batchId;
    private String batchName;
    private String startingDate;
    private String endingDate;
    private Integer totalNumOfTrainee;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TraineeEntity> trainees;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BATCH_SCHEDULE",
            joinColumns = {
                    @JoinColumn(name = "batch_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "schedule_id")
            }
    )
    private Set<ScheduleEntity> schedules;

    @OneToOne(cascade = CascadeType.ALL)
    private ClassRoom classRoom;
}

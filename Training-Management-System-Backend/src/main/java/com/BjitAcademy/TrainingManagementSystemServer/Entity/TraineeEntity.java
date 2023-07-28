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
@Entity
@Table(name = "Trainees")
@Builder
public class TraineeEntity{
    @Id
    private Long traineeId;
    private String address;
    private String dob;
    private String degreeName;
    private String educationalInstitute;
    private Integer passingYear;
    private Double cgpa;
    private Long batchId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserEntity user;
}

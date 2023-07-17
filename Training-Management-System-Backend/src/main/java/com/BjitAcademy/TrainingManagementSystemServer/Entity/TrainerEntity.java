package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Trainers")
@Data
@Builder
public class TrainerEntity {
    @Id
    private Long trainerId;

    private String address;
    private String designation;
    private String joiningDate;
    private Integer totalYrsExp;
    private String expertises;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserEntity user;
}

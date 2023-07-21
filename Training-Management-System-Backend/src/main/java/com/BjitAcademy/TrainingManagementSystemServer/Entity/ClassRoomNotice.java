package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classRoomNotice")
@Data
@Builder
public class ClassRoomNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noticeId;
    private Long trainerId;
    private String trainerName;
    private String title;
    private String description;
    private Long classRoomId;
}

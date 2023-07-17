package com.BjitAcademy.TrainingManagementSystemServer.Dto.Course;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.TrainerEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseReqDto {
    private String name;
    private Long trainerId;
}

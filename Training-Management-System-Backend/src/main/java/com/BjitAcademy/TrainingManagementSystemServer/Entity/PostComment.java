package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "postComment")
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private Long traineeId;
    private String msg;
    private Long postId;
}

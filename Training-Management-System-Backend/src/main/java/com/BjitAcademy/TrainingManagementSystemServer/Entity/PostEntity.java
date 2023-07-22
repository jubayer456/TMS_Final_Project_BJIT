package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private Long classRoomId;
    private Long trainerId;
    private String trainerName;
    private String profilePicture;
    private String postDate;
    private String msg;
    private String postFile;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PostComment> postComments;
}

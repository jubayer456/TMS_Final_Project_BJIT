package com.BjitAcademy.TrainingManagementSystemServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "classRoom")
public class ClassRoom {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "classRoomId")
    private Long classRoomId;
    private String classRoomName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PostEntity> posts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClassRoomNotice> classRoomNotice;
}

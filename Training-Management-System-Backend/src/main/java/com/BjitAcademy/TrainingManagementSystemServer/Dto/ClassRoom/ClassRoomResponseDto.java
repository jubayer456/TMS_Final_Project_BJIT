package com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoomNotice;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.PostEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassRoomResponseDto {

    private Long classRoomId;
    private String classRoomName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ClassRoomPostResDto> posts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<NoticeResDto> classRoomNotice;
}

package com.BjitAcademy.TrainingManagementSystemServer.Repository;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.ClassRoomNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<ClassRoomNotice,Long> {
}

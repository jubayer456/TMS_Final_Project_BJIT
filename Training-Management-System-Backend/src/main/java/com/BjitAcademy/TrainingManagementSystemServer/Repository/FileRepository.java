package com.BjitAcademy.TrainingManagementSystemServer.Repository;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.FileDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileDataEntity,Long> {
    Optional<FileDataEntity> findByName(String fileName);
}

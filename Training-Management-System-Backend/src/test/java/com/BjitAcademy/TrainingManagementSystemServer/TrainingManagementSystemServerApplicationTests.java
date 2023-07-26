package com.BjitAcademy.TrainingManagementSystemServer;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.BatchEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.ScheduleException;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.Imp.BatchServiceImp;
import com.BjitAcademy.TrainingManagementSystemServer.Service.Imp.TraineeServiceImp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserException;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TrainingManagementSystemServerApplicationTests {
    @Test
	public void testCreateTrainee_Success() {
		// Mocking the repositories and password encoder
		UserRepository userRepository = mock(UserRepository.class);
		TraineeRepository traineeRepository = mock(TraineeRepository.class);
		BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);

		// Create a TraineeService instance and inject the mocked repositories and encoder
		TraineeServiceImp traineeService = new TraineeServiceImp( traineeRepository,userRepository, passwordEncoder);

		// Create a test trainee request DTO
		TraineeRegReqDto traineeRegReqDto = TraineeRegReqDto.builder()
				.traineeId(1L)
				.email("test@example.com")
				.password("testPassword")
				.fullName("Test Trainee")
				.gender("Male")
				.contactNumber("1234567890")
				.profilePicture("test.jpg")
				.build();

		// Mock the behavior of userRepository.findByUserId and userRepository.findByEmail
		when(userRepository.findByUserId(1L)).thenReturn(null);
		when(userRepository.findByEmail("test@example.com")).thenReturn(null);

		// Mock the behavior of password encoder
		when(passwordEncoder.encode("testPassword")).thenReturn("hashedPassword");

		// Perform the unit test
		ResponseEntity<Object> response = traineeService.createTrainee(traineeRegReqDto);

		// Verify the save method is called with correct TraineeEntity instance
		verify(traineeRepository).save(argThat(traineeEntity ->
				traineeEntity.getTraineeId().equals(1L) &&
						traineeEntity.getUser().getUserId().equals(1L) &&
						traineeEntity.getDob() == null && // set dob as null in DTO
						traineeEntity.getCgpa() == null && // set cgpa as null in DTO
						traineeEntity.getAddress() == null && // set address as null in DTO
						traineeEntity.getEducationalInstitute() == null && // set educationalInstitute as null in DTO
						traineeEntity.getPassingYear() == null && // set passingYear as null in DTO
						traineeEntity.getDegreeName() == null // set degreeName as null in DTO
		));

		// Verify the response status and message
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("successfully registered", ((SuccessResponseDto)response.getBody()).getMsg());
	}

	@Test
	public void testCreateTrainee_TraineeIdAlreadyTaken() {
		// Mocking the repositories and password encoder
		UserRepository userRepository = mock(UserRepository.class);
		TraineeRepository traineeRepository = mock(TraineeRepository.class);
		BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);

		// Create a TraineeService instance and inject the mocked repositories and encoder
		TraineeServiceImp traineeService = new TraineeServiceImp(traineeRepository,userRepository, passwordEncoder);

		// Create a test trainee request DTO
		TraineeRegReqDto traineeRegReqDto = TraineeRegReqDto.builder()
				.traineeId(1L)
				.email("test@example.com")
				.password("testPassword")
				.fullName("Test Trainee")
				.gender("Male")
				.contactNumber("1234567890")
				.profilePicture("test.jpg")
				.build();

		// Mock the behavior of userRepository.findByUserId to return a UserEntity
		when(userRepository.findByUserId(1L)).thenReturn(new UserEntity());

		// Perform the unit test and assert that UserException is thrown with the correct message
		Assertions.assertThrows(UserException.class, () -> traineeService.createTrainee(traineeRegReqDto), "TraineeId is Already taken. Please enter a new trainee Id");
	}

	@Test
	public void testCreateTrainee_UserAlreadyExist() {
		// Mocking the repositories and password encoder
		UserRepository userRepository = mock(UserRepository.class);
		TraineeRepository traineeRepository = mock(TraineeRepository.class);
		BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);

		// Create a TraineeService instance and inject the mocked repositories and encoder
		TraineeServiceImp traineeService = new TraineeServiceImp(traineeRepository,userRepository,passwordEncoder);

		// Create a test trainee request DTO
		TraineeRegReqDto traineeRegReqDto = TraineeRegReqDto.builder()
				.traineeId(1L)
				.email("test@example.com")
				.password("testPassword")
				.fullName("Test Trainee")
				.gender("Male")
				.contactNumber("1234567890")
				.profilePicture("test.jpg")
				.build();

		// Mock the behavior of userRepository.findByEmail to return a UserEntity
		when(userRepository.findByUserId(1L)).thenReturn(null);
		when(userRepository.findByEmail("test@example.com")).thenReturn(new UserEntity());

		// Perform the unit test and assert that UserException is thrown with the correct message
		Assertions.assertThrows(UserException.class, () -> traineeService.createTrainee(traineeRegReqDto), "User Already Exist.. Please Change the email");
	}


	@Test
	public void testCreateBatch_Success() {
		// Mocking the repositories
		BatchesRepository batchRepository = mock(BatchesRepository.class);
		ClassRoomRepository classRoomRepository = mock(ClassRoomRepository.class);
		TraineeRepository traineeRepository = mock(TraineeRepository.class);
		CourseRepository courseRepository = mock(CourseRepository.class);
		ScheduleRepository scheduleRepository = mock(ScheduleRepository.class);

		// Create a BatchService instance and inject the mocked repositories
		BatchServiceImp batchService = new BatchServiceImp(batchRepository, traineeRepository, courseRepository, scheduleRepository, classRoomRepository);

		// Create a test batch request DTO
		BatchReqDto batchReqDto = BatchReqDto.builder()
				.batchId(1L)
				.batchName("Test Batch")
				.startingDate("2023-07-01")
				.endingDate("2023-07-30")
				.totalNumOfTrainee(20)
				.build();

		// Mock the behavior of batchRepository.findByBatchName to return null (batch name doesn't exist)
		when(batchRepository.findByBatchName("Test Batch")).thenReturn(null);

		// Perform the unit test
		ResponseEntity<Object> response = batchService.createBatch(batchReqDto);

		// Verify the save method is called with the correct BatchEntity instance
		verify(batchRepository).save(argThat(batchEntity ->
				batchEntity.getBatchName().equals("Test Batch") &&
						batchEntity.getStartingDate().equals("2023-07-01") &&
						batchEntity.getEndingDate().equals("2023-07-30") &&
						batchEntity.getTotalNumOfTrainee().equals(20)
		));

		// Verify the classRoomRepository.save method is called with the correct ClassRoom instance
		verify(classRoomRepository).save(argThat(classRoom ->
				classRoom.getClassRoomId() != null &&
						classRoom.getClassRoomName().equals("Test Batch")
		));

		// Verify the response status and message
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("Successfully Batch Created", ((SuccessResponseDto)response.getBody()).getMsg());
	}

	@Test
	public void testCreateBatch_BatchNameAlreadyExists() {
		// Mocking the repositories
		BatchesRepository batchRepository = mock(BatchesRepository.class);
		ClassRoomRepository classRoomRepository = mock(ClassRoomRepository.class);
		TraineeRepository traineeRepository=mock(TraineeRepository.class);
		CourseRepository courseRepository=mock(CourseRepository.class);
		ScheduleRepository scheduleRepository=mock(ScheduleRepository.class);

		// Create a BatchService instance and inject the mocked repositories
		BatchServiceImp batchService = new BatchServiceImp(batchRepository,traineeRepository,courseRepository,scheduleRepository,classRoomRepository);

		// Create a test batch request DTO
		BatchReqDto batchReqDto = BatchReqDto.builder()
				.batchName("Test Batch")
				.startingDate("2023-07-01")
				.endingDate("2023-07-30")
				.totalNumOfTrainee(20)
				.build();

		// Mock the behavior of batchRepository.findByBatchName to return a BatchEntity (batch name already exists)
		when(batchRepository.findByBatchName("Test Batch")).thenReturn(new BatchEntity());

		// Perform the unit test and assert that UserException is thrown with the correct message
		Assertions.assertThrows(UserException.class, () -> batchService.createBatch(batchReqDto), "Already created Batch in this name... please insert a new batch name");
	}

	@Test
	public void testCreateBatch_InvalidDateRange() {
		// Mocking the repositories
		BatchesRepository batchRepository = mock(BatchesRepository.class);
		ClassRoomRepository classRoomRepository = mock(ClassRoomRepository.class);
		TraineeRepository traineeRepository=mock(TraineeRepository.class);
		CourseRepository courseRepository=mock(CourseRepository.class);
		ScheduleRepository scheduleRepository=mock(ScheduleRepository.class);

		// Create a BatchService instance and inject the mocked repositories
		BatchServiceImp batchService = new BatchServiceImp(batchRepository,traineeRepository,courseRepository,scheduleRepository,classRoomRepository);

		// Create a test batch request DTO with invalid date range (end date before start date)
		BatchReqDto batchReqDto = BatchReqDto.builder()
				.batchName("Test Batch")
				.startingDate("2023-07-30")
				.endingDate("2023-07-01")
				.totalNumOfTrainee(20)
				.build();

		// Perform the unit test and assert that ScheduleException is thrown with the correct message
		Assertions.assertThrows(ScheduleException.class, () -> batchService.createBatch(batchReqDto), "Please enter a valid date range");
	}

}

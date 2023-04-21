package com.festus.login.service;

import com.festus.login.config.mongoDb.SequenceGeneratorService;
import com.festus.login.error.exception.ConflictRequestException;
import com.festus.login.error.exception.ResourceNotFoundException;
import com.festus.login.model.Roles;
import com.festus.login.model.Student;
import com.festus.login.repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepo studentRepo;
    @Mock
    SequenceGeneratorService sequenceGeneratorService;
    @InjectMocks
    StudentService studentService;
    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepo,sequenceGeneratorService);
    }
    @Test
    void getStudents(){
        List<Student> expectedList = new ArrayList<>();
        expectedList.add(new Student());
        expectedList.add(new Student());
        when(studentRepo.findAll()).thenReturn(expectedList);

        List<Student> actualStudents = studentRepo.findAll();
        assertEquals(expectedList,actualStudents);
    }
    @Test
    void getAllWhenThereIsNone() {
        List<Student> emptyList = new ArrayList<>();
        when(studentRepo.findAll()).thenReturn(emptyList);
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getAll();
        });
        assertEquals("There are currently no students",ex.getMessage());
    }
    @Test
    void createStudent() throws ConflictRequestException {
        Student student = Student.builder()
                .studentName("Jaaja")
                .email("jaaja@opobo.com")
                .roles(Roles.ADMIN)
                .build();
        when(studentRepo.findByEmail(student.getEmail())).thenReturn(Optional.empty());
        when(sequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME)).thenReturn(1L);
        when(studentRepo.save(student)).thenReturn(student);
        Student createdStudent = studentService.createStudent(student);
        assertNotNull(createdStudent.getId());
        assertEquals(student.getEmail(),createdStudent.getEmail());
        assertEquals(student.getStudentName(),createdStudent.getStudentName());
    }
    @Test
    void createStudentsShouldFailForDuplicateEmail() {
        String email = "test@gmail.com";
        Student existingStudent = new Student();
        existingStudent.setEmail(email);
        when(studentRepo.findByEmail(email.toLowerCase())).thenReturn(Optional.of(existingStudent));
        Student student = new Student();
        student.setEmail(email);
        ConflictRequestException ex = assertThrows(ConflictRequestException.class, () -> {
            studentService.createStudent(student);
        });
        assertEquals("Email has already been taken",ex.getMessage());
    }
    @Test
    void getById() throws ResourceNotFoundException {
        Long id = 1L;
        Student existing = new Student();
        existing.setId(id);
        when(studentRepo.findById(id)).thenReturn(Optional.of(existing));
        Student byId = studentService.getById(1L);
        assertEquals(byId,existing);
    }
    @Test
    void ShouldThrowErrorWhenIdDoesNotExist(){
        when(studentRepo.findById(any())).thenReturn(Optional.empty());
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getById(2L);
        });
        assertEquals("The student with id : 2 does not exist",ex.getMessage());
    }

    @Test
    void updateStudent() {
    }
}
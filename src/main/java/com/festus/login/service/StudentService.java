package com.festus.login.service;

import com.festus.login.config.mogodb.SequenceGeneratorService;
import com.festus.login.error.exception.ConflictRequestException;
import com.festus.login.error.exception.ResourceNotFoundException;
import com.festus.login.model.Student;
import com.festus.login.model.dto.StudentDto;
import com.festus.login.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final StudentDTORowMapper studentDTORowMapper;

    public StudentService(UserRepository userRepository, SequenceGeneratorService sequenceGeneratorService, StudentDTORowMapper studentDTORowMapper) {
        this.userRepository = userRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.studentDTORowMapper = studentDTORowMapper;
    }
    public Student createStudent(Student student) throws ConflictRequestException {
        if (userRepository.findByEmail(student.getEmail().toLowerCase()).isPresent())
            throw new ConflictRequestException("Email has already been taken");
        student.setId(sequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME));
        return userRepository.save(student);
    }
    public List<StudentDto> getAll() throws  ResourceNotFoundException {
        List<Student> studentList = userRepository.findAll();
        if (studentList.size() == 0)
            throw new ResourceNotFoundException("There are currently no students");
        return studentList.stream()
                .map(studentDTORowMapper)
                .collect(Collectors.toList());
    }
    public StudentDto getById(Long id) throws ResourceNotFoundException {
       return userRepository.findById(id)
               .map(studentDTORowMapper)
               .orElseThrow(()-> new ResourceNotFoundException("The student with id : [%d] does not exist".formatted(id)));
    }
    public Student updateStudent(Long id, Student student) throws ResourceNotFoundException {
        Student oldStudent = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %d does not exist", id)));
        oldStudent.setId(id);
        oldStudent.setStudentName(student.getStudentName());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setRoles(student.getRoles());
        return userRepository.save(oldStudent);
    }
}

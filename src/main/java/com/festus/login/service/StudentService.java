package com.festus.login.service;

import com.festus.login.config.mongoDb.SequenceGeneratorService;
import com.festus.login.error.exception.ConflictRequestException;
import com.festus.login.error.exception.ResourceNotFoundException;
import com.festus.login.model.Student;
import com.festus.login.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final SequenceGeneratorService sequenceGeneratorService;

    public StudentService(StudentRepo studentRepo, SequenceGeneratorService sequenceGeneratorService) {
        this.studentRepo = studentRepo;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
    public Student createStudent(Student student) throws ConflictRequestException {
        if (studentRepo.findByEmail(student.getEmail().toLowerCase()).isPresent())
            throw new ConflictRequestException("Email has already been taken");
        student.setId(sequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME));
        return studentRepo.save(student);
    }
    public List<Student> getAll() throws  ResourceNotFoundException {
        List<Student> studentList = studentRepo.findAll();
        if (studentList.size() == 0)
            throw new ResourceNotFoundException("There are currently no students");
        return studentList;
    }
    public Student getById(Long id) throws ResourceNotFoundException {
       return studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("The student with id: "+ id +
                "does not exist"));
    }
}

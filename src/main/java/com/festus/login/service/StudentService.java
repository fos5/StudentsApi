package com.festus.login.service;

import com.festus.login.exception.ApplicationException;
import com.festus.login.exception.NotFoundException;
import com.festus.login.model.Student;
import com.festus.login.model.dto.StudentRequest;
import com.festus.login.repository.StudentRepo;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    public Student createStudent(Student student){
        return studentRepo.save(student);
    }
    public List<Student> getAll() throws ApplicationException, NotFoundException {
        List<Student> studentList = studentRepo.findAll();
        if (studentList.isEmpty())
            throw new NotFoundException("There are currently no students");
        return studentList;
    }
    public Student getById(Long id) throws NotFoundException {
       return studentRepo.findById(id).orElseThrow(()-> new NotFoundException("The student with id: "+ id +
                "does not exist"));
    }
}

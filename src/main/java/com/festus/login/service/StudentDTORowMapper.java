package com.festus.login.service;

import com.festus.login.model.Student;
import com.festus.login.model.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentDTORowMapper implements Function<Student, StudentDto> {
    @Override
    public StudentDto apply(Student student) {
       return new StudentDto(student.getId(),
                student.getStudentName(),
                student.getEmail(),
                student.getRoles());
    }
}

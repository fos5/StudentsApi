package com.festus.login.cotroller;

import com.festus.login.exception.ApplicationException;
import com.festus.login.exception.NotFoundException;
import com.festus.login.model.Student;
import com.festus.login.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){
          return   ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.createStudent(student));
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws ApplicationException, NotFoundException {
        return ResponseEntity.ok()
                .body(studentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getById(id));
    }
}

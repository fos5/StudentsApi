package com.festus.login.cotroller;

import com.festus.login.error.exception.ApplicationException;
import com.festus.login.error.exception.ConflictRequestException;
import com.festus.login.error.exception.ResourceNotFoundException;
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
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) throws ConflictRequestException {
          return   ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.createStudent(student));
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws ApplicationException, ResourceNotFoundException {
        return ResponseEntity.ok()
                .body(studentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) throws ResourceNotFoundException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.updateStudent(id,student));
    }
}

package com.festus.login.repository;

import com.festus.login.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}

package com.qrbats.qrbats.authentication.entities.student.repository;

import com.qrbats.qrbats.authentication.entities.student.Student;
import com.qrbats.qrbats.authentication.entities.student.StudentRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByUserName(String userName);
    Student findByStudentEmail(String studentEmail);
    Student findByStudentRole(StudentRole studentRole);


}

package com.example.CrudSpringBootProject.repository;

import com.example.CrudSpringBootProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

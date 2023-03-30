package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TeacherSubject;

public interface TeacherSubjectRepository extends CrudRepository<TeacherSubject, Integer> {
}

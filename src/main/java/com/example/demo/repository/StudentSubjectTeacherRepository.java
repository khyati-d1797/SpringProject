package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.studentSubTeach.StudentSubjectTeacher;
import com.example.demo.entity.studentSubTeach.StudentSubjectTeacherKey;

@Repository
public interface StudentSubjectTeacherRepository extends CrudRepository<StudentSubjectTeacher, StudentSubjectTeacherKey> {
	
	public Iterable<StudentSubjectTeacher> findByStdSubTeachKeyStuId(Integer stdId);

}

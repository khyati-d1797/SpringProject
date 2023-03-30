package com.example.demo.entity.studentSubTeach;

import com.example.demo.entity.Subject;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentSubjectTeacher {
	
	@EmbeddedId
	private StudentSubjectTeacherKey stdSubTeachKey;

	

}

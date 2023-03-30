package com.example.demo.entity.studentSubTeach;

import java.io.Serializable;

import com.example.demo.entity.Subject;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class StudentSubjectTeacherKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stuId;
	private Integer teachSubId;
	
	
	
}

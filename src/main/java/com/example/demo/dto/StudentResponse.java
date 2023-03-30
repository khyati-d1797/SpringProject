package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

	private Integer stdId = 0;
	private String StdName = "";
	
	List<SubjectResponse> subjects = new ArrayList<SubjectResponse>();
	List<TeacherResponse> teachers = new ArrayList<TeacherResponse>();
	List<String> subjectTaughtByList = new ArrayList<String>();
	List<SubjectTeacherResponse> subteachList = new ArrayList<SubjectTeacherResponse>();
	
	
}

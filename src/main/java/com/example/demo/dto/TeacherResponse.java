package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {

	private String teacherName = "";
	private String edu = "";
	
}

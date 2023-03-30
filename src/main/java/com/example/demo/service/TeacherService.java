package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TeacherResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.studentSubTeach.StudentSubjectTeacher;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepo;
	
	public TeacherResponse getTeacherById(Integer teachId) {
		
		TeacherResponse response = new TeacherResponse();
		Iterable<Teacher> teachList = teacherRepo.findAll();
		/**
		 * Getting Teacher List in Service Layer
		 
		List<String> teachers = new ArrayList<String>();
		teachList.forEach(x -> {
			teachers.add(x.getName());
		});
		System.out.println(teachers);
		*/
		teachList.forEach(x->{
			if(teachId == x.getId())
				response.setTeacherName(x.getName());	
		});
		
		return response;
	}

	public List<TeacherResponse> getTeacherByName(String name) {
		List<TeacherResponse> responseList = new ArrayList<TeacherResponse>();
		Iterable<Teacher> teachList = teacherRepo.findAll();
		/**
		 * Getting Teacher List in Service Layer
		 
		List<String> teachers = new ArrayList<String>();
		teachList.forEach(x -> {
			teachers.add(x.getName());
		});
		System.out.println(teachers);
		*/
		teachList.forEach(x->{
			if(x.getName().contains(name)) {
				
				
				TeacherResponse tr = new TeacherResponse();
				tr.setTeacherName(x.getName());
				tr.setEdu(x.getEducation());
				
				
				responseList.add(tr);
			}
//			if(name == x.getId())
//				response.setTeacherName(x.getName());	
		});
		
		return responseList;
	}

}

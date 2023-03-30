package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TeacherResponse;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;

@RestController
public class TeacherController {
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private TeacherService teachService;
	
	
	@GetMapping("/all-teacher")
	public Iterable<Teacher> getAllTeachers() {
		
		teacherRepo.findAll().forEach(x ->{
			System.out.println(x);
		});
		return teacherRepo.findAll();
	}
	
	@PostMapping("/teacher")
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		System.out.println(teacher);
		return teacherRepo.save(teacher);
	}
	
	@GetMapping("/teacher/byId/{id}")
	public TeacherResponse getTeacherById(@PathVariable("id") Integer id) {
		
		
		return teachService.getTeacherById(id);
		
	}
	
	@GetMapping("/teacher/byName/{name}")
	public List<TeacherResponse> getTeacherByName(@PathVariable("name") String name) {
		
		
		return teachService.getTeacherByName(name);
		
	}
}

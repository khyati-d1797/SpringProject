package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository stdRepo;

	@Autowired
	private StudentService stdService;

	@GetMapping("/all-students")
	public Iterable<Student> getAllUsers() {
		
		stdRepo.findAll().forEach(x ->{
			System.out.println(x);
		});
		return stdRepo.findAll();
	}
	
	@GetMapping("/student/{id}")
	public Optional<Student> getStudentById(@PathVariable("id") Integer id) {
		System.out.println(id);
		return stdRepo.findById(id);
	}
	
	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student std) {
		System.out.println(std);
		return stdRepo.save(std);
	}
	
	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student std) {
		System.out.println(std);
		return stdRepo.save(std);
	}
	
	@DeleteMapping("/student")
	public void deleteStudent(@RequestBody Student std) {
		System.out.println(std);
//		stdRepo.deleteById(std.getId());
	}
	
	@GetMapping("/student-subject-list/{id}")
	public StudentResponse getStudentAndSubjectsById(@PathVariable("id") Integer id) {
		
		
		return stdService.getStudentAndSubjects(id);
		
	}

}

package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;

@RestController
public class SubjectController {
	@Autowired
	private SubjectRepository subjectRepo;
	
	@RequestMapping("/all-subjects")
	public Iterable<Subject> getAllSubjects() {
		
		subjectRepo.findAll().forEach(x ->{
			System.out.println(x);
		});
		return subjectRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/subject")
	public String addSubject(@RequestBody Subject subject) {
		subjectRepo.save(subject);
		return "Successfully added";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/subject/{id}")
	public String updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
		subjectRepo.save(subject);
		return "Successfully added";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/subject/{id}")
	public String deleteSubject(@PathVariable Integer id) {
		subjectRepo.deleteById(id);
		return "Successfully deleted";
	}

}

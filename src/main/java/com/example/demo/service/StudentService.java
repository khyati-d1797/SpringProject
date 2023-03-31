package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentResponse;
import com.example.demo.dto.SubjectResponse;
import com.example.demo.dto.SubjectTeacherResponse;
import com.example.demo.dto.TeacherResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherSubject;
import com.example.demo.entity.studentSubTeach.StudentSubjectTeacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.StudentSubjectTeacherRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.TeacherSubjectRepository;
import com.example.demo.utilities.DateUtil;
import com.example.demo.utilities.Logger;

@Service
public class StudentService {
	
	

	@Autowired
	private StudentRepository stdRepo;

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private StudentSubjectTeacherRepository stdSubRepo;

	@Autowired
	private TeacherSubjectRepository teachSubRepo;

	public StudentResponse getStudentAndSubjects(Integer stdId) {
		StudentResponse response = new StudentResponse();
		/**
		 * Getting Student and StudentSubject Mapping Key
		 */
		Optional<Student> optionalStd = stdRepo.findById(stdId);
		if(optionalStd.isEmpty()) {
			return response;
		}
		Student std = optionalStd.get();
		Iterable<StudentSubjectTeacher> stdSubTeachList = stdSubRepo.findByStdSubTeachKeyStuId(stdId);

		/**
		 * Building Subject ID list
		 */
		List<Integer> subTeachIdList = new ArrayList<Integer>();
		List<Integer> subIdList = new ArrayList<Integer>();
		List<Integer> teacherIdList = new ArrayList<Integer>();
		stdSubTeachList.forEach(x -> {
			subTeachIdList.add(x.getStdSubTeachKey().getTeachSubId());
		});

		/**
		 * Get ALl Teacher Subject Mapping List
		 */
		Iterable<TeacherSubject> subTeachMappingList = teachSubRepo.findAllById(subTeachIdList);
		subTeachMappingList.forEach(x -> {
			subIdList.add(x.getSubjectId());
			teacherIdList.add(x.getTeacherId());
		});

		/**
		 * Getting all the Teacher and Subjects by id list
		 */
		Iterable<Subject> subjectList = subjectRepo.findAllById(subIdList);
		Iterable<Teacher> teacherList = teacherRepo.findAllById(teacherIdList);
		ArrayList<SubjectResponse> subResList = new ArrayList<SubjectResponse>();
		subjectList.forEach(x -> {
			SubjectResponse subResItem = new SubjectResponse();
			subResItem.setSubjectName(x.getName());
			subResList.add(subResItem);
		});
		ArrayList<TeacherResponse> teachResList = new ArrayList<TeacherResponse>();
		teacherList.forEach(x -> {
			TeacherResponse teachResItem = new TeacherResponse();
			teachResItem.setTeacherName(x.getName());
			teachResItem.setEdu(x.getEducation());
			teachResList.add(teachResItem);
		});
		System.out.println(stdSubTeachList);

		/**
		 * Subject taught By List
		 */
		ArrayList<SubjectTeacherResponse> subTeachResList = new ArrayList<SubjectTeacherResponse>();
		ArrayList<String> subTaughtByList = new ArrayList<String>();
		subTeachMappingList.forEach(x -> {
			
			SubjectTeacherResponse teacherSubjectResObj = new SubjectTeacherResponse();
			
			String subName = "";
			String teacherName = "";

			for (Subject y : subjectList) {
				if (y.getId().equals(x.getSubjectId())) {
					subName = y.getName();
					teacherSubjectResObj.setSubjectName(y.getName());
				}
					
			}

			for (Teacher y : teacherList) {
				if (y.getId().equals(x.getTeacherId())) {
					teacherName = y.getName() + " with " + y.getEducation();
					teacherSubjectResObj.setTeacherName(y.getName());
					teacherSubjectResObj.setTeacherEducation(y.getEducation());
				}
					
			}
			String msg = subName + " is taught by " + teacherName;
			msg = msg.replaceAll("\n", "");
			msg = msg.replaceAll("\r", "");
			subTaughtByList.add(msg);
			subTeachResList.add(teacherSubjectResObj);
		});

		/**
		 * Getting Subject with Teacher and Education
		 */
//		ArrayList<String> subTeachList = new ArrayList<String>();
//		ArrayList<SubjectTeacherResponse> subTeachList = new ArrayList<SubjectTeacherResponse>();
//		subTeachMappingList.forEach(x -> {
//			SubjectTeacherResponse str = new SubjectTeacherResponse();
//			for (Subject y : subjectList) {
//				if (y.getId().equals(x.getSubjectId())) {
//					str.setSubjectName(y.getName());
//				}
//			}
//
//			for (Teacher t : teacherList) {
//				if (t.getId().equals(x.getTeacherId())) {
//					str.setTeacherName(t.getName());
//					str.setTeacherEducation(t.getEducation());
//				}
//			}
//			subTeachList.add(str);
//		});

		/**
		 * Building Final Student Response
		 */
		
		response.setStdId(stdId);
		response.setStdName(std.getName());
		response.setSubjects(subResList);
		response.setTeachers(teachResList);
		response.setSubjectTaughtByList(subTaughtByList);
		response.setSubteachList(subTeachResList);

		return response;
	}

	public StudentResponse getStudentDescription(Integer id) {
		StudentResponse stdres = new StudentResponse();
		Optional<Student> optionalStd = stdRepo.findById(id);
		if(optionalStd.isEmpty()) {
			return stdres;
		}
		Student std = optionalStd.get();
		System.out.println(std.toString());
		stdres.setStdName(std.getName());
		System.out.println(stdres.toString());
		
		
		LocalDate curDate = LocalDate.now();
		System.out.println(curDate);
		System.out.println(std.getDob());
		
		
		
		LocalDate localDob = std.getDob().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		Integer age = Period.between(localDob, curDate).getYears();
		stdres.setAge(age);
		Logger.log(curDate, std.getDob(), age);
		String gender = std.getGender() == 'F' ? "girl" : "boy";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
		Logger.log(localDob.format(formatter)); //print month ex. Oct or Aug
		String ordinal = DateUtil.getOrdinal(localDob.getDayOfMonth());
		stdres.setStudentDescription(std.getName() + ", born in "+ localDob.format(formatter)+ " " + ordinal + ", " + localDob.getYear() + " is a good " + gender);
		return stdres;
	}
}

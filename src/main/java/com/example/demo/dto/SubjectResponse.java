package com.example.demo.dto;

public class SubjectResponse {
	
	private String subjectName;

	public SubjectResponse() {
		super();
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "SubjectResponse [subjectName=" + subjectName + "]";
	}

	
	
}

package com.customsorting;

import java.util.Comparator;

import com.sdbms.Student;

public class SortStudentById implements Comparator<Student> {
	@Override
	public int compare(Student x, Student y) {
		return x.getId().compareTo(y.getId());
	}

}

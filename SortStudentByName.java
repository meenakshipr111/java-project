package com.customsorting;

import java.util.Comparator;

import com.sdbms.Student;

public class SortStudentByName implements Comparator<Student>{
	@Override
	public int compare(Student x, Student y) {
		return x.getName().compareTo(y.getName());
	}

}

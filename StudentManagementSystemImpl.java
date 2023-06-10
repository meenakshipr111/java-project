package com.sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.customException.InvalidChoiceException;
import com.customException.StudentNotFoundException;
import com.customsorting.SortStudentByAge;
import com.customsorting.SortStudentById;
import com.customsorting.SortStudentByMarks;
import com.customsorting.SortStudentByName;

public  class StudentManagementSystemImpl implements StudentManagementSystem{
	Scanner scan=new Scanner(System.in);
	Map<String, Student> db=new LinkedHashMap<String, Student>();
	@Override
	public void addStudent() {
		System.out.println("Enter student Age");
		int age=scan.nextInt();
		System.out.println("Enter student Name");
		String name=scan.next();
		System.out.println("Enter student Marks");
		int marks=scan.nextInt();
		Student std =new Student(age, name, marks);

		db.put(std.getId(),std);
		System.out.println("student Record Inserted successfully");
		System.out.println("Student ID is:"+std.getId());

	}
	@Override
	public void displayStudent() {
		String id=scan.next(); // Student name=scan.next(to.UpperCase());
		id=id.toUpperCase();
		if(db.containsKey(id)) { // checking if id is present or not // getting the student object
			Student std=db.get(id);
			System.out.println("ID:"+std.getId());
			System.out.println("AGE:"+std.getAge());
			System.out.println("NAME:"+std.getName());
			System.out.println("MARKS:"+std.getMarks());
			//			System.out.println(std); as toString() is overridden
		}
		else {
			try {
				String message="Student with the ID"+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void displayAllStudent() {
		if(db.size()!=0) {
			System.out.println("student details are as follows");
			System.out.println("-----------");
			Set <String>keys=db.keySet();
			for(String key:keys) {
				//			Student std=db.get(key);
				//			System.out.println(std);
				System.out.println(db.get(key));
			}
		}
		else {
			try {
				String message="studentdatabase is empty, nothing to display ";
				throw new StudentNotFoundException(message);
			}	
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void removeStudent() {
		System.out.println("Enter student Id ");
		String  id=scan.next();
		id=id.toUpperCase();
		if(db.containsKey(id)) { 
			System.out.println("student record found");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("student Record Deleted successfully");
		}
		else {
			try {
				String message="Student with the ID"+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());

			}
		}
	}

	@Override
	public void removeAllStudent() {
		if(db.size()!=0) {
			System.out.println("student records available:"+db.size());
			db.clear();
			System.out.println("all student records deleted successfully");
			System.out.println("student records available:"+db.size());
		}
		else {
			try {
				String message="student database is empty, nothing to delete";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	@Override
	public void updateStudent() {
		System.out.println("Enter student Id");
		String id=scan.next();
		id=id.toUpperCase();
		if(db.containsKey(id)) {
			Student std=db.get(id);
			System.out.println("1:update the age\n2:update name\n3:update marks");
			System.out.println("Enter choice");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter age ");
				int age=scan.nextInt();
				std.setAge(age); //std.setAge(scan.nextInt());
				break;

			case 2:
				System.out.println("Enter name");
				String name=scan.next();
				std.setName(name); // std.setName(scan.next());
				break;

			case 3:
				System.out.println("Enter marks");
				int marks=scan.nextInt();
				std.setMarks(marks);
				break;

			default :
				try {
					String message="Inavlid choice, kindly enter vali choice";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		else {
			try {
				String message="student with the id"+id+"is not found";
				throw new StudentNotFoundException(message);
			}	
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}


	@Override
	public void countsStudent() {
		System.out.println("No Of Student Records:"+db.size());

	}
	@Override
	public void sortStudent() {
		if(db.size()>=2) {
			Set<String> keys= db.keySet();
			List<Student> list= new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			System.out.println("1:Sort By ID\n2:Sort By Age");
			System.out.println("3:Sort By name\n4:Sort By marks");
			System.out.println("Enter choice");
			int choice =scan.nextInt();
			switch(choice) {
			case 1:
				Collections.sort(list,new SortStudentById());
				display(list);
				break;

			case 2:
				Collections.sort(list, new SortStudentByAge());
				for(Student s:list) {
					System.out.println(s);
				}
				break;
			case 3:
				Collections.sort(list, new SortStudentByName());
				display(list);

				break;
			case 4:
				Collections.sort(list,new SortStudentByMarks());
				display(list);
				break;

			default:
				try {
					String message="student with the id is not found";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());

				}
			}
		}
		else {
			try {
				String message="No suffiecient student records to compare";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	private static void display(List<Student>list) {
		for(Student s:list) {
			System.out.println(s);
		}
	}
	@Override
	public void getStudentWithHighestMarks() {
		if(db.size()>=2) {
			Set<String> keys=db.keySet();
			List<Student> list=new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(list.size()-1));
		}
		else {
			try {
				String message="No suffiecient student records to compare";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void getStudentWithLowestMarks() {
		if(db.size()>=2) {
			Set<String> keys=db.keySet();
			List<Student> list=new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(0));
		}
		else {
			try {
				String message="No Sufficient Student Record to compare";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

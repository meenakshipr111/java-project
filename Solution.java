package com.sdbms;

import java.util.Scanner;

import com.customException.InvalidChoiceException;

public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to student Data Base management System");
		System.out.println("-------------------------------------");
		Scanner scan=new Scanner(System.in);
		StudentManagementSystem Sms=new StudentManagementSystemImpl();
		while(true) {
			System.out.println("1:addStudent\n2:displayStudent\n3:displayAllStudent");
			System.out.println("4:removeStudent\n5:removeAllStudent\n6:updateStudent");
			System.out.println("7:countStudent\n8:sortStudent\n9:getStudentWithHighestMarks");
			System.out.println("10:getStudentWithLowestMarks");
			System.out.println("11:EXIT\n Enter Choice");
			int choice=scan.nextInt();
			switch(choice) {
			case 1:
				Sms.addStudent();
				break;
			case 2:
				Sms.displayStudent();
				break;
			case 3:
				Sms.displayAllStudent();
				break;
			case 4:
				Sms.removeStudent();
				break;
			case 5:
				Sms.removeAllStudent();
				break;
			case 6:
				Sms.updateStudent();
				break;
			case 7:
				Sms.countsStudent();
				break;
			case 8:
				Sms.sortStudent();
				break;
			case 9:
				Sms.getStudentWithHighestMarks();
				break;
			case 10:
				Sms.getStudentWithLowestMarks();
				break;
			case 11:
				System.out.println("THANK YOU !!");
				System.exit(0);
			default:
				try {
					String msg= "Invalid choice,kindly enter valid choice";
					throw new InvalidChoiceException(msg);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}	
			} // end of switch
			System.out.println("-----------------");
		} // end of while
	} // end of main()

}// end of class

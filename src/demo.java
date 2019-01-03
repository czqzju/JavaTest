import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Student{
	private int id;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
	}
}


public class demo
{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		
		List<Student> studentList = new ArrayList<Student>();
		while(testCases>0){
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();
			
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			testCases--;
		}
        Collections.sort(studentList, new Comparator<Student>(){
            @Override
            public int compare(Student s1, Student s2){
                if(s1.getCgpa() > s1.getCgpa()) return -1;
                else if(s1.getCgpa() < s1.getCgpa()) return 1;
                else{
                    if(s1.getFname().compareTo(s2.getFname()) != 0) return s1.getFname().compareTo(s2.getFname());
                    else{
                        return s1.getId() > s2.getId() ? 1 : -1;
                    }
                }
            }
        });
      
      	for(Student st: studentList){
			System.out.println(st.getFname());
		}
	}
}
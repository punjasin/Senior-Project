package test;

public class Student extends User{
	public int student_id;
	
	public Student(int id){
		this.student_id = id;
	}
	public int getID(){
		return student_id;
	}
}

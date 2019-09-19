package P98;
public class Instructor extends Person {
	
	private int salary;
	public Instructor(String name, int year,int salary) {
		super(name, year);
		this.salary = salary;
	}
	
	public String toString(){
		return "My name: "+name+" Year of Birth: "+year+" Salary: "+salary;
	}
}

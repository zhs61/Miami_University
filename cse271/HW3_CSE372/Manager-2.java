package P99;

public class Manager extends Employee {

	String department;
	
	//===================constructors
	public Manager(String name, int salary,String department) {
		super(name, salary);
		this.department=department;
	}
	
	//=====================methods
	public String toString(){
		return "My name: "+name+" Salary: "+salary+" My department: "+department;
	}
	

}

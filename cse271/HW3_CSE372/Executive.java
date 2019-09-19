package P99;

public class Executive extends Manager {
	
	//======================constructors
	public Executive(String name, int salary, String department) {
		super(name, salary, department);
	}

	//=========================methods
	public String toString(){
		return "My name: "+name+" Salary: "+salary+" My department: "+department;
	}
}

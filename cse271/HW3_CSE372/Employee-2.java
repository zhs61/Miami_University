package P99;

public abstract class Employee {
	public String name;
	public int salary;
	
	//==================constructor
	public Employee(String name,int salary){
		this.salary = salary;
		this.name = name;
	}
	//===================Methods
	public String toString(){
		return "My name: "+name+" Salary: "+salary;
	}
}

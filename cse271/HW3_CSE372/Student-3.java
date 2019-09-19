package P98;
public class Student extends Person {

	private String major;
	
	//=================constructor
	public Student(String name, int year,String major) {
		super(name, year);
		this.major=major;
	}
	//==================methods
	public String toString(){
		return "My name: "+name+" Year of Birth: "+year+" My major: "+major;
	}
	
}

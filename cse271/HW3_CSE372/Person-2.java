package P98;
public class Person {
	String name;
	int year;
	
	//===========Constructors
	public Person(String name, int year) {
		setName(name);
		setYear(year);
	}
	//====================methods
	public String toString(){
		return "My name: "+name+"Year of Birth: "+year;
		
	}
	//=============getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}

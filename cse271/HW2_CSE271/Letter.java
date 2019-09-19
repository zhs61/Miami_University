//P 8.11
public class Letter {

	public static void main(String[] args) {
		Letter l1 = new Letter("Mary","John");
		l1.addLine("I am sorry we must part.");
		l1.addLine("I wish you all the best.");
		System.out.println(l1.getText());
	}
	
	public String from,to,text="";
	
	//================= constructors
	public Letter (){
		this("","");
	}
	public Letter(String from, String to){
		setFrom(from);
		setTo(to);
	}
	
	//===================methods
	public void addLine(String line){
		
		text = text+line+"\n";
	
	}
	
	public String getText(){
		text = "Dear " + to + ":\n\n" + text + "\nSincerely\n\n"+from;
		return text;
	}

	//=================getters and setters
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
}

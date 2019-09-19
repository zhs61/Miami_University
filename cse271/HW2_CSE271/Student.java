
// P 8.7

public class Student {
	
	//========================= Others
	
	
	//========================= Properties
	private String name;
	private int totalScore,numOfQuizzes;
	
	
	//========================= Constructors
	public Student(){
		this("",0,0);
	}
	
	public Student(String name, int totalScore, int numOfQuizzes) {
		setName(name);
		setTotalScore(totalScore);
		setNumOfQuizzes(numOfQuizzes);	
	}
	//========================= methods
	public void addQuiz(int score){
		totalScore += score;
		numOfQuizzes++;
	}
	public double getAverageScore(){
		
		return totalScore*1.0/numOfQuizzes;
	}
	
	@Override
	public String toString() {
		return "Student name=" + name + ", totalScore=" + totalScore
				+ ", numOfQuizzes=" + numOfQuizzes + ", getAverageScore()="
				+ getAverageScore() ;
	}

	//========================= Setter and getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getNumOfQuizzes() {
		return numOfQuizzes;
	}
	public void setNumOfQuizzes(int numOfQuizzes) {
		this.numOfQuizzes = numOfQuizzes;
	}
	
	
	
	
	
	
}

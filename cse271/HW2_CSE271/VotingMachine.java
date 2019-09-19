// P 8.10
public class VotingMachine {

	//============= other
	
	//============= properties
	int tailsOfRepublic=0, tailsOfDemocraft=0;
	//============= constroctors
	public VotingMachine(){
		this(0,0);
	}

	public VotingMachine(int tailsOfRepublic, int tailsOfDemocraft) {
		setTailsOfRepublic(tailsOfRepublic);
		setTailsOfDemocraft(tailsOfDemocraft);
	}
	
	//============= methods
	


	//reset the vote for both parties
	public void clear(){
		tailsOfDemocraft=0;
		tailsOfRepublic=0;
	}
	
	//vote for republic 
	public void voteForRepublic(){
		tailsOfRepublic++;
	}
	
	//vote for democraft
	public void voteForDemocraft(){
		tailsOfDemocraft++;
	}
	
	@Override
	public String toString() {
		return "VotingMachine [tailsOfRepublic=" + tailsOfRepublic
				+ ", tailsOfDemocraft=" + tailsOfDemocraft + "]";
	}
	//============= getter and setter


	

	public int getTailsOfRepublic() {
		return tailsOfRepublic;
	}


	public void setTailsOfRepublic(int tailsOfRepublic) {
		this.tailsOfRepublic = tailsOfRepublic;
	}


	public int getTailsOfDemocraft() {
		return tailsOfDemocraft;
	}


	public void setTailsOfDemocraft(int tailsOfDemocraft) {
		this.tailsOfDemocraft = tailsOfDemocraft;
	}
	
}

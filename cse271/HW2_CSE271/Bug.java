//P 8.12
public class Bug {

	int initialPosition;
	public boolean direction = true;

	// ===================constructors
	public Bug(int initialPosition) {
		setInitialPosition(initialPosition);
	}

	// ============================methods
	public void turn() {
		direction = !direction;
	}

	public void move(){
		if(direction){
			initialPosition++;
		}else{
			initialPosition--;
		}
	}

	public int getPosition() {
		return initialPosition;
	}

	// ========================getters and setters
	public int getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}

}

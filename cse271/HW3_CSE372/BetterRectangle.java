package P910;

import java.awt.Rectangle;

public class BetterRectangle extends Rectangle {

	//========================constructor
	public BetterRectangle(int x, int y, int width, int height) {
		super.setLocation(x, y); 
		super.setSize(width, height); 
	}
	
	//=========================Methods
	public double getPerimeter() { 
		return super.getHeight() * 2 + super.getWidth() * 2; 
	} 

	public double getArea() { 
		return super.getHeight() * super.getWidth(); 
	} 


}

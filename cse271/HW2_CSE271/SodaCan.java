//p 8.5

public class SodaCan {
	
	//========== properties
	double height,radius;

	
	//========== Constructors
	
	public SodaCan(){  //empty
		this(0.0,0.0);
	}
	
	public SodaCan(double height, double radius) {
		setHeight(height);
		setRadius(radius);
	}

	//========== methods
	
	public double getSurfaceArea(){
		return (radius*radius*Math.PI)*2+(2*radius*Math.PI*height);
	}
	
	public double getVolume(){
		return (radius*radius*Math.PI)*height;
	}
	
	@Override
	public String toString() {
		return String.format("%s %.2f %s %.2f %s %.2f %s %.2f", "SodaCan height=", height, ", radius=" ,radius
				,", SurfaceArea=", getSurfaceArea(), ", Volume="
				,getVolume());
	}

	//========== getters and setters
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}

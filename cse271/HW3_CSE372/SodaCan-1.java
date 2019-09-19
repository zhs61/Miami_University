//p 8.5
package P914;
public class SodaCan implements Measurable{
	
	//========== properties
	double height,radius;
	double sum;
	
	//========== Constructors
	
	public SodaCan(){  //empty
		this(0.0,0.0);
	}
	
	public SodaCan(double height, double radius) {
		this.height=height;
		this.radius=radius;
	}

	//========== methods
	
	public double getSurfaceArea(){
		return (radius*radius*Math.PI)*2+(2*radius*Math.PI*height);
	}
	
	public double getVolume(){
		return (radius*radius*Math.PI)*height;
	}
	 public double getMeasure(double[] d){
      double area = this.getSurfaceArea(); 
      for(double a : d){
    	  sum += a;
      }
      return sum / d.length;
    } 
	@Override
	public String toString() {
		return String.format("%s %.2f %s %.2f %s %.2f %s %.2f", "SodaCan height=", height, ", radius=" ,radius
				,", SurfaceArea=", getSurfaceArea(), ", Volume="
				,getVolume());
	}

	
	
}

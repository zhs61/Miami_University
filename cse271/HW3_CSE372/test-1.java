package P914;



public class test {
	
	public static void main(String[] args) {
		SodaCan s = new SodaCan();
//		System.out.println("Please enter the surfacearea of the cans:");
		double b1[] = new double[6];
		b1[0]=32;
		b1[1]=4;
		b1[2]=22;
		b1[3]=6;
		b1[4]=12;
		b1[5]=8;
		System.out.println(s.getMeasure(b1));
		
	}
}

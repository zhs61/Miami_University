

public class Program8 {
 
   public static void main(String[] args){
     int num=7;
     boolean right = num%3==0||num%5==0?true:false; 
      System.out.print(right);
   }
   
   public static int smallest(double a, double b, double c){
   int count = 0;
   if(a==b){
    count++;  
   }
   if(a==c){
      count++;
   }
   if(b==c){
      count++;
   }
   return count;
   }
}
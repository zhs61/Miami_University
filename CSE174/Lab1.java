public class Lab1 {
   public static void main(String[] args) {
      printBorder('*',20);
      System.out.println("*                  *");
      
      
      
      
      
   }
   public static void printBorder(char symbol,int size) {
      for(int i=0;i<=size;i++){
         System.out.print(symbol);
      }
   }
}
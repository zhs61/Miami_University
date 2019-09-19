//Name: Zhongxuan Song
//Instructor: Norm Krumpe
//CSE 174, Section H
//Date: Oct 2 2016
//Filename: Program5.java
//Description: 
//                
import java.util.Scanner;
public class Program5{
   
   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      System.out.print("Name: ");
      String oriName = scan.nextLine();
      System.out.print("Phone: ");
      String oriPhone = scan.nextLine();
      System.out.print("Address: ");
      String oriAddress = scan.nextLine();
      System.out.println();
      System.out.println("***** Results *****");
      
      //Seperate Name
      int comma1 = oriName.indexOf(",");
      String oriLastName = oriName.substring(0,--comma1);
      comma1++;
      
      int space1 = oriName.lastIndexOf(" ");
      String oriFirstName = oriName.substring(++comma1,space1);
      
      String oriMidName= oriName.substring(++space1);
      
      //last Name
      oriLastName = oriLastName.trim();
      String firstLLast = oriLastName.substring(0,1).toUpperCase();
      String afterFirstLLast = oriLastName.substring(1).toLowerCase();
      String finalLastName = firstLLast + afterFirstLLast;
      
      //First name
      oriFirstName = oriFirstName.trim();
      String firstLFirst = oriFirstName.substring(0,1).toUpperCase();
      String afterFirstFirst = oriFirstName.substring(1).toLowerCase();
      String finalFirstName = firstLFirst + afterFirstFirst;
     
      //mid initial
      String firstLMid = oriMidName.substring(0,1).toUpperCase();
      
      System.out.println(finalFirstName + " " + firstLMid + ". " + finalLastName);
      
      //Phone number
      oriPhone = oriPhone.replace(" ","");
      int firstHyphen = oriPhone.indexOf("-");
      
      String firstPartNum = oriPhone.substring(0,firstHyphen).trim();
      firstHyphen++;
      int secondHyphen = oriPhone.indexOf("-",firstHyphen);
      String secondPartNum = oriPhone.substring(firstHyphen,secondHyphen).trim();
      secondHyphen++;
      String thirdPartNum = oriPhone.substring(secondHyphen).trim();
      
      System.out.println("(" + firstPartNum + ")" + secondPartNum + "-" + thirdPartNum);
   
      //Address
      int lastCommaOfAddress = oriAddress.lastIndexOf(",");
      lastCommaOfAddress--;
      int secondLastComma = oriAddress.lastIndexOf(",",lastCommaOfAddress);
      String firstPartOfAddress =oriAddress.substring(0,secondLastComma);
      System.out.println(firstPartOfAddress);
      
      secondLastComma++;
      String secondPartOfAddress = oriAddress.substring(secondLastComma).trim();
      System.out.println(secondPartOfAddress
                       );
   
   
   }// end main
   
   
   
}
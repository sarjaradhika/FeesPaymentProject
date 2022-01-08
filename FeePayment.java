package project1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class FeePayment {
	
	static Scanner input=new Scanner(System.in);
	 
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
	 
     String nationality,level;
     
     //parsing the json file
     //Enter the File path inside the FileReader
	 Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Radhika\\eclipse-workspace\\Project1\\src\\project1\\FeeStruct.json"));
     
     // typecasting obj to JSONObject
     JSONObject jo = (JSONObject) obj;
     
	 //for entering the type of fee
     System.out.println("\nEnter the fee type\n 1 for Examfee\n 2 for Appfee\n");
     Scanner input=new Scanner(System.in);
     int FeeType=input.nextInt();
     System.out.println(FeeType);
     switch(FeeType){
     //if the feetype is Examination fee then this part is executed
     case 1: {
         
	     //Exam fee JSONObject is taken into Examinfee
    	 JSONObject Examinfee= (JSONObject) jo.get("Exam Fee");
    	 
	     //for selecting nationality
	     System.out.println("Select the nationality\n1.Indian\n2.Foreign \n3.NRI\n4.SAARC\n");
	     nationality= input.next();
	 
	     String capnat=nationality.toUpperCase();
	     
         //JSONObject under specified nationality is taken into fee
	     JSONObject fee=((JSONObject) Examinfee.get(capnat));
	     
	     //level_course_selecn will take the course type and level and returns the level 
	     level_course_selcn();
	     //fetch_fee1 will process the fee object and prints the fee amount
	     fetch_examinfee(fee);
	     break;
	     
	  
     }
     //if Application fee is selected then this part is executed
     case 2:
    	 {
    	 
    		 JSONObject Applnfee= (JSONObject) jo.get("Application Fee");
    		 
    		 //for selecting the nationality
	         System.out.println("Select the nationality\n1.Indian \n2.Foriegn \n");
	         nationality= input.nextLine();
	         String capnat=nationality.toUpperCase();
	         
	         //level_course_selecn will take the course type and level and returns the level 
	         level=level_course_selcn();
	         
	         //JSONObject under specified nationality is taken into fee
	         JSONObject fee=((JSONObject) Applnfee.get(capnat));
	         JSONObject all_course=((JSONObject) fee.get("ALL_COURSES"));
	         
	         //fetch_fee2 will process the fee object and prints the fee amount
	         fetch_applnfee(all_course,level);
	         break;
	    	 }
    	 
    	 
    	 default:
    		 System.out.println("No such Fee type");
    		 return;
	}
     
 }
//this function will facilitate the selection of course and level	
 public static String level_course_selcn()
 {
	 String course,level;
	 System.out.println("Select a course\n1.Medical\n2.Dental\n3.Ayurveda\n");
     course=input.nextLine();
     System.out.println("Select a level\n1.UG\n2.UG-Diploma\n3.PG\n");
     level=input.nextLine();
     return level;
 }
 //For Examination fee this function is executed to fetch the amount from JSON
 public static void fetch_examinfee(JSONObject nationalty)
 {
	 
		 JSONObject all_course=((JSONObject) nationalty.get("ALL_COURSES")); 
		 
	     JSONObject all_level=((JSONObject) all_course.get("ALL_LEVEL"));
	     
	     long amount = (long) all_level.get("amount");
	     
	     System.out.println("Examination fees is "+amount);
	    
	     
	     
	 
 }
 //For Application fee this function is executed to fetch the amount from JSON
 public static void fetch_applnfee(JSONObject all_course, String level)
 {
	      
	     String lvl=level.toUpperCase();
    	 JSONObject ugfee=((JSONObject) all_course.get(lvl));
    	 long amount=((long) ugfee.get("amount"));
    	 System.out.println("Application fees is: "+amount);
     	
 }
}





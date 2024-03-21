package com.tns.userdefined;

 import java.util.Scanner;

 public class customerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		Scanner obj=new Scanner(System.in);
          String name;
           int id;
        
         
           //read integer value
           System.out.println("enter the id");
           id=obj.nextInt();
           obj.nextLine();
           System.out.println("id is"+id);
           
           System.out.println("enter the name");
	       name = obj.nextLine();
	       System.out.println("name is" +name);
	
	}
}

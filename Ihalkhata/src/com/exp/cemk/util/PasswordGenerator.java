package com.exp.cemk.util;

import java.util.Random;



public class PasswordGenerator {
  static PasswordGenerator _instance = new PasswordGenerator();
  
  public static PasswordGenerator getInstance()
  {
	  return _instance;
  }
  
  private  Random rn = new Random();


	public String getPassword()
	{
		String pwd = PasswordGenerator.getInstance().randomstring()+"@"+new Integer(PasswordGenerator.getInstance().rand(100,999)).toString();
		
		
		
		return pwd;
	}
	
	 @SuppressWarnings("deprecation")
	public  String randomstring()
     {
             
             byte b[] = new byte[3];
             for (int i = 0; i < 3; i++)
                     b[i] = (byte)rand('a', 'z');
             return new String(b, 0);
     }
	 
	 public  int rand(int lo, int hi)
     {
             int n = hi - lo + 1;
             int i = rn.nextInt() % n;
             if (i < 0)
                     i = -i;
             return lo + i;
     }
	 
	 public static void main(String[] args)
	 {
		 String test = "";
		 
		 test = PasswordGenerator.getInstance().randomstring()+"@"+new Integer(PasswordGenerator.getInstance().rand(100,999)).toString();
		 System.out.println("test is :"+test);
	 }




	
}

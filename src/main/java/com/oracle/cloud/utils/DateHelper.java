package com.oracle.cloud.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class DateHelper
{
	
	 static String db = "abcdefghijklmnopqrstuvwxyz";
     static String dbCap = "ABCDEFGHIJKLMKNOPRSTUVWXYZ";
     static String dbNum = "123456789";

     static int seed = 0;
	
//	private static Properties properties=new Properties();
//	public static String UNIQUE_ID=getUniqueID();
	
	
  public static String getCurrentDateTime(String format)
  {
	  DateFormat dateFormat=new SimpleDateFormat(format);
	  Calendar calender=Calendar.getInstance();
	  String date=dateFormat.format(calender.getTime());
	  return date;
  }
  
  public static String GetRandomName(int length)
  {
      seed++;

      String name = "";

      Random r = new Random();

      int charIndex = 0;

      for (int i = 0; i < length; i++)
      {
      	
          charIndex = r.nextInt(db.length() - 1);

          name += db.charAt(charIndex);
      }

      return name;
  }

  public static String GetRandomCapName(int length)
  {
      seed++;

      String name = "";

      Random r = new Random();

      int charIndex = 0;

      for (int i = 0; i < length; i++)
      {
          charIndex = r.nextInt(dbCap.length() - 1);

          name += dbCap.charAt(charIndex);
      }

      return name;
  }


  public static String GetRandomNumber(int length)
  {
     // seed++;

      String name ="";

      Random r = new Random();

      int charIndex = 0;

      for (int i = 0; i < length; i++)
      {
      	charIndex=r.nextInt(dbNum.length() - 1);

          name += dbNum.charAt(charIndex);
          
      }

      return name;
  }
  
  
  
  
}

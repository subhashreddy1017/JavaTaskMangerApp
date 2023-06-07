package com.uttra.taskmanager.task.util;

import com.uttra.taskmanager.exception.com.EnterProperDate;

public class TaskUtil {

	public static boolean checkValidName(String name) {

		if ((name == null) || name.trim().equals(""))
			return false;
		if (!Character.isAlphabetic(name.charAt(0)))
			return false;
		for (int i = 1; i < name.length(); i++) {
			char c=name.charAt(i);
			if (!(Character.isAlphabetic(c)||(Character.isDigit(c))))
				return false;
		}
		return true;
	}
	public static boolean dateValidation(String date) throws EnterProperDate 
	{
		for(int i=0;i<date.length();i++) 
		{
			if(!(date.charAt(i)=='/'||Character.isDigit(date.charAt(i))))
				return false;
		}
		String[] s=date.split("/");
		if(Integer.parseInt(s[1])>12) 
		{
			return false;
		}
			
		if(Integer.parseInt(s[1])>29&&Integer.parseInt(s[2])==2)
			throw new EnterProperDate("Feb have only 29 days");
		return true;
	}
	public static boolean priorityCheck(int priority) 
	{
		return (priority<=10)&&(priority>=1);
	}
}

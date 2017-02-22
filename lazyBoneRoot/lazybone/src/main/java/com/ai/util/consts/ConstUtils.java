package com.ai.util.consts;

import java.util.Calendar;
import java.util.Date;

public class ConstUtils {
	
	public static long getTodayStartTime(){
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();
	}
	
	public static Date getDateStartTime(Date date){
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();
	}
	
	public static Date getDateEndTime(Date date){
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR, 23);
        todayStart.set(Calendar.MINUTE, 59);
        todayStart.set(Calendar.SECOND, 59);
        todayStart.set(Calendar.MILLISECOND, 999);  
        return todayStart.getTime();
	}
	
	public static Date addDay(Date date, int amount){
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.add(Calendar.DATE, amount);
		return todayStart.getTime();
	}
}

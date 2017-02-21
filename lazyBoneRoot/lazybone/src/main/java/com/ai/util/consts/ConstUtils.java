package com.ai.util.consts;

import java.util.Calendar;

public class ConstUtils {
	
	public static long getTodayStartTime(){
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();
	}
}

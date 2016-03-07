package com.zhushan.bishengwang.Itools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtils {                                   
    private static SimpleDateFormat sf = null;
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

	public static String getDateToString() {
		Date d = new Date();
		sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(d);
	}
    
    public static String serverToClientTime(String times){  
    	if(times == null)  
    	return "";  
    	Calendar serverNow = Calendar.getInstance();  
    	//从PHP转成Java的时间值,在末尾添加三位  
    	try{  
    	serverNow.setTime(new Date(Long.parseLong(times+"000")));  
    	}catch(NumberFormatException e){  
    	return times;  
    	}  
    	int serverHour = serverNow.get(Calendar.HOUR_OF_DAY);  
    	int serverMinute = serverNow.get(Calendar.MINUTE);  	  
    	return serverHour +""+ serverMinute;  
    	
    	}  
        
}
   
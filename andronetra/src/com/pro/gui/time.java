package com.pro.gui;

import java.util.Calendar;

public class time 
{
	int hr,min,time,year,month,date;
	String timeis;
	
	public String timefun(){
	Calendar c = Calendar.getInstance(); 
    min=c.get(Calendar.MINUTE);
    hr=c.get(Calendar.HOUR_OF_DAY);
    date=c.get(Calendar.DATE);
    month=c.get(Calendar.MONTH)+1;
    time=c.get(Calendar.AM_PM);
    year=c.get(Calendar.YEAR);
    
    if(time==1)
    {
     hr=hr-12;
     timeis="The time is "+Integer.toString(hr)+":"+Integer.toString(min)+" PM "+" and the date is "+Integer.toString(date)+" "+Integer.toString(month)+" "+Integer.toString(year);
    }
    else
    	timeis="The time is "+Integer.toString(hr)+":"+Integer.toString(min)+" AM "+" and the date is "+Integer.toString(date)+" "+Integer.toString(month)+" "+Integer.toString(year);	
    
    return timeis;
	}
}
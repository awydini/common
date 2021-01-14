package net.aydini.common.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Jan 13, 2021
 */
public class DateUtils
{

    public static DateUtils dateUtils;
    
    
    public static DateUtils getInstance()
    {
        if(dateUtils == null)
            dateUtils = new DateUtils();
        return dateUtils;
    }
    
    private Calendar getCalendar(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return calendar;
    }
    
    public Date subtractyear(Date date , int number)
    {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - number);
        
        return calendar.getTime();
    }
    
    public Date addYear(Date date , int number)
    {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + number);
        
        return calendar.getTime();
    }
    
    
    public Date subtractMonth(Date date , int number)
    {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - number);
        
        return calendar.getTime();
    }
    
    
    public Date addMonth(Date date , int number)
    {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + number);
        
        return calendar.getTime();
    }
    
    
    public Date addDay(Date date , int number)
    {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + number);
        
        return calendar.getTime();
    }
    
    
    public Date subtractDay(Date date , int number)
    {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - number);
        
        return calendar.getTime();
    }
    
    
}

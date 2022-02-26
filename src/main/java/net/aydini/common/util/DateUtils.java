package net.aydini.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public static LocalDateTime dateToLocalDateTime(Date date)
    {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    public static Date localDateTimeToDate(LocalDateTime localDateTime)
    {
        return Date.from(localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    public Date addtYear(Date date , int number)
    {
        return localDateTimeToDate(dateToLocalDateTime(date).plusYears(number));


    }

    
    public Date addMonth(Date date , int number)
    {
        return localDateTimeToDate(dateToLocalDateTime(date).plusMonths(number));
    }
    

    
    public Date addDay(Date date , int number)
    {
        return localDateTimeToDate(dateToLocalDateTime(date).plusDays(number));
    }

    
}

package net.aydini.common.date;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import net.aydini.common.date.PersianCalendar.YearMonthDate;
import net.aydini.common.exception.ServiceException;


/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Jan 13, 2021
 */
public class PersianDateUtils
{

    private static PersianDateUtils persianDateUtils;

    private final static int STANDARD_PERSIAN_DATE_LENTH = 8;
    
    
    private final String defaultDelimitter = "-";

    private PersianDateUtils()
    {
    }

    public static PersianDateUtils getInstance()
    {
        if (persianDateUtils == null) persianDateUtils = new PersianDateUtils();
        return persianDateUtils;
    }

    public PersianCalendar getCalandar(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        YearMonthDate yearMonthDate = new YearMonthDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        yearMonthDate = PersianCalendar.gregorianToJalali(yearMonthDate);
        return getCalandar(yearMonthDate.getYear(),yearMonthDate.getMonth(),yearMonthDate.getDate());

    }

    public PersianCalendar getCalandar(int year, int month, int day)
    {
        return new PersianCalendar(year, month, day);

    }

    public Date convertPersianDate(String jalaliDate, String delimiter)
    {
        if (jalaliDate == null) return null;
        if (delimiter != null) jalaliDate = jalaliDate.replaceAll(delimiter, "");
        if (jalaliDate.length() != STANDARD_PERSIAN_DATE_LENTH) throw new ServiceException("invalid date", jalaliDate);
        if (!StringUtils.isNumeric(jalaliDate)) throw new ServiceException("invalid date", jalaliDate);

        int year = Integer.parseInt(jalaliDate.substring(0, 4));
        int month = Integer.parseInt(jalaliDate.substring(4, 6))-1;
        int day = Integer.parseInt(jalaliDate.substring(6, 8));

        return getCalandar(year, month, day).getTime();

    }


    public String getPersianDate(Date date,String delimitter)
    {

        return toString(date,delimitter);

    }

    private String toString(Date date,String delimitter)
    {
        PersianCalendar calendar = getCalandar(date);
        StringBuilder stringBuilder = new StringBuilder();
        String month = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MONTH) +1), 2,"0");
        String day = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2,"0");
        delimitter = delimitter!= null ? delimitter : defaultDelimitter;
        return stringBuilder.append(calendar.get(Calendar.YEAR)).append(delimitter).append(month).append(delimitter)
                .append(day).toString();
    }
    
    
}

package net.aydini.common.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.github.mfathi91.time.PersianDate;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Jan 13, 2021
 */
public class PersianDateUtil
{

    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void main(String[] args) {
        System.out.println(toGregorian("13470801",null));
    }
    public static String toJalaliDate(LocalDate date,DateTimeFormatter formatter)
    {
        if(formatter==null)
            formatter=dateTimeFormatter;
        return formatter.format(PersianDate.fromGregorian(date));

    }

    public static Long toJalaliDateLong(LocalDate date)
    {
        return Long.parseLong(dateTimeFormatter.format(PersianDate.fromGregorian(date)));

    }

    public static LocalDate toGregorian(String jalaliDate, String delimiter)
    {
        if(jalaliDate == null)
            throw new IllegalArgumentException("input date is null");
        if(delimiter != null)
            jalaliDate = jalaliDate.replaceAll(delimiter, "");

        return PersianDate.parse(jalaliDate,dateTimeFormatter).toGregorian();

    }

    public static Date toGregorianDate(String jalaliDate, String delimiter)
    {

        return Date.from(toGregorian(jalaliDate, delimiter).atStartOfDay(ZoneId.systemDefault()).toInstant());

    }
    
    
}

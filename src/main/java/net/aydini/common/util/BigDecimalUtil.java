package net.aydini.common.util;

import java.math.BigDecimal;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public class BigDecimalUtil {

    /**
     * @throws NullPointerException if any of elements is null
     * @param bigDecimals
     * @return multiplication
     */
    public static BigDecimal multiply(BigDecimal ...bigDecimals )
    {
        BigDecimal total =  BigDecimal.ONE;

        for(BigDecimal bigDecimal : bigDecimals)
            total = total.multiply(bigDecimal);

        return total;
    }


    /**
     * @throws NullPointerException if any of elements is null
     * @param bigDecimals
     * @return sum
     */
    public static BigDecimal sum(BigDecimal ...bigDecimals )
    {
        BigDecimal total =  BigDecimal.ZERO;

        for(BigDecimal bigDecimal : bigDecimals)
            total = total.add(bigDecimal);

        return total;
    }



    public static boolean isNotNullAndGreaterThanZero(BigDecimal bigDecimal)
    {
        if(bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) != 1)
            return false;
        return true;
    }



    public static boolean isNullOrLessThanZero(BigDecimal bigDecimal)
    {
        if(bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) != -1)
            return false;
        return true;
    }
}

package net.aydini.common.math;

import java.math.BigDecimal;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public class BigDecimalUtil {

    private static BigDecimalUtil instance;

    public static BigDecimalUtil getInstance()
    {
        if(instance == null)
            instance = new BigDecimalUtil();
        return instance;
    }


    public BigDecimal orZero(BigDecimal number)
    {
        return number != null ? number : BigDecimal.ZERO;
    }


    /**
     *
     * @throws NullPointerException
     */
    public boolean isFirstEqualTo(BigDecimal first, BigDecimal second)
    {
        validate(new BigDecimal[]{first,second});
        return first.compareTo(second) == 0;
    }

    public boolean isFirstGreaterThan(BigDecimal first , BigDecimal second)
    {
        validate(new BigDecimal[]{first,second});
        return first.compareTo(second) == 1;
    }


    public boolean isFirstLessThan(BigDecimal first , BigDecimal second)
    {
        validate(new BigDecimal[]{first,second});
        return first.compareTo(second) == -1;
    }

    public boolean isFirstGreaterThanOrEqual(BigDecimal first , BigDecimal second)
    {
        validate(first,second);
        return first.compareTo(second) >= 0;
    }

    public boolean isFirstLessThanOrEqual(BigDecimal first , BigDecimal second)
    {
        validate(new BigDecimal[]{first,second});
        return first.compareTo(second) <= 0;
    }

    public BigDecimal add(BigDecimal ... numbers)
    {
        BigDecimal total = BigDecimal.ZERO;
        for(BigDecimal number : numbers)
            total.add(orZero(number));
        return total;
    }

    public BigDecimal subtract(BigDecimal ... numbers)
    {
        BigDecimal total = BigDecimal.ZERO;
        for(BigDecimal number : numbers)
            total.subtract(orZero(number));
        return total;
    }

    public BigDecimal multiply(BigDecimal ... numbers)
    {
        BigDecimal total = BigDecimal.ONE;
        for(BigDecimal number : numbers)
            total.multiply(orZero(number));
        return total;
    }

    public BigDecimal divide(BigDecimal ... numbers)
    {
        BigDecimal total = BigDecimal.ONE;
        for(BigDecimal number : numbers)
            total.divide(orZero(number));
        return total;
    }

    private void validate(BigDecimal ... numbers)
    {
        if(numbers == null)
            return;
        for(int i = 0 ; i < numbers.length ; i++)
            if(numbers[i] == null)
                throw new NullPointerException("input of index " + i + " is null");
    }
}

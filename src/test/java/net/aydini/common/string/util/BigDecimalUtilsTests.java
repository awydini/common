package net.aydini.common.string.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import net.aydini.common.util.BigDecimalUtil;
import org.junit.jupiter.api.Test;


/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class BigDecimalUtilsTests {


    @Test
    public void test_isNullOrLessThanZero_falseOnNull()
    {
        assertFalse(BigDecimalUtil.isNullOrLessThanZero(null));
    }

    @Test
    public void test_isNullOrLessThanZero_falseForZero()
    {
        assertFalse(BigDecimalUtil.isNullOrLessThanZero(BigDecimal.ZERO));
    }

    @Test
    public void test_isNullOrLessThanZero_falseForPositive()
    {
        assertFalse(BigDecimalUtil.isNullOrLessThanZero(BigDecimal.ONE));
        assertFalse(BigDecimalUtil.isNullOrLessThanZero(BigDecimal.TEN));
    }

    @Test
    public void test_isNullOrLessThanZero_trueForNegative()
    {
        assertTrue(BigDecimalUtil.isNullOrLessThanZero(BigDecimal.valueOf(-1)));
    }


    /**
     *
     */

    @Test
    public void test_isNotNullAndGreaterThanZero_falseOnNull()
    {
        assertFalse(BigDecimalUtil.isNotNullAndGreaterThanZero(null));
    }

    @Test
    public void test_isNotNullAndGreaterThanZero_falseForZero()
    {
        assertFalse(BigDecimalUtil.isNotNullAndGreaterThanZero(BigDecimal.ZERO));
    }

    @Test
    public void test_isNotNullAndGreaterThanZero_falseForPositive()
    {
        assertTrue(BigDecimalUtil.isNotNullAndGreaterThanZero(BigDecimal.ONE));
        assertTrue(BigDecimalUtil.isNotNullAndGreaterThanZero(BigDecimal.TEN));
    }

    @Test
    public void test_isNotNullAndGreaterThanZero_trueForNegative()
    {
        assertFalse(BigDecimalUtil.isNotNullAndGreaterThanZero(BigDecimal.valueOf(-1)));
    }






    /**
     *
     */



    public void test_multiply_nullPointerOnNullInput()
    {
        assertThrows(NullPointerException.class, ()->BigDecimalUtil.multiply(BigDecimal.ONE,null));
    }

    public void test_multiply_zeroIfAnyInputIsZero()
    {
        assertEquals(BigDecimal.ZERO, BigDecimalUtil.multiply(BigDecimal.ONE,BigDecimal.ZERO));
        assertEquals(BigDecimal.ZERO, BigDecimalUtil.multiply(BigDecimal.ONE,BigDecimal.TEN,BigDecimal.ZERO));

    }

    public void test_multiply_multiplicationOfNoneZeroValues()
    {
        assertEquals(BigDecimal.TEN, BigDecimalUtil.multiply(BigDecimal.ONE,BigDecimal.TEN));
        assertEquals(BigDecimal.valueOf(1000), BigDecimalUtil.multiply(BigDecimal.TEN,BigDecimal.TEN,BigDecimal.TEN));

    }



    /**
     *
     */
    public void test_sum_nullPointerOnNullInput()
    {
        assertThrows(NullPointerException.class, ()->BigDecimalUtil.sum(BigDecimal.ONE,null));
    }



    public void test_multiply_sumOfNotNullValues()
    {
        assertEquals(BigDecimal.valueOf(11L), BigDecimalUtil.sum(BigDecimal.ONE,BigDecimal.TEN));
        assertEquals(BigDecimal.valueOf(30L), BigDecimalUtil.sum(BigDecimal.TEN,BigDecimal.TEN,BigDecimal.TEN));

    }

}

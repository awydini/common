package net.aydini.common.doamin.enumoration;

import java.math.BigDecimal;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */
public enum IfNullValue {

    DEFAULT_INT(0), DEFAULT_FLOAT(0f), DEFAULT_DOUBLE(0d), DEFAULT_LONG(0L), 
    DEFAULT_BIGDECIMAl(BigDecimal.ZERO), EMPTY_STRING(""),ZERO_STRING("0"),NULL(null);

    private Object value;

    IfNullValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

}
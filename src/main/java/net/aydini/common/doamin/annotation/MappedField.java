package net.aydini.common.doamin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.aydini.common.mapper.Mapper;
import net.aydini.common.doamin.enumoration.IfNullValue;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MappedField {

    String fieldName() default "";

    boolean isCustom() default false;

    @SuppressWarnings("rawtypes")
    Class<? extends Mapper> mapperClass() default Mapper.class;

    IfNullValue ifNullValue() default IfNullValue.NULL;

}

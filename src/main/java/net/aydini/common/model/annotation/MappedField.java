package net.aydini.common.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.aydini.common.mapper.Mapper;
import net.aydini.common.mapper.cast.Castable;
import net.aydini.common.mapper.cast.DefaultCastClass;
import net.aydini.common.model.enumoration.IfNullValue;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MappedField {

    String fieldName() default "";

    boolean isCustom() default false;

    Class<? extends Mapper> mapperClass() default Mapper.class;

    IfNullValue ifNullValue() default IfNullValue.NULL;

    Class<? extends Castable> castTo() default DefaultCastClass.class;
}

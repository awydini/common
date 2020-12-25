package net.aydini.common.doamin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mappable
{
    boolean nestedMapping() default false;
}

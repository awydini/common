package net.aydini.common.doamin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn
{
    String header() default "";

    Class<? extends Mapper> mapperClass() default Mapper.class;
    
    int index();
    
}

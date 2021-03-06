package net.aydini.common.reflection;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;

import net.aydini.common.exception.ServiceException;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 *         Jul 8, 2020
 */
public class ReflectionUtil
{

    @SuppressWarnings("rawtypes")
    private final static List<Class> SIMPLE_TYPE_LIST;

    static
    {
        SIMPLE_TYPE_LIST = Arrays.asList(new Class[]{Byte.class,Short.class,Integer.class,Float.class,Double.class,Boolean.class,Long.class, BigDecimal.class, BigInteger.class,String.class,java.util.Date.class,java.sql.Date.class});
    }

    public static Set<Field> getClassFields(Class<?> clazz)
    {
        Set<Field> fields = new HashSet<Field>(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> superClass = clazz.getSuperclass();
        while (!superClass.equals(Object.class))
        {
            fields.addAll(getClassFields(superClass));
            superClass = superClass.getSuperclass();
        }
        return fields;
    }


    @SafeVarargs
    public static <T extends Annotation> Set<Field> getAnnotatedClassFields(Class<?> clazz, Class<T>... annotations)
    {
        Set<Field> annotatedFields = new HashSet<Field>();
        for (Class<T> annotaion : annotations)
        {
            annotatedFields.addAll(getClassFields(clazz).stream().filter(field->field.isAnnotationPresent(annotaion)).collect(Collectors.toSet()));
        }
        return annotatedFields;
    }

    public static Field findClassFieldByFieldName(Class<?> clazz, String fieldName)
    {
        Set<Field> fields = getClassFields(clazz);
        for (Field field : fields)
        {
            if (field.getName().equals(fieldName)) return field;
        }
        return null;
    }

    /**
     *
     * @param field
     * @param object
     *            object from which the represented field's value is to be
     *            extracted
     * @return the value of the represented field in object {@code object};
     *         primitive values are wrapped in an appropriate object before
     *         being returned
     *
     * @exception IllegalAccessException
     *                if {@code field} object is enforcing Java language access
     *                control and the underlying field is inaccessible.
     * @exception IllegalArgumentException
     *                if the specified object is not an instance of the class or
     *                interface declaring the underlying field (or a subclass or
     *                implementor thereof).
     * @exception NullPointerException
     *                if the specified object is null and the field is an
     *                instance field.
     * @exception ExceptionInInitializerError
     *                if the initialization provoked by this method fails.
     *
     * @see Field#get
     */
    public static Object getFieldValueFromObject(Field field, Object object)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Method getterMethod = findGetterMethod(field.getName(), object);
        if (getterMethod != null) return getterMethod.invoke(object, null);
        return null;
    }

    private static Method findGetterMethod(String propertyName, Object object)
    {
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(object);
        for (PropertyDescriptor descriptor : descriptors)
        {
            if (descriptor.getName().equals(propertyName))
                return descriptor.getReadMethod();
        }
        return null;
    }

    /**
     *
     * @param targetField
     * @param targetObject
     *            the object whose field should be modified
     * @param value
     *            the new value for the field of {@code targetObject} being
     *            modified
     *
     * @see Field#set
     */
    public static void setFieldValueToObject(Field targetField, Object targetObject, Object value)
    {
        try
        {
            Method setterMethod = findSetterMethod(targetField.getName(), targetObject);
            if (setterMethod != null) setterMethod.invoke(targetObject, value);
        }
        catch(Exception e)
        {
            throw new ServiceException(e);
        }
    }

    private static Method findSetterMethod(String propertyName, Object object)
    {
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(object);
        for (PropertyDescriptor descriptor : descriptors)
        {
            if (descriptor.getName().equals(propertyName)) return descriptor.getWriteMethod();
            
        }
        return null;
    }

    /**
     * 
     * @throws NullPointerException if clazz or interface is null
     */
    public static boolean isClassContainsInterface(Class<?> clazz, Class<?> interfaze)
    {

        boolean containsInterface = ClassUtils.getAllInterfaces(clazz).contains(interfaze);
        if (containsInterface || !hasSuperClass(clazz)) return containsInterface;

        return isClassContainsInterface(getSuperClass(clazz), interfaze);
    }

    public static Class<?> getSuperClass(Class<?> clazz)
    {
        return clazz.getSuperclass();
    }

    public static boolean hasSuperClass(Class<?> clazz)
    {
        return !getSuperClass(clazz).equals(Object.class);
    }

    public static boolean isSimpleType(Class<?> clazz)
    {
        return SIMPLE_TYPE_LIST.contains(clazz);
    }
    
    
    public static <T>  T instantiate(Class<T> clazz)
    {
        try
        {
            return clazz.newInstance();
        }
        catch (Exception e)
        {
            throw new ServiceException(e);
        }
    }
    
    public static boolean isInterface(Class<?> clazz)
    {
        return clazz.isInterface();
    }
    

}

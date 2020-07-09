package net.aydini.common.util.reflection;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         Jul 8, 2020
 */
public class ReflectionUtil
{

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

    public static Set<Field> getAnnotatedClassFields(Class<?> clazz, Class<? extends Annotation> annotation)
    {
        Set<Field> annotatedFields = new HashSet<Field>();
        for (Field field : getClassFields(clazz))
        {
            if (field.isAnnotationPresent(annotation))
            {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }

    @SafeVarargs
    public static <T extends Annotation> Set<Field> getAnnotatedClassFields(Class<?> clazz, Class<T>... annotations)
    {
        Set<Field> annotatedFields = new HashSet<Field>();
        for (Class<T> annotaion : annotations)
        {
            annotatedFields.addAll(getAnnotatedClassFields(clazz, annotaion));
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
     * @exception IllegalAccessException
     *                if this {@code Field} object is enforcing Java language
     *                access control and the underlying field is either
     *                inaccessible or final.
     * @exception IllegalArgumentException
     *                if the specified object is not an instance of the class or
     *                interface declaring the underlying field (or a subclass or
     *                implementor thereof), or if an unwrapping conversion
     *                fails.
     * @exception NullPointerException
     *                if the specified object is null and the field is an
     *                instance field.
     * @exception ExceptionInInitializerError
     *                if the initialization provoked by this method fails.
     *
     * @see Field#set
     */
    public static void setFieldValueToObject(Field targetField, Object targetObject, Object value)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Method setterMethod = findSetterMethod(targetField.getName(), targetObject);
        if (setterMethod != null) setterMethod.invoke(targetObject, value);
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

}

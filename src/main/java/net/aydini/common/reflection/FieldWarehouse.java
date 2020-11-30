package net.aydini.common.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */
public class FieldWarehouse
{
    private Map<String, Set<Field>> classFields = new HashMap<>();

    private Map<Map<String, String>, Set<Field>> annotatedClassFieldsMap = new HashMap<>();

    private static FieldWarehouse instanse;

    private FieldWarehouse()
    {};

    private static void init()
    {
        if (instanse == null) instanse = new FieldWarehouse();
    }

    public static Set<Field> getClassFields(Class<?> clazz)
    {
        init();
        addClassFields(clazz);
        return instanse.classFields.get(clazz.getName());
    }

    private static void addClassFields(Class<?> clazz)
    {
        if (!instanse.classFields.containsKey(clazz.getName()))
            instanse.classFields.put(clazz.getName(), ReflectionUtil.getClassFields(clazz));
    }

    public static Set<Field> getAnnotatedClassFields(Class<?> clazz, Class<? extends Annotation> annotation)
    {
        init();
        addAnnotatedClassFields(clazz, annotation);
        Map<String, String> map = new HashMap<String, String>();
        map.put(clazz.getName(), annotation.getName());
        return instanse.annotatedClassFieldsMap.get(map);
    }

    private static void addAnnotatedClassFields(Class<?> clazz, Class<? extends Annotation> annotation)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put(clazz.getName(), annotation.getName());

        if (!instanse.annotatedClassFieldsMap.containsKey(map))
        {
            instanse.annotatedClassFieldsMap.put(map, ReflectionUtil.getAnnotatedClassFields(clazz, annotation));
        }
    }

}

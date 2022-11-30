package edu.upc.dsa.minim.DB.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ObjectHelper {
    public static String[] getFields(Object entity) {
        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] fieldsStringNames = new String[fields.length];

        int i = 0;
        for(Field field : fields) {
            fieldsStringNames[i++] = field.getName();
        }

        return fieldsStringNames;
    }

    public static void setter(Object object, String property, Object value) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        List<Method> methods = Arrays.stream(object.getClass().getMethods()).toList();
        Method method = methods.stream()
                .filter(x-> x.getName().matches("(?i).*"+"set"+property+".*"))
                .findFirst()
                .orElse(null);
        assert method != null;
        method.invoke(object,value);
    }

    public static Object getter(Object object, String property) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        List<Method> methods = Arrays.stream(object.getClass().getMethods()).toList();
        Method method = methods.stream()
                .filter(x-> x.getName().matches("(?i).*"+"get"+property+".*"))
                .findFirst()
                .orElse(null);
        assert method != null;
        return method.invoke(object,null);
    }

    public static List<Object> createObjects(ResultSet resultSet) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        List<Object> objects = new ArrayList<>();
        String namew= "edu.upc.dsa.minim.Domain.Entity."+resultSet.getMetaData().getTableName(1).substring(0,1).toUpperCase() + resultSet.getMetaData().getTableName(1).substring(1);
        Class theClass = Class.forName(namew);

        String[] fields = getFields(theClass.newInstance());

        while(resultSet.next()) {
            Object object = theClass.newInstance();
            for(String field : fields) {
                setter(object, field, resultSet.getObject(field));
            }
            objects.add(object);
        }
        return objects;
    }
}

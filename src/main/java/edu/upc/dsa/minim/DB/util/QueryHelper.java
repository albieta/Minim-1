package edu.upc.dsa.minim.DB.util;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class QueryHelper {
    public static String createQueryINSERT(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" (");

        String[] fields = ObjectHelper.getFields(entity);

        for(String field : fields) {
            sb.append(field).append(", ");
        }
        sb.setLength(sb.length()-2);

        sb.append(") VALUES (");
        for(String field : fields) {
            sb.append("'").append(ObjectHelper.getter(entity, field)).append("', ");
        }
        sb.setLength(sb.length()-2);
        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        List<String> fields = Arrays.stream(ObjectHelper.getFields(entity)).toList();
        String field = fields.stream()
                .filter(x-> x.matches("(?i).*"+"id"+".*"))
                .findFirst()
                .orElse(null);

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ").append(field);
        sb.append(" = '").append(ObjectHelper.getter(entity, field)).append("'");

        return sb.toString();
    }

    public static String createQueryDeleteRecords(Class theClass) {
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM ").append(theClass.getSimpleName());
        return query.toString();
    }

    public static String createQuerySelectAll(Class theClass) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM ").append(theClass.getSimpleName());
        return query.toString();
    }
}

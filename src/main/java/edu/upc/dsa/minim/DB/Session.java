package edu.upc.dsa.minim.DB;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public interface Session {
    void save(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException;
    void close() throws SQLException;
    Object get(Class theClass, int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException;
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    void deleteRecords(Class theClass);
    List<Object> query(String query, Class theClass, HashMap params);
}

package edu.upc.dsa.minim.DB;

import edu.upc.dsa.minim.DB.util.ObjectHelper;
import edu.upc.dsa.minim.DB.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try{
            pstm = conn.prepareStatement(insertQuery);
            pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public Object get(Class theClass, int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Object entity = theClass.newInstance();
        ObjectHelper.setter(entity, "id", id);

        String selectQuery = QueryHelper.createQuerySELECT(entity);

        PreparedStatement pstm = null;
        Object object = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            object = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public List<Object> findAll(Class theClass) {
        String selectQuery = QueryHelper.createQuerySelectAll(theClass);

        PreparedStatement pstm = null;
        List<Object> objects = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            objects = ObjectHelper.createObjects(pstm.executeQuery());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }

    @Override
    public void deleteRecords(Class theClass) {
        String selectQuery = QueryHelper.createQueryDeleteRecords(theClass);

        PreparedStatement pstm = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

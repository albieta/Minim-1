package edu.upc.dsa.minim.DB.util;

import edu.upc.dsa.minim.Domain.Entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class QueryHelperTest {
    @Test
    public void testCreateQueryINSERT() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        User user = new User("1A", "Alba", "Roma");
        String query = QueryHelper.createQueryINSERT(user);
        Assert.assertEquals("INSERT INTO User (userId, userName, userSurname, processedOrders) VALUES ('1A', 'Alba', 'Roma', '[]')", query);
    }

    @Test
    public void testCreateQuerySELECT() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        user.setUserId("1A");
        String query = QueryHelper.createQuerySELECT(user);
        Assert.assertEquals("SELECT * FROM User WHERE userId = '1A'", query);
    }

    @Test
    public void testCreateQueryDeleteRecords() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String query = QueryHelper.createQueryDeleteRecords(User.class);
        Assert.assertEquals("DELETE FROM User", query);
    }
}

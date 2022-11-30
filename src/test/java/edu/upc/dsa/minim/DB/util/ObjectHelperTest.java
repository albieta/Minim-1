package edu.upc.dsa.minim.DB.util;

import edu.upc.dsa.minim.Domain.Entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ObjectHelperTest {
    @Test
    public void testObjectGetFields() {
        User user = new User();
        String[] strings = ObjectHelper.getFields(user);
        Assert.assertEquals("userId", strings[0]);
        Assert.assertEquals("userName", strings[1]);
        Assert.assertEquals(4, strings.length);
    }

    @Test
    public void testObjectSetter() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        User user = new User();
        ObjectHelper.setter(user, "userName", "Alba");
        Assert.assertEquals("Alba", user.getUserName());
    }

    @Test
    public void testObjectGetter() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        User user = new User("1A", "Alba", "Roma");
        Object userName = ObjectHelper.getter(user, "userName");
        Assert.assertEquals("Alba", userName);
    }
}

package edu.upc.dsa.minim.DB.util;

import edu.upc.dsa.minim.DB.FactorySession;
import edu.upc.dsa.minim.DB.Session;
import edu.upc.dsa.minim.Domain.Entity.Order;
import edu.upc.dsa.minim.Domain.Entity.Product;
import edu.upc.dsa.minim.Domain.Entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SessionImplTest {
    Session session;

    @Before
    public void setUp() {
        this.session = FactorySession.openSession();
        this.session.deleteRecords(User.class);
        this.session.deleteRecords(Order.class);
        this.session.deleteRecords(Product.class);
    }

    @Test
    public void testSaveUser() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        User user = new User("1A", "Alba", "Roma");
        this.session.save(user);
        List<Object> users = this.session.findAll(User.class);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(User.class, users.get(0));
    }

    @Test
    public void testSaveProduct() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Product product = new Product("1A", "coses", 2.0, 3);
        this.session.save(product);
        List<Object> products = this.session.findAll(Product.class);
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(Product.class, products.get(0).getClass());
    }


}

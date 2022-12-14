package edu.upc.dsa.minim.Domain.Repository;

import edu.upc.dsa.minim.Domain.Entity.Order;
import edu.upc.dsa.minim.Domain.Entity.Product;

import java.util.List;

public interface ProductManager {
    public List<Product> productsByPrice();
    public List<Product> productsBySales();
    public void addOrder(Order order);
    public Order processOrder();
    public List<Order> ordersByUser(String userId);

    public void addUser(String s, String name, String surname);
    public void addProduct(String productId, String name, double price);

    public Product getProduct(String productId);

    public int numUsers();
    public int numProducts();

    public int numOrders();

    public int numSales(String b001);

    public int size();
}

package Domain.Repository;

import Domain.Entity.Order;
import Domain.Entity.Product;
import Domain.Exceptions.MaximumOrdersExceededException;
import Domain.Exceptions.NoOrdersToProcessException;

import java.util.List;

public interface ProductManager {
    public List<Product> productsByPrice();
    public List<Product> productsBySales();
    public void addOrder(Order order) throws MaximumOrdersExceededException;
    public Order processOrder() throws NoOrdersToProcessException;
    public List<Order> ordersByUser(String userId);
    /////////////////////////////////////////////
    ////////////////////////////////////////////




    public void addUser(String s, String name, String surname);
    public void addProduct(String productId, String name, double price);

    public Product getProduct(String productId);

    public int numUsers();
    public int numProducts();

    public int numOrders();

    public int numSales(String b001);
}

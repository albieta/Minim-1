package Domain.Repository;

import Domain.Entity.Order;
import Domain.Entity.Product;
import edu.upc.dsa.queue.EmptyQueueException;
import edu.upc.dsa.queue.FullQueueException;

import java.util.List;

public interface ProductManager {
    public List<Product> productsByPrice();
    public List<Product> productsBySales();
    public void addOrder(Order order) throws FullQueueException;
    public Order processOrder() throws EmptyQueueException;
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

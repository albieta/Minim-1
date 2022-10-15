package Infrastructure;

import Domain.Entity.Order;
import Domain.Entity.Product;
import Domain.Entity.User;
import Domain.Entity.VO.LP;
import Domain.Repository.ProductManager;
import edu.upc.dsa.queue.ArrayQueueImplementation;
import edu.upc.dsa.queue.EmptyQueueException;
import edu.upc.dsa.queue.FullQueueException;
import edu.upc.dsa.queue.Queue;

import java.util.*;

public class ProductManagerImpl implements ProductManager {
    List<User> users;
    List<Product> products;
    List<Order> ordersProcessed;
    Queue<Order> orders;

    public ProductManagerImpl(){
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.ordersProcessed = new ArrayList<>();
        this.orders = new ArrayQueueImplementation<>(100);
    }
    @Override
    public List<Product> productsByPrice() {
        this.products.sort((Product p1,Product p2)->Double.compare(p1.getPrice(),p2.getPrice()));
        return this.products;
    }

    @Override
    public List<Product> productsBySales() {
        this.products.sort((Product p1, Product p2)->(p1.getNumSales() - p2.getNumSales()));
        return this.products;
    }

    @Override
    public void addOrder(Order order) throws FullQueueException {
        this.orders.push(order);
    }

    @Override
    public Order processOrder() throws EmptyQueueException {
        Order orderToProcess = this.orders.pop();
        executeProcess(orderToProcess);
        return orderToProcess;
    }

    @Override
    public List<Order> ordersByUser(String userId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : this.ordersProcessed) {
            if(Objects.equals(order.getUserId(), userId)){
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public void addUser(String s, String name, String surname) {
        this.users.add(new User(s, name, surname));
    }

    @Override
    public void addProduct(String productId, String name, double price) {
        if (!this.getProduct(productId).isNull()){
            return;
        }
        this.products.add(new Product(productId, name, price, 0));
    }

    @Override
    public Product getProduct(String productId) {
        Product productDesired = new Product("","",0,0);
        for(Product product : this.products){
            if(Objects.equals(product.getProductId(), productId)){
                productDesired = product;
            }
        }
        return productDesired;
    }

    @Override
    public int numUsers() {
        return users.size();
    }

    @Override
    public int numProducts() {
        return products.size();
    }

    @Override
    public int numOrders() {
        return this.orders.size();
    }

    @Override
    public int numSales(String b001) {
        return this.getProduct(b001).getNumSales();
    }

    private void executeProcess(Order orderToProcess) {
        List<LP> elements = orderToProcess.getElements();
        for(LP element : elements) {
            Product product = this.getProduct(element.getProduct());
            int index = products.indexOf(product);
            product.sold(element.getQuantity());
            products.set(index, product);
        }
        this.ordersProcessed.add(orderToProcess);
    }
}

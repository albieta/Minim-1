package edu.upc.dsa.minim.Infrastructure;

import edu.upc.dsa.minim.Domain.Entity.Order;
import edu.upc.dsa.minim.Domain.Entity.Product;
import edu.upc.dsa.minim.Domain.Entity.User;
import edu.upc.dsa.minim.Domain.Entity.VO.LP;
import edu.upc.dsa.minim.Domain.Repository.ProductManager;
import edu.upc.dsa.queue.ArrayQueueImplementation;
import edu.upc.dsa.queue.EmptyQueueException;
import edu.upc.dsa.queue.FullQueueException;
import edu.upc.dsa.queue.Queue;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {
    private static ProductManager instance;
    List<Product> products;
    Map<User, List<Order>> users;
    Queue<Order> orders;

    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.products.size();
        logger.info("size " + ret);

        return ret;
    }
    public ProductManagerImpl(){
        this.products = new ArrayList<>();
        this.users = new HashMap<>();
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
        Order order = this.orders.pop();
        executeProcess(order);
        return order;
    }

    @Override
    public List<Order> ordersByUser(String userId) {
        return this.users.get(getUserFromId(userId));
    }

    @Override
    public void addUser(String s, String name, String surname) {
        this.users.put(new User(s, name, surname), new LinkedList<>());
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
        Product product = new Product("","",0,0);
        for(Product p : this.products){
            if(Objects.equals(p.getProductId(), productId)){
                product = p;
            }
        }
        return product;
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

    private void executeProcess(Order order) {
        for(LP element : order.getElements()) {
            Product product = this.getProduct(element.getProduct());
            int index = products.indexOf(product);
            product.sold(element.getQuantity());
            products.set(index, product);
        }
        this.users.get(getUserFromId(order.getUserId())).add(order);
    }

    private User getUserFromId(String id) {
        for(User key : this.users.keySet()) {
            if(Objects.equals(key.getUserId(), id)){
                return key;
            }
        }
        return null;
    }
}

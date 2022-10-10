import java.util.*;

public class ProductManagerImpl implements ProductManager{
    List<User> users;
    List<Product> products;
    List<Order> ordersProcessed;
    Order[] orders;
    int pointer;
    int maximum = 100;

    public ProductManagerImpl(){
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.ordersProcessed = new ArrayList<>();
        this.orders = new Order[maximum];
        this.pointer = 0;
    }
    @Override
    public List<Product> productsByPrice() {
        this.products.sort(new ProductComparatorByPrice());
        return this.products;
    }

    @Override
    public List<Product> productsBySales() {
        this.products.sort(new ProductComparatorBySales());
        return this.products;
    }

    @Override
    public void addOrder(Order order) throws MaximumOrdersExceededException {
        if(isFull()){
            throw new MaximumOrdersExceededException();
        }

        this.orders[pointer] = order;
        this.pointer++;
    }

    @Override
    public Order processOrder() throws NoOrdersToProcessException {
        if(isEmpty()){
            throw new NoOrdersToProcessException();
        }

        Order orderToProcess = orders[0];
        executeProcess(orderToProcess);
        rearrangeOrders();
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
        return this.pointer;
    }

    @Override
    public int numSales(String b001) {
        return this.getProduct(b001).getNumSales();
    }

    private void rearrangeOrders() {
        for (int i=0; i<this.pointer-1;i++) {
            this.orders[i] = this.orders[i+1];
        }
        this.pointer--;
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

    private boolean isFull() {
        return (pointer==maximum);
    }

    private boolean isEmpty() {
        return (pointer==0);
    }
}

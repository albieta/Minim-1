import java.util.ArrayList;
import java.util.List;

public class ProductManagerImpl implements ProductManager{
    List<User> users;
    List<Product> products;
    Order[] orders;
    int pointer;
    int maximum = 100;

    public ProductManagerImpl(){
        this.users = new ArrayList<User>();
        this.products = new ArrayList<Product>();
        this.orders = new Order[maximum];
        this.pointer = 0;
    }
    @Override
    public List<Product> productsByPrice() {
        return null;
    }

    @Override
    public List<Product> productsBySales() {
        return null;
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
        return null;
    }

    @Override
    public void addUser(String s, String name, String surname) {
        this.users.add(new User(s, name, surname));
    }

    @Override
    public void addProduct(String productId, String name, double price) {
        this.products.add(new Product(productId, name, price, 0));
    }

    @Override
    public Product getProduct(String productId) {
        return null;
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

    }

    private boolean isFull() {
        return (pointer==maximum);
    }

    private boolean isEmpty() {
        return (pointer==0);
    }
}

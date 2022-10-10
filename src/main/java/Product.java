import java.util.Objects;

public class Product {
    String productId;
    String productName;
    double price;
    int numSales;

    public Product(String productId, String productName, double price, int numSales){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.numSales = numSales;
    }
    public String getProductId() {
        return this.productId;
    }

    public double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.productName;
    }

    public int getNumSales() {
        return this.numSales;
    }

    public void sold(int quantity) {
        this.numSales = numSales + quantity;
    }

    public boolean isNull(){
        return(Objects.equals(productId, ""));
    }
}

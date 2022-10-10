package Domain.Entity.VO;

public class LP {
    int quantity;
    String product;

    public LP(int quantity, String product){
        this.quantity = quantity;
        this.product = product;
    }

    public String getProduct(){
        return this.product;
    }

    public int getQuantity(){
        return this.quantity;
    }
}

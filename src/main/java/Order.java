import java.util.*;

public class Order {
    String userId;
    List<LP> elements;

    public Order(String userId){
        this.userId = userId;
        this.elements = new ArrayList<LP>();
    }
    public void addLP(int i, String b001) {
        elements.add(new LP(i, b001));
    }

    public LP getLP(int i) {
        return elements.get(i);
    }
}

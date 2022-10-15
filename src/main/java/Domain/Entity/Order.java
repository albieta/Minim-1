package Domain.Entity;

import Domain.Entity.VO.LP;

import java.util.*;

public class Order {
    String userId;
    LinkedList<LP> elements;

    public Order(String userId){
        this.userId = userId;
        this.elements = new LinkedList<>();
    }

    public LinkedList<LP> getElements(){
        return this.elements;
    }

    public String getUserId(){
        return this.userId;
    }

    public void addLP(int i, String b001) {
        elements.add(new LP(i, b001));
    }

    public LP getLP(int i) {
        return elements.get(i);
    }
}

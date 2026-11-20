
import java.util.*;

abstract class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    Product(int id, String name, double price, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = qty;
    }

    int getId() { return id; }
    String getName() { return name; }
    double getPrice() { return price; }
    int getQuantity() { return quantity; }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("SmartStock - work in progress");
    }
}
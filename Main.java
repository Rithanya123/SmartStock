
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

    abstract double getTax();
}

class GeneralProduct extends Product {
    GeneralProduct(int id, String n, double p, int q) {
        super(id, n, p, q);
    }
    double getTax() { return 0.05; }
}

class GroceryProduct extends Product {
    GroceryProduct(int id, String n, double p, int q) {
        super(id, n, p, q);
    }
    double getTax() { return 0.0; }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("SmartStock sys");
    }
}
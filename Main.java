
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
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> stock = new ArrayList<>();

    static void addProduct() {
        System.out.print("Enter ID, Name, Price, Quantity: ");
        int id = sc.nextInt();
        String name = sc.next();
        double price = sc.nextDouble();
        int qty = sc.nextInt();

        System.out.print("1.General  2.Grocery: ");
        int type = sc.nextInt();

        Product p;
        if (type == 2)
            p = new GroceryProduct(id, name, price, qty);
        else
            p = new GeneralProduct(id, name, price, qty);

        stock.add(p);
        System.out.println("Product added!");
    }

    static void viewStock() {
        if (stock.isEmpty()) {
            System.out.println("No products yet!");
            return;
        }
        for (Product p : stock) {
            System.out.println(p.getId() + " " + p.getName() + " Rs." + p.getPrice() + " qty:" + p.getQuantity());
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== SMARTSTOCK =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: viewStock(); break;
                case 3: System.out.println("bye"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }
}
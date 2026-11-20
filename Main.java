
import java.util.*;

abstract class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int minStock;

    Product(int id, String name, double price, int qty, int min) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = qty;
        this.minStock = min;
    }

    int getId() { return id; }
    String getName() { return name; }
    double getPrice() { return price; }
    int getQuantity() { return quantity; }
    int getMinStock() { return minStock; }

    abstract double getTax();
}

class GeneralProduct extends Product {
    GeneralProduct(int id, String n, double p, int q, int m) {
        super(id, n, p, q, m);
    }
    double getTax() { return 0.05; }
}

class GroceryProduct extends Product {
    GroceryProduct(int id, String n, double p, int q, int m) {
        super(id, n, p, q, m);
    }
    double getTax() { return 0.0; }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> stock = new ArrayList<>();
    static HashMap<Integer, Product> products = new HashMap<>();

    // binary search by id
    static Product search(int id) {
        int l = 0, h = stock.size() - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (stock.get(mid).getId() == id)
                return stock.get(mid);
            if (stock.get(mid).getId() < id)
                l = mid + 1;
            else h = mid - 1;
        }
        return null;
    }

    // sorting by id
    static void sortStock() {
        for (int i = 1; i < stock.size(); i++) {
            Product key = stock.get(i);
            int j = i - 1;
            while (j >= 0 && stock.get(j).getId() > key.getId()) {
                stock.set(j + 1, stock.get(j));
                j--;
            }
            stock.set(j + 1, key);
        }
    }

    static void addProduct() {
        System.out.print("Enter ID, Name, Price, Quantity, Min Stock: ");
        int id = sc.nextInt();
        String name = sc.next();
        double price = sc.nextDouble();
        int qty = sc.nextInt();
        int min = sc.nextInt();

        if (products.containsKey(id)) {
            System.out.println("product alredy exists!!");
            return;
        }

        System.out.print("1.General  2.Grocery: ");
        int type = sc.nextInt();

        Product p;
        if (type == 2)
            p = new GroceryProduct(id, name, price, qty, min);
        else
            p = new GeneralProduct(id, name, price, qty, min);

        stock.add(p);
        products.put(id, p);
        sortStock();
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
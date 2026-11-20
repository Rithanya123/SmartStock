
import java.util.*;

// Custom Exception
class InsufficientStockException extends Exception {
    InsufficientStockException(String msg) {
        super(msg);
    }
}

abstract class Product {
    private int id, quantity, minStock;
    private String name;
    private double price;

    Product(int id, String name, double price, int qty, int min) {
        this.id = id;
        this.name = name;
        this.price = price;
        quantity = qty;
        minStock = min;
    }

    int getId() { return id; }
    String getName() { return name; }
    double getPrice() { return price; }
    int getQuantity() { return quantity; }
    int getMinStock() { return minStock; }

    abstract double getTax();

    // Unique feature: Smart Restock
    int suggestRestock() {
        return Math.max(0, minStock - quantity);
    }
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
        try {
            System.out.print("Enter ID, Name, Price, Quantity, Min Stock: ");
            int id = sc.nextInt();
            String name = sc.next();
            double price = sc.nextDouble();
            int qty = sc.nextInt(), min = sc.nextInt();

            if (products.containsKey(id) || id <= 0 ||
                price <= 0 || qty < 0 || min < 0)
                throw new IllegalArgumentException("Invalid or duplicate product!");

            System.out.print("1.General  2.Grocery: ");
            int type = sc.nextInt();

            if (type != 1 && type != 2)
                throw new IllegalArgumentException("Invalid product type!");

            Product p = type == 2
                ? new GroceryProduct(id, name, price, qty, min)
                : new GeneralProduct(id, name, price, qty, min);

            stock.add(p);
            products.put(id, p);
            sortStock();

            System.out.println("Product added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewStock() {
        if (stock.isEmpty()) {
            System.out.println("No products available!");
            return;
        }

        System.out.println("\n======= INVENTORY =======");
        for (Product p : stock) {
            System.out.println(p.getId() + " " + p.getName()
                + " | Price: Rs." + p.getPrice()
                + " | Quantity: " + p.getQuantity());
        }
    }

    // Option 3: Smart Restock Suggestion
    static void restockAlert() {
        System.out.println("\n======= RESTOCK ALERT =======");
        boolean found = false;

        for (Product p : stock) {
            if (p.getQuantity() <= p.getMinStock()) {
                found = true;
                System.out.println("Product: " + p.getName());
                System.out.println("Current Stock: " + p.getQuantity());
                System.out.println("Suggested Reorder: "
                    + p.suggestRestock() + " units");
            }
        }

        if (!found)
            System.out.println("All products have sufficient stock!");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== SMARTSTOCK =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Smart Restock Alert");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: viewStock(); break;
                case 3: restockAlert(); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
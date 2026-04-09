import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Billable {
    double calculateTotalPrice();
}

class Item {
    String brand;
    String model;
    double price;

    Item(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Price: $" + this.price);
    }
}

class Watch extends Item {
    String type;

    Watch(String brand, String model, double price, String type) {
        super(brand, model, price);
        this.type = type;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Type: " + this.type);
    }
}

class Customer implements Billable {
    String customerId;
    String name;
    String email;
    List<Item> cart;

    Customer(String name, String customerId, String email) {
        this.name = name;
        this.customerId = customerId;
        this.email = email;
        this.cart = new ArrayList<>();
    }

    @Override
    public double calculateTotalPrice() {
        double total = 0.0;
        for (Item item : cart) {
            total += item.price;
        }
        return total;
    }

    void addToCart(Item item) {
        cart.add(item);
        System.out.println("Added " + item.brand + " " + item.model + " to your cart.");
    }

    void viewCart() {
        System.out.println("Shopping Cart:");
        for (Item item : cart) {
            item.displayDetails();
            System.out.println("----------------");
        }
    }

    void checkout() {
        for (Item item : cart) {
            item.displayDetails();
        }
        System.out.println("Total Price: $" + calculateTotalPrice());
    }
}

public class WatchBuying {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the customer name: ");
        String name = sc.nextLine();
        System.out.println("Enter the customer id: ");
        String id = sc.nextLine();
        System.out.println("Enter the customer email: ");
        String email = sc.nextLine();
        Customer c1 = new Customer(name, id, email);

        boolean i = true;
        while (i) {
            System.out.println("Select a command: " +
                    "\n1. Add watch" + "\n2. View cart" +
                    "\n3. Checkout" +
                    "\n4. Quit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter brand: ");
                    String brand = sc.next();
                    System.out.println("Enter model: ");
                    String model = sc.next();
                    System.out.println("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.println("Enter type: ");
                    String type = sc.next();
                    Watch watch = new Watch(brand, model, price, type);
                    c1.addToCart(watch);
                    break;
                case 2:
                    c1.viewCart();
                    break;
                case 3:
                    c1.checkout();
                    i = false;
                    break;
                case 4:
                    i = false;
                    break;
            }
        }
    }
}

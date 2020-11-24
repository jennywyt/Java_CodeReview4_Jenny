package shop.core;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    String name;
    String address;

    HashMap<Product, Integer> storeroom = new HashMap<>();
    ArrayList<User> customers = new ArrayList<>();

    public void displayCustomers(PrintStream destination) {
        for (int i = 0; i < customers.size(); i++) {
            User user = customers.get(i);
            destination.println(i + " " + user.getFullName());
        }
    }

    public ArrayList<User> getCustomers() {
        return customers;
    }

    public void addCustomer(User customer) {
        customers.add(customer);
    }

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addProduct(Product product, int amount) throws StockLimitedReachedException {
        int currentStock = getStock(product);

        if (amount + currentStock > 15) {
            throw new StockLimitedReachedException();
        }

        storeroom.put(product, currentStock + amount);
        System.out.println("shop.core.Product added to the shop");
    }

    private void removeProduct(Product product, int amount) throws NoMoreStockException {
        int currentStock = getStock(product);
        if (currentStock < amount) {
            throw new NoMoreStockException();
        }

        storeroom.put(product, currentStock - amount);
        if (currentStock < 5) {
            System.out.println("Staff: It is time to fill" + " " + product.productName + " up.");
        }
        System.out.println(product.productName + " reduce " + amount + " from stock");
    }

    public void buyProduct(User user, Product product) throws NoMoreStockException {
        System.out.println(user.lastName + " buys product " + product.productName + " from the shop.");
        // stock before
        System.out.println("There are " + getStock(product) + " " + product.productName);
        removeProduct(product, 1);

        user.addToPurchaseHistory(product);
        // stock after
        System.out.println("There are " + getStock(product) + " " + product.productName);
    }

    private int getStock(Product product) {
        return storeroom.getOrDefault(product, 0);
    }

    public HashMap<Product, Integer> getProducts() {
        return storeroom;
    }

    @Override
    public String toString() {
        return "shop.core.Shop{" +
                "name=" + name +
                ", address='" + address + '\'' +
                ", products=" + storeroom +
                '}';
    }

}

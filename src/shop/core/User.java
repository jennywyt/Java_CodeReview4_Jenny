package shop.core;

import java.io.PrintStream;
import java.util.ArrayList;

public class User {
    private static int newUserId;
    int userId;
    String firstName;
    String lastName;
    String email;
    String address;
    String zip;
    String phoneNumber;

    ArrayList<Product> purchase = new ArrayList<>();


    public User(String firstName, String lastName, String email, String address, String zip, String phoneNumber) {
        newUserId += 1;
        this.userId = newUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ",  firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public void addToPurchaseHistory(Product product) {
        purchase.add(product);
        System.out.println(product.productName + " is added to the purchase history of user " + userId);
    }

    public void displayPurchaseHistory(PrintStream out) {
        out.println("Purchase History:");
        for (Product product : purchase) {
            out.println(product.productName + " " + product.productPrice);
        }
    }

}

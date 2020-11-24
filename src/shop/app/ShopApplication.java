package shop.app;

import shop.core.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import static shop.core.Product.ProductCategory.DRESS;
import static shop.core.Product.ProductCategory.SPORT;

public class ShopApplication {
    static Shop myShop = new Shop("Sisu", "Friedrichstraße 12, 1010 Wien");

    public static void main(String[] args) {
        addTestData();

        printHeader();

        int userInput;

        do {
            printMenu();
            userInput = getUserInput(7, "your service");
            switch (userInput) {
                case 1:
                    displayAllProduct();
                    break;
                case 2:
                    displayAllProductFromCategory();
                    break;
                case 3:
                    displayOutOfStockProducts();
                    break;
                case 4:
                    displayAllProductUnder5();

                    break;
                case 5:
                    printReport();
                    break;
                case 6:
                    myShop.displayCustomers(System.out);
                    break;

            }
        } while (userInput != 0);

    }

    private static void displayAllProductFromCategory() {
        System.out.println("Category");
        for (int i = 0; i < Product.ProductCategory.values().length; i++) {
            Product.ProductCategory currentCategory = Product.ProductCategory.values()[i];
            System.out.println((i + 1) + ": " + currentCategory);
        }

        int ProductCategoryChoice = getUserInput(Product.ProductCategory.values().length, "a category");
        Product.ProductCategory productCategory = Product.ProductCategory.values()[ProductCategoryChoice-1];
        for (Map.Entry<Product, Integer> entry : myShop.getProducts().entrySet()) {
            Product product = entry.getKey();
            if (product.getProductCategory() == productCategory) {
                System.out.println(product);
            }
        }
    }

    private static void displayOutOfStockProducts() {
        for (Map.Entry<Product, Integer> entry : myShop.getProducts().entrySet()) {
            int stock = entry.getValue();
            if (stock == 0) {
                Product product = entry.getKey();
                System.out.println(product);
            }
        }
    }

    private static void displayAllProductUnder5() {
        for (Map.Entry<Product, Integer> entry : myShop.getProducts().entrySet()) {
            int stock = entry.getValue();
            if (stock < 5) {
                System.out.println(entry.getKey());
            }
        }
    }

    private static void displayAllProduct() {
        myShop.getProducts();
        System.out.println(myShop.getProducts());
    }

    private static void addTestData() {
        User user1 = new User("Chris", "Martin", "chris.martin@gmail.com", "Rauscherstrasse 15", "1200", "004312762549");
        User user2 = new User("Adam", "Levin", "a.levin@gmail.com", "Stumpergasse 44-46", "1060", "0043015952951");

        myShop.addCustomer(user1);
        myShop.addCustomer(user2);

        Product product1 = new Product("Nike Running Tights", "Black,Dry-Fit,Elastic", 30, SPORT);
        Product product10 = new Product("Summer Dress", "Hollister, Blue and White Striipe", 30, DRESS);

        try {
            myShop.addProduct(product1, 2);
            myShop.addProduct(product10, 2);
        } catch (StockLimitedReachedException e) {
            e.printStackTrace();
        }

        try {
            myShop.buyProduct(user1, product1);
            myShop.buyProduct(user1, product1);
        } catch (NoMoreStockException e) {
            System.out.println("Error: No more stock");
        }
    }

    public static void printHeader() {
        System.out.println("+*********+");
        System.out.println("+ Welcome +");
        System.out.println("+   to    +");
        System.out.println("+  Sisu   +");
        System.out.println("+*********+");

    }

    public static void printMenu() {
        System.out.println("How can I help you with?");
        System.out.println("1: Display all products");
        System.out.println("2: Display products from category");
        System.out.println("3: Display out of stock products");
        System.out.println("4: Display products that are < 5");
        System.out.println("5: Purchase History of User");
        System.out.println("6: Display all customer");
        System.out.println("0. Exit");

    }

    public static int getUserInput(int maxChoices, String whatToSelect) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > maxChoices) {
            try {
                System.out.println("Please select " + whatToSelect + ". ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input can only be number. ");
            }
        }
        return choice;
    }

    public static void printReport() {
        System.out.println("Which user's purchase report you would like to print?");
        for (int i = 0; i < myShop.getCustomers().size(); i++) {
            User user = myShop.getCustomers().get(i);
            System.out.println(i + " " + user.getFullName());
        }
        int customerIndex = getUserInput(myShop.getCustomers().size(), "Customer");


        try {
            File report = new File("report.txt");

            try (PrintStream myWriter = new PrintStream(new FileOutputStream(report))) {
                myShop.getCustomers().get(customerIndex).displayPurchaseHistory(myWriter);
            }

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}


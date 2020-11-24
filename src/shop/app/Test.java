package shop.app;

import shop.core.*;

import static shop.core.Product.ProductCategory.*;

public class Test {
    public static void main(String[] args) {
        Product product1 = new Product("Nike Running Tights", "Black,Dry-Fit,Elastic", 30, SPORT);
        Product product2 = new Product("Pullover", "American Eagle,White", 55, PULLOVER);
        Product product3 = new Product("Beanie", "Adidas, Black", 20, ACCESSORY);
        Product product4 = new Product("Shopper Bag", "Guess,Black,Pratical,Large Volume", 100, BAG);
        Product product5 = new Product("Dress", "Mango, Every Woman need a little black dress", 40, DRESS);
        Product product6 = new Product("Air Max 270", "Nike,magenta,Stylish", 105, SPORT);
        Product product7 = new Product("Day Backpack", "Vans,Check in black and white", 25, BAG);
        Product product8 = new Product("Crossbody clutch", "Michael Kors, Brown", 120.95, BAG);
        Product product9 = new Product("Ear rings", "Aldo, 20 pieces Gold color", 15.95, ACCESSORY);
        Product product10 = new Product("Summer Dress", "Hollister, Blue and White Striipe", 30, DRESS);

        //make a shop
        Shop myShop = new Shop("Sisu", "Friedrichstraße 12, 1010 Wien");

        // Add products to shop
        System.out.println("Add products to the shop");
        try {
            myShop.addProduct(product1, 1);
        } catch (StockLimitedReachedException e) {
            e.printStackTrace();
        }
        try {
            myShop.addProduct(product2, 2);
        } catch (StockLimitedReachedException e) {
            e.printStackTrace();
        }
        try {
            myShop.addProduct(product2, 1);
        } catch (StockLimitedReachedException e) {
            e.printStackTrace();
        }
        System.out.println(myShop);


        User user1 = new User("Chris", "Martin", "chris.martin@gmail.com", "Rauscherstrasse 15", "1200", "004312762549");
        User user2 = new User("Adam", "Levin", "a.levin@gmail.com", "Stumpergasse 44-46", "1060", "0043015952951");

        myShop.addCustomer(user1);
        myShop.addCustomer(user2);
        myShop.displayCustomers(System.out);

        try {
            myShop.buyProduct(user1, product1);
            myShop.buyProduct(user1, product1);
        } catch (NoMoreStockException e) {
            System.out.println("Error: No more stock");
        }

        user1.displayPurchaseHistory(System.out);

        try {
            myShop.addProduct(product1, 20);
            System.out.println(myShop.toString());
        } catch (StockLimitedReachedException e) {
            System.out.println("Error: The items reached the maximum limit.");
        }
    }
}

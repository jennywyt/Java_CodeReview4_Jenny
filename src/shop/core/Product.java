package shop.core;

public class Product {
    private static int nextProductId = 1;
    int productId;
    String productName;
    String productDescription;
    double productPrice;
    ProductCategory productCategory;

    public enum ProductCategory {
        SPORT,
        PULLOVER,
        ACCESSORY,
        BAG,
        DRESS,
    }

    public Product(String productName, String productDescription, double productPrice, ProductCategory productCategory) {
        this.productId = nextProductId;
        nextProductId += 1;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "shop.core.Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}

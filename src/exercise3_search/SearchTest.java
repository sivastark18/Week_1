// SearchTest.java
package exercise3_search;
import java.util.Arrays;

public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Apple", "Fruit"),
            new Product(2, "Banana", "Fruit"),
            new Product(3, "Carrot", "Vegetable")
        };

        // Linear Search
        int index1 = SearchUtil.linearSearch(products, "Banana");
        System.out.println("Found at index (linear): " + index1);

        // For binary search, sort by productName
        Arrays.sort(products, (a, b) -> a.productName.compareToIgnoreCase(b.productName));
        int index2 = SearchUtil.binarySearch(products, "Carrot");
        System.out.println("Found at index (binary): " + index2);
    }
}

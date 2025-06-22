// SearchUtil.java
package exercise3_search;

public class SearchUtil {
    public static int linearSearch(Product[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Product[] products, String name) {
        int low = 0, high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comp = products[mid].productName.compareToIgnoreCase(name);
            if (comp == 0) return mid;
            else if (comp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}

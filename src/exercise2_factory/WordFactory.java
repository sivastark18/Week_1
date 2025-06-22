// WordFactory.java
package exercise2_factory;

public class WordFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

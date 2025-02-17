import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class Product {
    String name;
    String price;
    String rating;
    public Product(String name, String price, String rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
}
public class SKILLCRAFT_SD_04 {
    private static final String URL = "https://www.bestbuy.com/site/searchpage.jsp?st=wireless+headphones";
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .get();
            Elements productElements = doc.select("div.sku-item");
            for (Element productElement : productElements) {
                String name = productElement.select("h4.sku-header").text();
                String price = productElement.select("div.priceView-hero-price span").text();
                String rating = productElement.select("span.c-reviews-v4").text();
                System.out.println("Found product: " + name + ", Price: " + price + ", Rating: " + rating);

                if (!name.isEmpty() && !price.isEmpty()) {
                    products.add(new Product(name, price, rating));
                }
            }
            try (FileWriter writer = new FileWriter("products.csv")) {
                writer.append("Name,Price,Rating\n");
                for (Product product : products) {
                    writer.append(product.name).append(",")
                          .append(product.price).append(",")
                          .append(product.rating).append("\n");
                }
            }
            for (Product product : products) {
                System.out.println("Name: " + product.name);
                System.out.println("Price: " + product.price);
                System.out.println("Rating: " + product.rating);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
}
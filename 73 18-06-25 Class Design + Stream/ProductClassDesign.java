// You are building an E-Commerce Product Insights Engine for a marketplace like 
// Amazon or Flipkart. The platform stores information about various products, 
// their pricing history, and user ratings.

// Your job is to:

//     1. Accept data for multiple products.
    
//     2. Each product has:
//         🎯 Multiple price entries (date + price)
//         🎯 Multiple ratings (user + stars out of 5)
        
//     3. Calculate:
//         🎯  Average price of the product
//         🎯 Price volatility score: Standard deviation of prices
//         🎯 Average rating

//     4. Classify products into Insight Tiers:
//         🟢 Stable & Loved: Volatility < 100 and Rating ≥ 4.0
//         🟡 Unstable but Popular: Volatility ≥ 100 and Rating ≥ 4.0
//         🔴 Unstable & Poorly Rated: Volatility ≥ 100 and Rating < 4.0
//         ⚪ Stable but Low-Rated: Volatility < 100 and Rating < 4.0
        
// Sample Input:
// -------------
// 2               // Number of products
// EchoDot         // ProductName
// 3               // Number of price entries
// 2024-06-01 3499 // priceDate priceAmount
// 2024-06-10 3299
// 2024-06-15 3599
// 2               // Number of ratings
// Alice 5         // userName stars
// Bob 4
// OldTV           // ProductName
// 4               // Number of price entries
// 2024-05-01 9999 // priceDate priceAmount
// 2024-05-10 10999
// 2024-05-15 11999
// 2024-05-20 8999
// 3               // Number of ratings
// Charlie 2       // userName stars
// Diana 3
// Eve 1

// Sample Output:
// --------------
// Product: EchoDot, AvgPrice: 3465.7, Volatility: 124.7, AvgRating: 4.5, Tier: Unstable but Popular
// Product: OldTV, AvgPrice: 10499.0, Volatility: 1118.0, AvgRating: 2.0, Tier: Unstable & Poorly Rated


import java.util.*;

public class ProductClassDesign {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            int m = Integer.parseInt(sc.nextLine());

            List<PriceEntry> prices = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                String[] parts = sc.nextLine().split(" ");
                prices.add(new PriceEntry(parts[0], Double.parseDouble(parts[1])));
            }

            int k = Integer.parseInt(sc.nextLine());
            List<Rating> ratings = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                String[] parts = sc.nextLine().split(" ");
                ratings.add(new Rating(parts[0], Integer.parseInt(parts[1])));
            }

            products.add(new Product(name, prices, ratings));
        }

        InsightEngine engine = new InsightEngineImpl();
        // System.out.println("=== Product Insights Summary ===");
        for (Product p : products) {
            ProductInsight insight = engine.analyze(p);
            System.out.println(insight);
        }
        sc.close();
    }
}

// TODO: Complete this POJO
class PriceEntry {
    // String date; double amount
    private String date;
    private double amount;
    public PriceEntry(String data, double amount){
        setDate(date);
        setAmount(amount);
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public double getAmount(){
        return this.amount;
    }
}

// TODO: Complete this POJO
class Rating {
    // String userName; int stars
    private String userName;
    private int stars;
    public Rating(String userName, int stars){
        setUserName(userName);
        setStars(stars);
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setStars(int stars){
        this.stars = stars;
    }
    public int getStars(){
        return this.stars;
    }
}

// TODO: Complete this POJO
class Product {
    // String name; List<PriceEntry>; List<Rating>
    private String name;
    private List<PriceEntry> priceEntry;
    private List<Rating> rating;
    
    public Product(String name, List<PriceEntry> priceEntry, List<Rating> rating){
        this.name = name;
        this.priceEntry = priceEntry;
        this.rating = rating;
    }
    
    public List<PriceEntry> getPriceEntry(){
        return this.priceEntry;
    }
    public List<Rating> getRating(){
        return this.rating;
    }
    public String getName(){
        return this.name;
    }
}

// TODO: Complete this POJO
class ProductInsight {
    // Product; double avgPrice; double volatility; double avgRating; String insightTier
    // Override toString() for output
    private double avgPrice;
    private double volatility;
    private double avgRating;
    String insightTier;
    private Product p;
    
    public ProductInsight(Product p, double avgPrice, double volatility, double avgRating, String insightTier){
        setAvgPrice(avgPrice);
        setVolatility(volatility);
        setAvgRating(avgRating);
        setInsightTier(insightTier);
        setProduct(p);
    }
    public void setAvgPrice(double avgPrice){
        this.avgPrice = avgPrice;
    }
    public void setVolatility(double volatility){
        this.volatility = volatility;
    }
    public void setAvgRating(double avgRating){
        this.avgRating = avgRating;
    }
    public void setInsightTier(String insightTier){
        this.insightTier = insightTier;
    }
    public void setProduct(Product p){
        this.p = p;
    }
    public String getInsightTier(){
        return this.insightTier;
    }
    public double getAvgPrice(){
        return this.avgPrice;
    }
    public double getVolatility(){
        return this.volatility;
    }
    public double getAvgRating(){
        return this.avgRating;
    }
//     Product: EchoDot, AvgPrice: 3465.7, Volatility: 124.7, AvgRating: 4.5, Tier: Unstable but Popular
// Product: OldTV, AvgPrice: 10499.0, Volatility: 1118.0, AvgRating: 2.0, Tier: Unstable & Poorly Rated
    @Override
    public String toString(){
        return "Product: " + p.getName() + ", AvgPrice: " + String.format("%.1f",getAvgPrice()) + ", Volatility: " + String.format("%.1f",getVolatility()) + ", AvgRating: " + String.format("%.1f",getAvgRating()) + ", Tier: " + getInsightTier();
    }
}

interface InsightEngine {
    ProductInsight analyze(Product p);
}

// TODO: Implement InsightEngineImpl using Math.pow and Math.sqrt for std deviation
class InsightEngineImpl implements InsightEngine {
    public ProductInsight analyze(Product p) {
        // Logic:
        // - Calculate avgPrice
        // - Calculate standard deviation
        // - Calculate avgRating
        // - Tier assignment
        List<PriceEntry> priceEntry = p.getPriceEntry();
        double avgPrice = 0.0;
        int n = priceEntry.size();
        for(PriceEntry i : priceEntry){
            avgPrice += i.getAmount();
        }
        avgPrice /= n;
        
        double diff = 0.0;
        
        for(PriceEntry i : priceEntry){
            diff += Math.pow((i.getAmount()-avgPrice),2);
        }
        double std = Math.pow(diff/n, 0.5);
        
        List<Rating> rating = p.getRating();
        
        double stars = 0.0;
        
        for(Rating i : rating){
            stars += i.getStars();
        }
        
        double avgRating = stars/rating.size();
        
        String insightTier = (std < 100 && avgRating >= 4.0) ? "Stable & Loved" : (std >= 100 && avgRating >= 4.0) ? "Unstable but Popular" : (std >= 100 && avgRating < 4.0) ? "Unstable & Poorly Rated" : "Stable but Low-Rated";
        
        ProductInsight prod = new ProductInsight(p, avgPrice, std, avgRating, insightTier);
        return prod; // TODO
    }
}

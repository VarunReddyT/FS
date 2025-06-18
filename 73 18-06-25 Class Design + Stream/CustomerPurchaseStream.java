// Given a list of CustomerPurchase objects, each representing a purchase 
// with customerId, customerName, purchaseAmount, and category. 
// Compute the tier of customers based on total purchases 
// (only counting purchases ≥ 500.0).

// Tiers:
// - Platinum ≥ 5000
// - Gold ≥ 2000 and < 5000
// - Silver < 2000

// Note: Display the customers in descending order of their purchases of same tier.

// Example 1
// ---------

// Input:
// 4
// C101 Alice 1200 Electronics
// C102 Bob 499 Books
// C101 Alice 4500 Travel
// C103 Charlie 800 Furniture

// Output:
// C101 Alice : Platinum
// C103 Charlie : Silver


// Example 2
// ----------
// Input:
// 8
// C801 Mia 1000 Electronics
// C801 Mia 1200 Furniture
// C801 MIA 3000 Lighting
// C802 Olivia 1001 Apparel
// C803 Emma 1999 Jewelry
// C803 Emma 1 Books
// C804 Ava 2000 Appliances
// C805 Mia 1000 Garden

// Output:
// C801 Mia : Platinum
// C804 Ava : Gold
// C803 Emma : Silver
// C802 Olivia : Silver
// C805 Mia : Silver

import java.util.*;
import java.util.stream.Collectors;

class CustomerPurchase{
    String customerId;
    String customerName;
    double purchaseAmount;
    String category;
    String tier;
    public CustomerPurchase(String customerId, String customerName, double purchaseAmount, String category){
        this.customerId = customerId;
        this.customerName = capitalize(customerName);
        this.purchaseAmount = purchaseAmount;
        this.category = category;
    }
    
    public static String capitalize(String name){
        if(name == null || name.isEmpty()){
            return name;
        }
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
    @Override
    public String toString(){
        return this.customerId + " " + this.customerName + " : " + this.tier;
    }
}

public class CustomerPurchaseStream{
    public static void getCustomers(List<CustomerPurchase> arr, int n){
        List<String> res = arr.stream()
                            .filter(c -> c.purchaseAmount >= 500.0)
                            .collect(Collectors.groupingBy(
                                    c -> c.customerId + " " + c.customerName,
                                    Collectors.summingDouble(c -> c.purchaseAmount)
                            ))
                            .entrySet().stream()
                            .sorted(Comparator
                                    .comparing(Map.Entry<String,Double>::getValue, Comparator.reverseOrder())
                                    )
                            .map(c -> {
                                double totalPurchase = c.getValue();
                                String key = c.getKey();
                                String tier = "";
                                if(totalPurchase >= 5000){
                                    tier = "Platinum";
                                }
                                else if(totalPurchase >= 2000){
                                    tier = "Gold";
                                }
                                else{
                                    tier = "Silver";
                                }
                                return key + " : " + tier;
                            })
                            .collect(Collectors.toList());
        // System.out.println(res);
        for(String i : res){
            System.out.println(i);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<CustomerPurchase> arr = new ArrayList<>();
        for(int i = 0;i<n;i++){
            String[] input = sc.nextLine().split(" ");
            arr.add(new CustomerPurchase(input[0], input[1], Double.parseDouble(input[2]), input[3]));
        }
        getCustomers(arr,n);
        sc.close();
    }
}
import java.util.*;

public class DistinctCount {
    public static void findDistinctCount(int arr[], int K) {
        HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>();
        for (int i = 0; i < K; i++){
            hM.put(arr[i], hM.getOrDefault(arr[i], 0) + 1);
        }
        System.out.print(hM.size() + " ");
        for (int i = K; i < arr.length; i++) {
            if (hM.get(arr[i - K]) == 1) {
                hM.remove(arr[i - K]);
            } 
            else{
                hM.put(arr[i - K], hM.get(arr[i - K]) - 1);
            }
            hM.put(arr[i], hM.getOrDefault(arr[i], 0) + 1);
            System.out.print(hM.size() + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter array elements size");
        int n = sc.nextInt();
        int array[] = new int[n];
        System.out.println("enter the elements");
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("enter the subarray size");
        int k = sc.nextInt();
        findDistinctCount(array, k);
        sc.close();
    }
}
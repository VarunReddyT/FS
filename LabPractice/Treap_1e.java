// e) Write a JAVA Program to implement TREAP with its operations
// Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a pair 
// (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
// Example 1:
// Input: nums = [1,3,2,3,1] 
// Output: 2
// Example 2:
// Input: nums = [2,4,3,5,1] 
// Output: 3
// Constraints:
// 1 <= nums.length <= 5 * 104
// -2^31 <= nums[i] <= 2^31 – 1

import java.util.*;

class Pair<U,V>{
    U left;
    V right;

    Pair(U left, V right){
        this.left = left;
        this.right = right;
    }
}

class Item{
    Double key;
    Double priority;
    long count;
    Item left, right;
    Item(){
        left = right = null;
        key = null;
        priority = null;
        count = 1L;
    }
}

public class Treap_1e {
    public static int countReversePairs(int[] nums){
        if(nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        Item root = null;
        int ans = 0;
        Random rand = new Random();
        for(int i = len-1;i>=0;i--){
            Pair<Item,Long> res = searchNotGreaterThan(root, (nums[i]+0.0)/2);
            ans += res.right;
            root = res.left;

            Item e = new Item();
            e.priority = rand.nextDouble();
            e.key = nums[i] + 0.0;
            root = insert(root,e);
        }
        return ans;
    }

    static Pair<Item,Long> searchNotGreaterThan(Item root, Double key){
        Item[] res = split(root, key);
        long ret = count(res[0]);

        return new Pair<>(merge(res[0], res[1]), ret);
    }

    static Item insert(Item root, Item item){
        Item ret = null;
        if(root == null){
            ret = item;
        }
        else if(root.priority < item.priority){
            Item[] res = split(root, item.key);
            item.left = res[0];
            item.right = res[1];

            ret = item;
        }
        else{
            if(root.key > item.key){
                ret = insert(root.left, item);
                root.left = ret;
                ret = root;
            }
            else{
                ret = insert(root.right, item);
                root.right = ret;
                ret = root;
            }
        }
        updateCount(ret);
        return ret;
    }
    static Item merge(Item l, Item r){
        Item ret = null;
        if(l == null || r == null){
            ret = (l != null) ? l : r;
        }
        else if(l.priority > r.priority){
            l.right = merge(l.right, r);
            ret = l;
        }
        else{
            r.left = merge(l, r.left);
            ret = r;
        }
        updateCount(ret);
        return ret;
    }
    static Item[] split(Item item, double key){
        Item[] ret = null;
        if(item == null){
            ret = new Item[]{null, null};
        }
        else if(item.key < key){
            ret = split(item.right, key);
            item.right = ret[0];
            ret = new Item[]{item, ret[1]};
        }
        else{
            ret = split(item.left, key);
            item.left = ret[1];
            ret = new Item[]{ret[0], item};
        }
        updateCount(item);
        return ret;
    }

    static void updateCount(Item item){
        if(item != null){
            item.count = 1 + count(item.left) + count(item.right);
        }
    }

    static long count(Item item){
        return (item != null) ? item.count : 0L;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(countReversePairs(nums));
        sc.close();
    }
}

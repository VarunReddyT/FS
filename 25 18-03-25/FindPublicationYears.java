// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 


// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014
                  
// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.


import java.util.*;

class BST{
    int data;
    BST left;
    BST right;
    BST(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class FindPublicationYears{
    public static BST insert(BST curr, int val){
        if(curr == null){
            curr = new BST(val);
            return curr;
        }
        else if(val < curr.data){
            curr.left = insert(curr.left,val);
        }
        else{
            curr.right = insert(curr.right,val);
        }
        return curr;
    }
    public static void isPresent(BST root, int val, ArrayList<ArrayList<Integer>> res, int[] min, int[] max, boolean[] flag){
        if(root == null){
            return;
        }
        if(root.data == val){
            res.add(new ArrayList<>(Arrays.asList(val,val)));
            flag[0] = true;
            return;
        }
        else if(root.data > val){
            int diff = root.data - val;
            if(min[0] > diff){
                min[0] = diff;
                min[1] = root.data;
            }
            isPresent(root.left,val,res,min,max,flag);
        }
        else{
            int diff = val-root.data;
            if(max[0] > diff){
                max[0] = diff;
                max[1] = root.data;
            }
            isPresent(root.right,val,res,min,max,flag);
        }
    }
    public static ArrayList<ArrayList<Integer>> getYears(BST root, int[] arr, int[] queries){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int i : queries){
            boolean[] flag = {false};
            int[] min = new int[2];
            min[0] = Integer.MAX_VALUE;
            int[] max = new int[2];
            max[0] = Integer.MAX_VALUE;
            isPresent(root,i,res,min,max,flag);
            if(flag[0] == false){
                res.add(new ArrayList<>(Arrays.asList(max[0]==Integer.MAX_VALUE ? -1 : max[1],min[0]==Integer.MAX_VALUE ? -1 : min[1])));
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String[] queries = sc.nextLine().split(" ");
        int[] arr = new int[input.length];
        int[] queriesInt = new int[queries.length];
        BST root = null;
        for(int i = 0;i<input.length;i++){
            arr[i] = Integer.parseInt(input[i]);
            if(i == 0){
                root = new BST(arr[i]);
            }
            else{
                BST curr = root;
                root = insert(curr, arr[i]);
            }
        }
        for(int i = 0;i<queries.length;i++){
            queriesInt[i] = Integer.parseInt(queries[i]);
        }
        System.out.println(getYears(root,arr,queriesInt));
        sc.close();
    }
}
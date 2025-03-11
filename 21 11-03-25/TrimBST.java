// Imagine you're the curator of an ancient manuscript archive. Each manuscript is
// assigned a unique significance score, and the archive is arranged so that every 
// manuscript on the left has a lower score and every manuscript on the right has a
// higher score, forming a special ordered display. Now, for an upcoming exhibition,
// scholars have decided that only manuscripts with significance scores between low 
// and high (inclusive) are relevant. Your task is to update the archive by removing
// any manuscripts whose scores fall outside the range [low, high]. Importantly, 
// while you remove some manuscripts, you must preserve the relative order of the 
// remaining ones—if a manuscript was originally displayed as a descendant of another, 
// that relationship should remain intact. It can be proven that there is a unique 
// way to update the archive.

// Display the manuscript of the updated archive. Note that the main manuscript 
// (the root) may change depending on the given score boundaries.

// Input format:
// Line 1: space separated scores to build the manuscript archive
// Line 2: two space seperated integers, low and high.

// Output format:
// space separated scores of the updated archive

// Example 1:
// input=
// 1 0 2
// 1 2
// output=
// 1 2

// Explanation:
// Initial archieve:
//       1
//      / \
//     0   2


// Updated archieve:
//     1
//      \
//       2
// After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
// and its right manuscript 2 remain.

// Example 2:
// input=
// 3 0 4 2 1
// 1 3
// output=
// 3 2 1

// Explanation:
// Initial archieve:
//           3
//          / \
//         0   4
//          \
//           2
//          /
//         1

// Updated archieve:
//       3
//      /
//     2
//    /
//   1


/*
class Node {
	int data;
	Node left, right;

	public Node(int data) {
		this.data = data;
		this.left = this.right = null;
	}
}
*/

// class Solution {
//     public Node trimBST(Node curr, int low, int high) {
//         // write your code here   
//         if(curr == null){
//             return null;
//         }
//         if(curr.data < low){
//             return trimBST(curr.right,low,high);
//         }
//         if(curr.data > high){
//             return trimBST(curr.left,low,high);
//         }
//         curr.left = trimBST(curr.left,low,high);
//         curr.right = trimBST(curr.right,low,high);
//         return curr;
//     }
// }


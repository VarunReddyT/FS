// You are given a triangular assortment of size N, where:
// N is an even integer representing the total number of rows.
// The triangles are arranged in a diamond-shaped structure with:
// The number of triangles in each row increasing up to row N/2,
// Then decreasing from row N/2 + 1 to N.
// Each triangle is assigned a unique ID, starting from 1 at the top, and 
// increasing left-to-right within a row and top-to-bottom across rows.
// Two triangles are adjacent if they share a common edge.


// Given a triangular grid of size N, generate the adjacency list representation 
// of the structure. That is, for each triangle ID, output the list of triangle IDs to 
// which it is directly connected (i.e., triangles sharing a common edge with it).

// Input Format:
// -------------
// An even integer N (2 ≤ N ≤ 20), representing the number of rows in the diamond.

// Output Format:
// --------------
// For each triangle ID from 1 to (N × N / 2), print:
// Triangle X -> [list of adjacent triangle IDs]
// The adjacency list must reflect the physical edge-sharing in the diamond layout.

// Sample Input:
// -------------
// 4

// Sample Output:
// ---------------
// 1 -> [3]
// 2 -> [3, 5]
// 3 -> [1, 2, 4]
// 4 -> [3, 7]
// 5 -> [2, 6]
// 6 -> [5, 7, 8]
// 7 -> [4, 6]
// 8 -> [6]


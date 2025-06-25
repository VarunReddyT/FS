// Sreedhar is a farmer, and he started harvesting the watermelon crop, 
// the crop grown very well. There are several watermelons in the crop. 
// Sreedhar started picking up the watermelons one by one.
// After each pick, he keeps the watermelon in a truck placed inside the crop.

// The crop is in the from of 2D grid of size m*n.
// You will be given the positions of the truck, Sreedhar's, and the watermelons.  
// Positions are represented by the cells in the 2D grid. 

// Your task is to find the minimum distance for Sreedhar to collect 
// all the watermelons and put them inside the truck one by one. 

// Sreedhar can only take at most one watermelon at one time 
// and can move in four directions - up, down, left and right, to the adjacent cell. 
// The distance is represented by the number of moves.

// Input Format:
// -------------
// Line-1: Two space separated integers m and n, size of crop. 
// Line-2: Two space separated integers, position of the truck. 
// Line-3: Two space separated integers, position of Sreedhar.
// Line-4: An integer W, number of watermelons in the crop.
// Next W lines: Two space separated integers, positions of watermelon. 

// Output Format:
// --------------
// An integer, minimum distance covered by Sreedhar to pickup all the watermelons


// Sample Input-1:
// ---------------
// 5 7		//size of the crop as 2D grid.
// 2 2		//Truck Position
// 4 4		//Sreedhar Position
// 2		//Number of watermelons
// 3 0		//Watermelon positions
// 2 5

// Sample Output-1:
// ----------------
// 12

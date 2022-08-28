/*
Given a matrix of M x N elements (M rows, N columns), Print all elements of the matrix in diagonal order in Time Complexity O(m*n) and Space Complexity O(1)

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]

METHOD:

TIME: O(M * N).

SPACE: O(1).
*/

private static void diagonalPrint(int matrix [][]) { 
	    	
	int m = matrix.length;
	int n = matrix[0].length;
		
	// printing the first half
	for(int k = 0; k < m; k++) {
		    
		 int i = k;
		 int j = 0;
		    
		 while(i >= 0) {
		     System.out.print(matrix[i][j]+" ");
		     i--;
		     j++;
		 }
	}
		
	// printing the second half
	for(int k = 1; k < n; k++) {
		    
		int i = m-1;
		int j = k;
		    
		while(j <= n-1) {
		    System.out.print(matrix[i][j]+" ");
		    i--;
		    j++;
		 }
	}
} 
/*
Given a boolean 2D array, where each row is sorted. Find the row with the maximum number of 1s.

Example 1:

Input:
N = 3, M = 4
Mat[] = {{0 1 1 1},
         {0 0 1 1},
         {0 0 1 1}}

Output: 0

Explanation: Row 0 has 3 ones whereas rows 1 and 2 have just 2 ones.

Example 2:

Input: 
N = 2, M = 2
Mat[] = {{0 1},
         {1 1}}

Output: 1

Explanation: Row 1 has 2 ones whereas row 0 has just a single one. 

Input matrix
0 1 1 1
0 0 1 1
1 1 1 1  // this row has maximum 1s
0 0 0 0

Output: 2


METHOD 1:(BRUTE FORCE)
	APPROACH:
		A simple method is to do a row-wise traversal of the matrix, count the number of 1s in each row, and compare the count with max. Finally, return the index of the row with 
		maximum 1s.

TIME: O(N * M), where N is the no. of rows and M is the no. of columns.

SPACE: O(1).

METHOD 2: (BINARY SEARCH)
	APPROACH:
		Apply binary search in each row and find the last occurance of 0, then we can easily calculate the no. of zeros in a row using (M - no. of zeros) & also the no. of ones.

TIME: O(NlogM), as there are N no. of rows and in each row there are M no. of columns.

SPACE: O(1).
*/

class Sol
{
    private static int binarySearch(int[][] M, int r, int low, int high) {
        
        // finding the last occurance of zero
        while(low <= high) {
            
            int mid = (low + high) / 2;
            
            if(M[r][mid] == 0) {
                low = mid + 1;
            }
            
            if(M[r][mid] > 0) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        return low;
    }
    
    public static int maxOnes (int Mat[][], int N, int M)
    {
        int max = Integer.MIN_VALUE;
        int row_index = 0;
        
        for(int i = 0; i < N; i++) {
            
            // here we basically find the last occurance of the 0 
            // this will indicate the no. of zeros in a row, so the no. of 1's in that row is (M - zero_count)
            int zeroCount = binarySearch(Mat, i, 0, M-1);
            
            if(M - zeroCount > max) {
                max = M - zeroCount;
                row_index = i;
            }
        }
        
        return row_index;
    }
}

/*
METHOD 3:
	APPROACH:
		Step1: Get the index of first (or leftmost) 1 in the first row.
		Step2: Do following for every row after the first row 
				1) IF the element on left of previous leftmost 1 is 0, ignore this row. 
				2) ELSE Move left until a 0 is found. Update the leftmost index to this index and max_row_index to be the current row.

TIME: O(M + N).

SPACE: O(1).
*/

class Sol
{
    public static int maxOnes (int Mat[][], int N, int M)
    {
        int row_index = 0;
        int j = M - 1;
        
        for(int i = 0; i < N; i++) {
            
           // Move left until a 0 is found
           // basically finding the index of the first occurance of 1

           while(j >= 0 && Mat[i][j] == 1) {
               j--;       	    // Update the index of leftmost 1 seen so far
               row_index = i;    // Update max_row_index
           }
        }
        
        if(row_index==0 && Mat[0][M-1]==0)
            return -1;
            
        return row_index;
    }
}

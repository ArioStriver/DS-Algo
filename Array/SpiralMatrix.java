/*
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100


METHOD: (USING FOUR POINTER)
	APPROACH:
		Traverse right and increment rowBegin, then traverse down and decrement colEnd, then traverse left and decrement rowEnd, and finally traverse up and increment colBegin.

		The only tricky part is that when we traverse left or up we have to check whether the row or col still exists to prevent duplicates. 

TIME: O(M * N).

SPACE: O(1).
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> res = new ArrayList<>();
        
        if(matrix.length == 0){
            return res;
        }
        
        int rowBegin = 0;
        int colBegin = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;
        
        while(rowBegin <= rowEnd && colBegin <= colEnd){
            
            // traverse right
            for(int i = colBegin; i <= colEnd; i++){
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            
            // traverse down
            for(int i = rowBegin; i <= rowEnd; i++){
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            
            // for skipping duplicate checking whether the row still exists
            if(rowBegin <= rowEnd){

                // traverse left
                for(int i = colEnd; i >= colBegin; i--){
                    res.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            
            // for skipping duplicate checking whether the col still exists
            if(colBegin <= colEnd){

                // traverse up
                for(int i = rowEnd; i >= rowBegin; i--){
                    res.add(matrix[i][colBegin]);
                }
                colBegin++;
            }
        }
        return res;
    }
}